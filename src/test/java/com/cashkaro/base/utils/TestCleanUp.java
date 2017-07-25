package com.cashkaro.base.utils;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class TestCleanUp {


    //Gets the temp folder property from the System property itself
    public static void clearTempFolder() throws IOException {
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current DIR: "+currentDir);
        String location = currentDir +"/TestDataFiles/Snapshots";
        // String[] location = {String.valueOf(Thread.currentThread().getClass().getResource("/Proposal/")), String.valueOf(Thread.currentThread().getClass().getResource("/Snapshots/"))};
        System.out.println("Current DIR Location: "+location);
        try {

                File file = new File(location);
                FileUtils.cleanDirectory(file);

        } catch (IOException e) {
// Do nothing since we do not worry about the files that cannot be deleted
// Include exception handler logic if you want to
        }
    }
}