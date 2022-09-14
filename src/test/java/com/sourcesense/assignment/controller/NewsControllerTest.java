package com.sourcesense.assignment.controller;

import com.sourcesense.assignment.ControllerTest;
import com.sourcesense.assignment.model.News;
import com.sourcesense.assignment.service.NewsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

class NewsControllerTest extends ControllerTest {

    @MockBean
    private NewsService newsService;

    @Test
    void getAggregatedNews() {
        int limit = 10;
        String section = "section";
        String url = "/news?hackerNewsLimit=" + limit + "&nytSection=" + section;
        List<News> newsList = IntStream.range(0, limit)
                .mapToObj(i -> random(News.class))
                .toList();

        when(newsService.getAggregatedNews(anyInt(), anyString())).thenReturn(newsList);

        List<News> result = performAndExpectWithCollection(get(url), List.class, News.class);
        newsService.sortNewsByDate(newsList);

        assertThat(result.get(0))
                .usingRecursiveComparison()
                .ignoringFields("date")
                .isEqualTo(newsList.get(0));

        verify(newsService).getAggregatedNews(limit, section);
    }

    @Test
    void getNewsFromHackerNews() {
        int limit = 10;
        String url = "/news/hackernews?limit=" + limit;
        List<News> newsList = IntStream.range(0, limit)
                .mapToObj(i -> random(News.class))
                .toList();

        when(newsService.getNewsFromHackerNews(anyInt())).thenReturn(newsList);

        List<News> result = performAndExpectWithCollection(get(url), List.class, News.class);
        newsService.sortNewsByDate(newsList);

        assertThat(result.get(0))
                .usingRecursiveComparison()
                .ignoringFields("date")
                .isEqualTo(newsList.get(0));

        verify(newsService).getNewsFromHackerNews(limit);
    }

    @Test
    void getNewsFromNewYorkTimes() {
        String section = "section";
        String url = "/news/newyorktimes?section=" + section;
        List<News> newsList = IntStream.range(0, 10)
                .mapToObj(i -> random(News.class))
                .toList();

        when(newsService.getNewsFromNewYorkTimes(anyString())).thenReturn(newsList);

        List<News> result = performAndExpectWithCollection(get(url), List.class, News.class);
        newsService.sortNewsByDate(newsList);

        assertThat(result.get(0))
                .usingRecursiveComparison()
                .ignoringFields("date")
                .isEqualTo(newsList.get(0));

        verify(newsService).getNewsFromNewYorkTimes(section);
    }

}