package com.sourcesense.assignment.converter;

import com.sourcesense.assignment.BaseUnitTest;
import com.sourcesense.assignment.model.NYTStory;
import com.sourcesense.assignment.model.News;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NYTConverterTest extends BaseUnitTest {

    private final NYTConverter nytConverter = new NYTConverter();

    @Test
    void toNews() {
        NYTStory nytStory = random(NYTStory.class);

        News result = nytConverter.toNews(nytStory);

        assertEquals(nytStory.getTitle(), result.getTitle());
        assertEquals(nytStory.getPublishedDate(), result.getDate());
        assertEquals(nytStory.getUrl(), result.getUrl());
    }

}