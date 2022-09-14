package com.sourcesense.assignment.service;

import com.sourcesense.assignment.BaseUnitTest;
import com.sourcesense.assignment.mapper.NewsMapper;
import com.sourcesense.assignment.model.NYTResponse;
import com.sourcesense.assignment.model.NYTStory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class NYTServiceTest extends BaseUnitTest {

    @Mock
    private NewsMapper newsMapper;

    @InjectMocks
    private NYTService nytService;

    @Test
    void getNews() {
        String section = "section";
        String nytTopStoriesUrl = "nytTopStoriesUrl";
        String nytApiKey = "nytApiKey";
        ReflectionTestUtils.setField(nytService, "nytTopStoriesUrl", nytTopStoriesUrl);
        ReflectionTestUtils.setField(nytService, "nytApiKey", nytApiKey);

        List<NYTStory> stories =  IntStream.range(0, 10)
                .mapToObj(i -> random(NYTStory.class))
                .toList();
        NYTResponse nytResponse = random(NYTResponse.class);
        nytResponse.setResults(stories);

        when(newsMapper.parse(anyString(), eq(NYTResponse.class))).thenReturn(nytResponse);

        List<NYTStory> result = nytService.getNews(section);

        verify(newsMapper).parse(eq(nytTopStoriesUrl + section + ".json?api-key=" + nytApiKey), eq(NYTResponse.class));

        assertThat(result)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(stories);
    }

}
