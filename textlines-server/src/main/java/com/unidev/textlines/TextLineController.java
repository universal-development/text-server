package com.unidev.textlines;

import com.unidev.textlines.dto.FileListRequest;
import com.unidev.textlines.dto.LineRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(TextLineController.API_PATH)
public class TextLineController {

    public static final String API_PATH = "/api/v1";

    private TextLineService textLineService;

    public TextLineController(TextLineService textLineService) {
        this.textLineService = textLineService;
    }

    @PostMapping("list")
    public Collection<String> listFiles(@RequestBody FileListRequest fileRequest) {
        return textLineService.listFiles(fileRequest.getPath());
    }

    @PostMapping("lines")
    public Collection<String> fetchLines(@RequestBody LineRequest lineRequest) {
        return textLineService.randomLines(lineRequest.getPath(),lineRequest.getCount());
    }


}
