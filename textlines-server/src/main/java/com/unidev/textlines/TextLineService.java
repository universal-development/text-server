package com.unidev.textlines;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.DirectoryScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class TextLineService {

    @Value("${textline.storage:storage}")
    private String storagePath;

    private Map<String, List<String>> fileContentCache = new ConcurrentHashMap<>();

    private Random random = new Random();

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Scheduled(fixedDelay = 30000)
    @PostConstruct
    public void populateCache() {
        Map<String, List<String>> newCache = new ConcurrentHashMap<>();
        DirectoryScanner scanner = new DirectoryScanner();
        scanner.setBasedir(storagePath);
        scanner.scan();
        log.info("Loading files from {}", storagePath);
        for (String file : scanner.getIncludedFiles()) {
            log.info("Loading {}", file);
            try {
                newCache.put(file, new ArrayList<>(new HashSet<>(FileUtils.readLines(new File(storagePath, file), Charset.defaultCharset()))));
            } catch (IOException e) {
                log.warn("Failed to read file {}", file, e);
            }
        }
        fileContentCache = newCache;
    }

    public List<String> listFiles(String path) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : fileContentCache.entrySet()) {
            if ( antPathMatcher.match(path, entry.getKey())) {
                list.add(entry.getKey());
            }
        }
        return list;
    }

    public Collection<String> randomLines(String path, int keywordCount) {
        List<String> files = listFiles(path);
        List<String> list = new ArrayList<>();
        for (int keyword = 0; keyword < keywordCount; keyword++) {
            String file = randomValue(files);
            List<String> randomLines = getRandomLines(file, 1);
            list.addAll(randomLines);
        }
        return list;
    }

    public List<String> getRandomLines(String file, int count) {
        try {
            List<String> lines = fileContentCache.get(file);
            List<String> results = new ArrayList<>();
            for (int id = 0; id < count; id++) {
                String line = randomValue(lines);
                results.add(line);
            }
            return results;
        } catch (Exception e) {
            log.error("Failed to read file {}", file, e);
            return new ArrayList<>();
        }
    }

    private String randomValue(List<String> items) {
        int id = random.nextInt(items.size());
        return items.get(id);
    }


}
