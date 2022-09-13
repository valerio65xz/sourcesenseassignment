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

    //TODO: aggiusta limit
    public List<HackerNewsStory> getTopStories(Integer limit) {
        return newsMapper.getHackerNewsTopStoriesIds().stream()
                .limit(limit)
                .map(newsMapper::getItemFromId)
                .toList();
    }

}