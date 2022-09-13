package com.sourcesense.assignment.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourcesense.assignment.BaseUnitTest;
import com.sourcesense.assignment.model.News;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class NewsMapperTest extends BaseUnitTest {

    @Mock
    private ObjectMapper objectMapper;

    @Spy
    private NewsMapper newsMapper;

    @Test
    void parseHackerNewsTopStoriesIds() {
        List<Integer> news =  IntStream.range(1, 10).boxed().toList();

        doReturn(news).when(newsMapper).parse(any(), any());

        List<Integer> result = newsMapper.parseHackerNewsTopStoriesIds();

        verify(newsMapper).parse(eq(null), eq(List.class));

        assertThat(result)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(news);
    }

    @Test
    void parseItemById() {
    }

    @Test
    void parseNytTopStories() {
    }
}