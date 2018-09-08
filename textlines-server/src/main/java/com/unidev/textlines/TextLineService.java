package com.unidev.textlines;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.DirectoryScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class TextLineService {

    @Value("${textline.storage:storage}")
    private String storagePath;

    private Random random = new Random();

    public List<String> listFiles(String path) {
        DirectoryScanner scanner = new DirectoryScanner();
        scanner.setBasedir(storagePath);
        scanner.setIncludes(new String[]{path});
        scanner.scan();
        return Arrays.asList(scanner.getIncludedFiles());
    }

    public Collection<String> randomLines(String path, int keywordCount) {
        List<String> files = listFiles(path);
        List<String> list = new ArrayList<>();
        for(int keyword = 0;keyword<keywordCount;keyword++) {
            String file = randomValue(files);
            List<String> randomLines = getRandomLines(new File(new File(storagePath), file), 1);
            list.addAll(randomLines);
        }
        return list;
    }

    public List<String> getRandomLines(File file, int count) {

        try {
            List<String> lines = FileUtils.readLines(file, "UTF-8");
            List<String> results = new ArrayList<>();

            for(int id = 0;id<count;id++) {
                String line = randomValue(lines);
                results.add(line);
            }

            return results;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Failed to read file {}", file, e);
            return new ArrayList<>();
        }
    }

    private String randomValue(List<String> items) {
        int id = random.nextInt(items.size());
        return items.get(id);
    }


}
