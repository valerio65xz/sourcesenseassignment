package com.sourcesense.assignment.converter;

import com.sourcesense.assignment.model.NYTStory;
import com.sourcesense.assignment.model.News;
import org.springframework.stereotype.Service;

@Service
public class NYTConverter {

    public News toNews(NYTStory nytStory){
        News news = new News();

        news.setTitle(nytStory.getTitle());
        news.setDate(nytStory.getPublishedDate());
        news.setUrl(nytStory.getUrl());

        return news;
    }

}