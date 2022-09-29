package com.fang.meimaijII.utils;

import com.fang.meimaijII.config.FileConfiguration;

public class FileLimitUtil{

    private static long maxSize;

    private static String rootPath;

    private static String dbPath;

    public static void setFileLimitUtil(FileConfiguration fileConfiguration){
        FileLimitUtil.maxSize = fileConfiguration.getMaxSize();
        FileLimitUtil.rootPath = fileConfiguration.getRootPath();
        FileLimitUtil.dbPath = fileConfiguration.getDbPath();
    }

    public static long getMaxSize(){
        return maxSize;
    }

    public static String getRootPath(){
        return rootPath;
    }

    public static String getDbPath(){
        return dbPath;
    }
}
