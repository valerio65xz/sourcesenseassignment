package com.sourcesense.assignment.service;

import com.sourcesense.assignment.converter.HackerNewsConverter;
import com.sourcesense.assignment.converter.NYTConverter;
import com.sourcesense.assignment.model.HackerNewsStory;
import com.sourcesense.assignment.model.NYTStory;
import com.sourcesense.assignment.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NewsService {

    private final HackerNewsService hackerNewsService;
    private final NYTService nytService;
    private final HackerNewsConverter hackerNewsConverter;
    private final NYTConverter nytConverter;

    @Autowired
    public NewsService(
            HackerNewsService hackerNewsService,
            NYTService nytService,
            HackerNewsConverter hackerNewsConverter,
            NYTConverter nytConverter){
        this.hackerNewsService = hackerNewsService;
        this.nytService = nytService;
        this.hackerNewsConverter = hackerNewsConverter;
        this.nytConverter = nytConverter;
    }

    public List<News> getAggregatedNews(Integer limit, String section){
        Set<News> newsSet = new HashSet<>();

        List<News> hackerNewsList = getNewsFromHackerNews(limit);
        List<News> nytNewsList = getNewsFromNewYorkTimes(section);

        newsSet.addAll(hackerNewsList);
        newsSet.addAll(nytNewsList);

        return new ArrayList<>(newsSet);
    }

    public List<News> getNewsFromHackerNews(Integer limit){
        List<HackerNewsStory> hackerNewsStories = hackerNewsService.getNews(limit);

        return hackerNewsStories.stream()
                .map(hackerNewsConverter::toNews)
                .collect(Collectors.toList());
    }

    public List<News> getNewsFromNewYorkTimes(String section){
        List<NYTStory> nytStories = nytService.getNews(section);

        return nytStories.stream()
                .map(nytConverter::toNews)
                .collect(Collectors.toList());
    }

    public void sortNewsByDate(List<News> newsList){
        newsList.sort(Comparator.comparing(News::getDate).reversed());
    }

}