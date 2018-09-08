package com.unidev.textlines.dto;

import lombok.*;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileListRequest {

    @Getter
    @Setter
    private String path;

}
