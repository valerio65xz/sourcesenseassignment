package com.sourcesense.assignment.service;

import com.sourcesense.assignment.mapper.NewsMapper;
import com.sourcesense.assignment.model.HackerNewsStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HackerNewsService {

    private final NewsMapper newsMapper;

    @Autowired
    public HackerNewsService(NewsMapper newsMapper){
        this.newsMapper = newsMapper;
    }

    public List<HackerNewsStory> getNews(Integer limit) {
        return newsMapper.parseHackerNewsTopStoriesIds().stream()
                .limit(limit)
                .map(newsMapper::parseItemById)
                .toList();
    }

}