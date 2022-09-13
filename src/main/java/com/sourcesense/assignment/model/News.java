package com.sourcesense.assignment.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class News {

    private String title;
    private ZonedDateTime date;
    private String url;

}