package com.sourcesense.assignment.controller;

import com.sourcesense.assignment.model.News;
import com.sourcesense.assignment.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get the aggregated news from HackerNews and New York Times, ordered by date descending")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The news retrieved",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = News.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid JSON",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<List<News>> getAggregatedNews(
            @Parameter(description = "The limit of news to be retrieved") @RequestParam("hackerNewsLimit") Integer limit,
            @Parameter(description = "The New York Times' section where to look for the news") @RequestParam("nytSection") String section){
        List<News> newsList = newsService.getAggregatedNews(limit, section);
        newsService.sortNewsByDate(newsList);
        return ResponseEntity.ok(newsList);
    }

    @Operation(summary = "Get the news only from HackerNews, ordered by date descending")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The news retrieved",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = News.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid JSON",
                    content = @Content) })
    @GetMapping("hackernews")
    public ResponseEntity<List<News>> getHackerNewsNews(@Parameter(description = "The limit of news to be retrieved") @RequestParam("limit") Integer limit) {
        List<News> newsList = newsService.getNewsFromHackerNews(limit);
        newsService.sortNewsByDate(newsList);
        return ResponseEntity.ok(newsList);
    }

    @Operation(summary = "Get the news only from New York Times, ordered by date descending")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The news retrieved",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = News.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid JSON",
                    content = @Content) })
    @GetMapping("newyorktimes")
    public ResponseEntity<List<News>> getNewYorkTimesNews(@Parameter(description = "The New York Times' section where to look for the news") @RequestParam("section") String section) {
        List<News> newsList = newsService.getNewsFromNewYorkTimes(section);
        newsService.sortNewsByDate(newsList);
        return ResponseEntity.ok(newsList);
    }

}