package com.sourcesense.assignment.service;

import com.sourcesense.assignment.BaseUnitTest;
import com.sourcesense.assignment.mapper.NewsMapper;
import com.sourcesense.assignment.model.HackerNewsStory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class HackerNewsServiceTest extends BaseUnitTest {

    @Mock
    private NewsMapper newsMapper;

    @InjectMocks
    private HackerNewsService hackerNewsService;

    @Test
    void getNews() {
        int limit = 10;
        String hackerNewsTopStoriesUrl = "hackerNewsTopStoriesUrl";
        String hackerNewsItemUrl = "hackerNewsItemUrl";
        ReflectionTestUtils.setField(hackerNewsService, "hackerNewsTopStoriesUrl", hackerNewsTopStoriesUrl);
        ReflectionTestUtils.setField(hackerNewsService, "hackerNewsItemUrl", hackerNewsItemUrl);

        List<Integer> ids =  IntStream.range(0, limit).boxed().toList();
        HackerNewsStory hackerNewsStory = random(HackerNewsStory.class);

        when(newsMapper.parse(anyString(), eq(List.class))).thenReturn(ids);
        when(newsMapper.parse(anyString(), eq(HackerNewsStory.class))).thenReturn(hackerNewsStory);

        List<HackerNewsStory> result = hackerNewsService.getNews(limit);

        verify(newsMapper).parse(hackerNewsTopStoriesUrl, List.class);
        ids.forEach(id -> verify(newsMapper).parse(hackerNewsItemUrl + id + ".json", HackerNewsStory.class));

        result.forEach(story -> assertThat(story).usingRecursiveComparison().isEqualTo(hackerNewsStory));
    }

}