package com.unidev.textlines;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TextLineService {

    @Value("${textline.storage:storage}")
    private String storagePath;

    public Collection<String> listFiles(String path) {
        throw new RuntimeException("Not implemented");
    }

    public Collection<String> randomLines(String path, int count) {
        throw new RuntimeException("Not implemented");
    }

}
