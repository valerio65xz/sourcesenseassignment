package com.sourcesense.assignment.controller;

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
    public ResponseEntity<List<News>> getAggregatedNews(
            @RequestParam("hackerNewsLimit") Integer limit,
            @RequestParam("nytSection") String section){
        List<News> newsList = newsService.getAggregatedNews(limit, section);
        newsService.sortNewsByDate(newsList);
        return ResponseEntity.ok(newsList);
    }

    @GetMapping("hackernews")
    public ResponseEntity<List<News>> getHackerNewsNews(@RequestParam("limit") Integer limit) {
        List<News> newsList = newsService.getNewsFromHackerNews(limit);
        newsService.sortNewsByDate(newsList);
        return ResponseEntity.ok(newsList);
    }

    @GetMapping("newyorktimes")
    public ResponseEntity<List<News>> getNewYorkTimesNews(@RequestParam("section") String section) {
        List<News> newsList = newsService.getNewsFromNewYorkTimes(section);
        newsService.sortNewsByDate(newsList);
        return ResponseEntity.ok(newsList);
    }

}