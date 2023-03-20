package com.hudman.wikireader.wikipedia.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = "ns")
public class Article {
    @JsonProperty("pageid")
    private long id;
    private String title;
    @JsonProperty("extract")
    private String content;
}
