package com.sourcesense.assignment.service;

import com.sourcesense.assignment.BaseUnitTest;
import com.sourcesense.assignment.converter.HackerNewsConverter;
import com.sourcesense.assignment.converter.NYTConverter;
import com.sourcesense.assignment.model.HackerNewsStory;
import com.sourcesense.assignment.model.NYTStory;
import com.sourcesense.assignment.model.News;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class NewsServiceTest extends BaseUnitTest {

    @Mock
    private HackerNewsService hackerNewsService;
    @Mock
    private NYTService nytService;
    @Mock
    private HackerNewsConverter hackerNewsConverter;
    @Mock
    private NYTConverter nytConverter;
    @Spy
    @InjectMocks
    private NewsService newsService;

    @Test
    void getAggregatedNews() {
        int limit = 10;
        String section = "section";
        News news = random(News.class);
        List<News> hackerNewsList = Collections.singletonList(news);
        List<News> nytNewsList = Collections.singletonList(news);

        doReturn(hackerNewsList).when(newsService).getNewsFromHackerNews(anyInt());
        doReturn(nytNewsList).when(newsService).getNewsFromNewYorkTimes(anyString());

        List<News> result = newsService.getAggregatedNews(limit, section);

        verify(newsService).getNewsFromHackerNews(limit);
        verify(newsService).getNewsFromNewYorkTimes(section);

        assertThat(result)
                .isNotEmpty()
                .usingRecursiveComparison()
                .isEqualTo(Collections.singletonList(news));
    }

    @Test
    void getNewsFromHackerNews() {
        int limit = 10;
        List<HackerNewsStory> hackerNewsStoryList =  IntStream.range(0, limit)
                .mapToObj(i -> random(HackerNewsStory.class))
                .toList();
        News news = random(News.class);

        when(hackerNewsService.getNews(limit)).thenReturn(hackerNewsStoryList);
        when(hackerNewsConverter.toNews(any())).thenReturn(news);

        List<News> result = newsService.getNewsFromHackerNews(limit);

        verify(hackerNewsService).getNews(limit);
        hackerNewsStoryList.forEach(story -> verify(hackerNewsConverter, times(limit)).toNews(story));

        result.forEach(newsFromList -> assertThat(newsFromList)
                .usingRecursiveComparison()
                .isEqualTo(news));
    }

    @Test
    void getNewsFromNewYorkTimes() {
        String section = "section";
        List<NYTStory> nytStoryList =  IntStream.range(0, 10)
                .mapToObj(i -> random(NYTStory.class))
                .toList();
        News news = random(News.class);

        when(nytService.getNews(section)).thenReturn(nytStoryList);
        when(nytConverter.toNews(any())).thenReturn(news);

        List<News> result = newsService.getNewsFromNewYorkTimes(section);

        verify(nytService).getNews(section);
        nytStoryList.forEach(story -> verify(nytConverter, times(10)).toNews(story));

        result.forEach(newsFromList -> assertThat(newsFromList)
                .usingRecursiveComparison()
                .isEqualTo(news));
    }

}