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

    public List<News> getAggregatedNews(){
        Set<News> newsSet = new HashSet<>();

        List<News> hackerNewsList = getNewsFromHackerNews();
        List<News> nytNewsList = getNewsFromNewYorkTimes();

        newsSet.addAll(hackerNewsList);
        newsSet.addAll(nytNewsList);

        return new ArrayList<>(newsSet);
    }

    public List<News> getNewsFromHackerNews(){
        List<HackerNewsStory> hackerNewsStories = hackerNewsService.getTopStories();

        return hackerNewsStories.stream()
                .map(hackerNewsConverter::toNews)
                .collect(Collectors.toList());
    }

    public List<News> getNewsFromNewYorkTimes(){
        //TODO: gestire la sezione
        List<NYTStory> nytStories = nytService.getTopStories("arts");

        return nytStories.stream()
                .map(nytConverter::toNews)
                .collect(Collectors.toList());
    }

    public void sortNewsByDate(List<News> newsList){
        newsList.sort(Comparator.comparing(News::getDate).reversed());
    }

}