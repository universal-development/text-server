package com.unidev.textlines.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LineRequest {

    @Getter
    @Setter
    private String path;

    @Getter
    @Setter
    private int count;

}
