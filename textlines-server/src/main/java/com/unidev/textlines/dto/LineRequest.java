package com.unidev.textlines.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class LineRequest {

    @Getter
    @Setter
    private String file;

    @Getter
    @Setter
    private int count;

}
