package com.sourcesense.assignment.service;

import com.sourcesense.assignment.BaseUnitTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class NewsServiceTest extends BaseUnitTest {

    @Mock
    private HackerNewsService hackerNewsService;

    @Mock
    private NYTService nytService;

    @InjectMocks
    private NewsService newsService;

}