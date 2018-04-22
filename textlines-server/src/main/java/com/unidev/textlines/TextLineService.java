package com.unidev.textlines;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TextLineService {

    @Value("${textline.storage:storage}")
    private String storagePath;

    public Collection<String> listFiles(String path) {
        return Arrays.asList(Objects.requireNonNull(new File(storagePath, path).list()));
    }

    public Collection<String> randomLines(String path, int count) {
        throw new RuntimeException("Not implemented");
    }

}
