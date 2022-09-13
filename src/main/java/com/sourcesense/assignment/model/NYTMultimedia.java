package com.sourcesense.assignment.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NYTMultimedia {

    private String url;
    private String format;
    private Integer height;
    private Integer width;
    private String type;
    private String subtype;
    private String caption;
    private String copyright;

}
