package com.unidev.textlines.dto;

import lombok.*;

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
