package com.sourcesense.assignment.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sourcesense.assignment.exception.ResponseErrorEnum;
import com.sourcesense.assignment.exception.ResponseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class NewsMapper {

    private final ObjectMapper mapper;

    public NewsMapper(){
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    public <T> T parse(String URL, Class<T> clazz){
        try {
            return mapper.readValue(new URL(URL), clazz);
        } catch (MalformedURLException e) {
            throw new ResponseException(ResponseErrorEnum.BAD_REQUEST);
        } catch (IOException e) {
            throw new ResponseException(ResponseErrorEnum.BAD_JSON);
        }
    }

}