package com.example.articleapp.util;

import java.io.File;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

// CommandLine Runner
// 애플리케이션 시작될때 특정 로직을 실행하고 싶을때 제공
@Component
@Slf4j
public class DirectoryInitializer implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        File tempDir = new File("/tmp/uploads");
        if (!tempDir.exists()) {
            boolean created = tempDir.mkdir();
            if (created)
                log.info("upload directory created : {}", tempDir.getAbsolutePath());
            else
                log.info("failed to create upload directory {}", tempDir.getAbsolutePath());
        }
    }
}
