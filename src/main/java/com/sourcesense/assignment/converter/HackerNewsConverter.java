package com.sourcesense.assignment.converter;

import com.sourcesense.assignment.model.HackerNewsStory;
import com.sourcesense.assignment.model.News;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.TimeZone;

@Service
public class HackerNewsConverter {

    public News toNews(HackerNewsStory hackerNewsStory){
        News news = new News();

        news.setTitle(hackerNewsStory.getTitle());
        news.setDate(ZonedDateTime.ofInstant(Instant.ofEpochSecond(hackerNewsStory.getTime()),
                TimeZone.getDefault().toZoneId()));
        news.setUrl(hackerNewsStory.getUrl());

        return news;
    }

}