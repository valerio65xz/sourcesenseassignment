package com.sourcesense.assignment.service;

import com.sourcesense.assignment.mapper.NewsMapper;
import com.sourcesense.assignment.model.HackerNewsStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HackerNewsService {

    private final NewsMapper newsMapper;

    @Value( "${hackernews.topstories.url}" )
    private String hackerNewsTopStoriesUrl;
    @Value( "${hackernews.item.url}" )
    private String hackerNewsItemUrl;

    @Autowired
    public HackerNewsService(NewsMapper newsMapper){
        this.newsMapper = newsMapper;
    }

    public List<HackerNewsStory> getNews(Integer limit) {
        return getTopStoriesIds().stream()
                .limit(limit)
                .map(this::parseItemById)
                .toList();
    }

    private List<Integer> getTopStoriesIds() {
        return newsMapper.parse(hackerNewsTopStoriesUrl, List.class);
    }

    private HackerNewsStory parseItemById(Integer id) {
        return newsMapper.parse(hackerNewsItemUrl + id + ".json", HackerNewsStory.class);
    }

}
