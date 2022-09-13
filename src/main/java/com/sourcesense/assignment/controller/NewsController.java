package com.sourcesense.assignment.controller;

import com.sourcesense.assignment.exception.ResponseErrorEnum;
import com.sourcesense.assignment.exception.ResponseException;
import com.sourcesense.assignment.model.News;
import com.sourcesense.assignment.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public ResponseEntity<List<News>> getAggregatedNews(){
        List<News> newsList = newsService.getAggregatedNews();
        newsService.sortNewsByDate(newsList);
        return ResponseEntity.ok(newsList);
    }

    @GetMapping("{source}")
    public ResponseEntity<List<News>> getNewsBySource(@PathVariable("source") String source) {
        List<News> newsList;

        if (source.equals("hackernews")){
            newsList = newsService.getNewsFromHackerNews();
            newsService.sortNewsByDate(newsList);
            return ResponseEntity.ok(newsList);
        }
        else if (source.equals("newyorktimes")){
            newsList = newsService.getNewsFromNewYorkTimes();
            newsService.sortNewsByDate(newsList);
            return ResponseEntity.ok(newsList);
        }
        else{
            throw new ResponseException(ResponseErrorEnum.BAD_SOURCE);
        }
    }

}