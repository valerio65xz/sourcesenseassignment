package com.sourcesense.assignment.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourcesense.assignment.BaseUnitTest;
import com.sourcesense.assignment.exception.ResponseErrorEnum;
import com.sourcesense.assignment.exception.ResponseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class NewsMapperTest extends BaseUnitTest {

    @Mock
    private ObjectMapper objectMapper;

    private final NewsMapper newsMapper = new NewsMapper();

    private String url;

    private String parsed;

    @BeforeEach
    void setUp(){
        url = "http://en.wikipedia.org";
        parsed = "parsed";
        ReflectionTestUtils.setField(newsMapper, "mapper", objectMapper);
    }

    @Test
    void parse_success() throws IOException {
        when(objectMapper.readValue(any(URL.class), eq(String.class))).thenReturn(parsed);

        String result = newsMapper.parse(url, String.class);

        verify(objectMapper).readValue(eq(new URL(url)), eq(String.class));

        assertThat(result).isEqualTo(parsed);
    }

    @Test
    void parse_failForMalformedUrlException() throws IOException {
        when(objectMapper.readValue(any(URL.class), eq(String.class))).thenThrow(MalformedURLException.class);

        assertThatExceptionOfType(ResponseException.class)
                .isThrownBy(() -> newsMapper.parse(url, String.class))
                .matches(e -> e.getError().equals(ResponseErrorEnum.BAD_REQUEST));

        verify(objectMapper).readValue(eq(new URL(url)), eq(String.class));
    }

    @Test
    void parse_failForIOException() throws IOException {
        when(objectMapper.readValue(any(URL.class), eq(String.class))).thenThrow(IOException.class);

        assertThatExceptionOfType(ResponseException.class)
                .isThrownBy(() -> newsMapper.parse(url, String.class))
                .matches(e -> e.getError().equals(ResponseErrorEnum.BAD_JSON));

        verify(objectMapper).readValue(eq(new URL(url)), eq(String.class));
    }

}