package com.sourcesense.assignment.converter;

import com.sourcesense.assignment.BaseUnitTest;
import com.sourcesense.assignment.model.HackerNewsStory;
import com.sourcesense.assignment.model.News;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HackerNewsConverterTest extends BaseUnitTest {

    private final HackerNewsConverter hackerNewsConverter = new HackerNewsConverter();

    @Test
    void toNews() {
        HackerNewsStory hackerNewsStory = random(HackerNewsStory.class);
        hackerNewsStory.setTime(1663146683L);

        News result = hackerNewsConverter.toNews(hackerNewsStory);

        assertEquals(hackerNewsStory.getTitle(), result.getTitle());
        assertEquals(hackerNewsStory.getTime(), result.getDate().toEpochSecond());
        assertEquals(hackerNewsStory.getUrl(), result.getUrl());
    }

}