package io.github.tttol.springonlambdasample.ui.form;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SampleFormTest {
    @Autowired
    @Qualifier("localValidatorFactoryBean")
    Validator validator;

    @ParameterizedTest
    @DisplayName("userIdの正常系テスト")
    @ValueSource(strings = {"abcdef", "abCdEf", "AbcDe1234GhiK", "aa-bbB_C12567", "--___safea"})
    void validUserIdTest(String userId) {
        var sampleForm = new SampleForm(userId, "name");
        var exception = new BindException(sampleForm, "sampleForm");
        validator.validate(sampleForm, exception);
        var actual = Objects.isNull(exception.getFieldError("userId"));
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @DisplayName("userIdの必須テスト")
    @ValueSource(strings = "")
    @NullSource // nullがuserIdに渡されたときのテスト
    void blankUserIdTest(String userId) {
        var sampleForm = new SampleForm(userId, "name");
        var exception = new BindException(sampleForm, "sampleForm");
        validator.validate(sampleForm, exception);

        assertThat(Objects.nonNull(exception.getFieldError("userId"))).isTrue();
        assertThat(exception.getFieldError("userId").getDefaultMessage()).isEqualTo("入力必須です");
    }

    @ParameterizedTest
    @DisplayName("userIdのフォーマットエラーテスト")
    @ValueSource(strings = {"あああああ", "aaa*", "abc!", "abc?", "abcdefghijklmnopqrstuvwxyz"})
    void invalidUserIdTest(String userId) {
        var sampleForm = new SampleForm(userId, "name");
        var exception = new BindException(sampleForm, "sampleForm");
        validator.validate(sampleForm, exception);

        assertThat(Objects.nonNull(exception.getFieldError("userId"))).isTrue();
        assertThat(exception.getFieldError("userId").getDefaultMessage()).isEqualTo("半角20文字以内で入力してください");
    }

    @ParameterizedTest
    @DisplayName("name必須テスト")
    @ValueSource(strings = "")
    @NullSource
    void nameMustBeNotBlankTest(String name) {
        var sampleForm = new SampleForm("a", name);
        var exception = new BindException(sampleForm, "sampleForm");
        validator.validate(sampleForm, exception);
        var actual = Objects.nonNull(exception.getFieldError("name"));
        assertThat(actual).isTrue();
    }
}
