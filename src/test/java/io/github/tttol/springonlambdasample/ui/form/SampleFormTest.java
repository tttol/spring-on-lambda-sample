package io.github.tttol.springonlambdasample.ui.form;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SampleFormTest {
    @Autowired
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
    @DisplayName("userIdの異常系テスト")
    @ValueSource(strings = {"", "あああああ", "aaa*", "abc!", "abc?", "abcdefghijklmnopqrstuvwxyz"})
    @NullSource // nullがuserIdに渡されたときのテスト
    void invalidUserIdTest(String userId) {
        var sampleForm = new SampleForm(userId, "name");
        var exception = new BindException(sampleForm, "sampleForm");
        validator.validate(sampleForm, exception);
        var actual = Objects.nonNull(exception.getFieldError("userId"));
        assertThat(actual).isTrue();
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
