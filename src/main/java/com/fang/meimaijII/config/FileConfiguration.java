package com.fang.meimaijII.config;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.fang.meimaijII.utils.FileLimitUtil;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "file")
@Data
public class FileConfiguration{

    //    @Value("${file.maxsize:2000000}")
    private long maxSize;

    //    @Value("${file.rootPath}")
    private String rootPath;

    //    @Value("${file.dbPath}")
    private String dbPath;

    @PostConstruct
    public void init(){
        FileLimitUtil.setFileLimitUtil(this);
    }
    //    private List<String> allowExt = Arrays.asList("jpg", "jpeg", "pdf");
}
