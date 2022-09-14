package com.sourcesense.assignment.service;

import com.sourcesense.assignment.mapper.NewsMapper;
import com.sourcesense.assignment.model.NYTResponse;
import com.sourcesense.assignment.model.NYTStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NYTService {

    private final NewsMapper newsMapper;

    @Value( "${nytnews.topstories.url}" )
    private String nytTopStoriesUrl;

    @Value( "${nytnews.apikey}" )
    private String nytApiKey;

    @Autowired
    public NYTService(NewsMapper newsMapper){
        this.newsMapper = newsMapper;
    }

    public List<NYTStory> getNews(String section){
        return newsMapper
                .parse(nytTopStoriesUrl + section + ".json?api-key=" + nytApiKey, NYTResponse.class)
                .getResults();
    }

}