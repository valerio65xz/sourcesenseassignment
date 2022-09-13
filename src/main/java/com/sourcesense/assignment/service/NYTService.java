package com.sourcesense.assignment.service;

import com.sourcesense.assignment.mapper.NewsMapper;
import com.sourcesense.assignment.model.NYTStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NYTService {

    private final NewsMapper newsMapper;

    @Autowired
    public NYTService(NewsMapper newsMapper){
        this.newsMapper = newsMapper;
    }

    public List<NYTStory> getTopStories(String section){
        return newsMapper.getNytTopStories(section).getResults();
    }

}