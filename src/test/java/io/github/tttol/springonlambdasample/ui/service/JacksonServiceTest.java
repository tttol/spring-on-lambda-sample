package io.github.tttol.springonlambdasample.ui.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.tttol.springonlambdasample.ui.dto.PersonDto;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonServiceTest {
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void close() throws Exception {
        closeable.close();
    }

    @InjectMocks
    private JacksonService jacksonService;

    @Nested
    class GetCsv {
        @Test
        @DisplayName("PersonDtoオブジェクトをCSV文字列に変換できること")
        void convertTest() throws JsonProcessingException {
            var expected = "A001,Tom,20\n";
            var actual = jacksonService.getCsv(new PersonDto("A001", "Tom", 20));
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("カンマを含む文字列の場合はダブルクォートで囲んでCSV出力")
        void includingCommaTest() throws JsonProcessingException {
            var expected = "A001,\"Thomas, aka Tom\",20\n";
            var actual = jacksonService.getCsv(new PersonDto("A001", "Thomas, aka Tom", 20));
            assertThat(actual).isEqualTo(expected);
        }
    }
}
