package com.sourcesense.assignment.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sourcesense.assignment.exception.ResponseErrorEnum;
import com.sourcesense.assignment.exception.ResponseException;
import com.sourcesense.assignment.model.HackerNewsStory;
import com.sourcesense.assignment.model.NYTResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class NewsMapper {

    @Value( "${hackernews.topstories.url}" )
    private String hackerNewsTopStoriesUrl;
    @Value( "${hackernews.item.url}" )
    private String hackerNewsItemUrl;
    @Value( "${nytnews.topstories.url}" )
    private String nytTopStoriesUrl;
    @Value( "${nytnews.apikey}" )
    private String nytApiKey;
    private final ObjectMapper mapper;

    public NewsMapper(){
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    public List<Integer> parseHackerNewsTopStoriesIds() {
        return parse(hackerNewsTopStoriesUrl, List.class);
    }

    public HackerNewsStory parseItemById(Integer id) {
        return parse(hackerNewsItemUrl + id + ".json", HackerNewsStory.class);
    }

    public NYTResponse parseNytTopStories(String section){
        return parse(nytTopStoriesUrl + section + ".json?api-key=" + nytApiKey, NYTResponse.class);
    }

    private <T> T parse(String url, Class<T> clazz){
        try {
            return mapper.readValue(new URL(url), clazz);
        } catch (MalformedURLException e) {
            throw new ResponseException(ResponseErrorEnum.BAD_REQUEST);
        } catch (IOException e) {
            throw new ResponseException(ResponseErrorEnum.BAD_JSON);
        }
    }

}
