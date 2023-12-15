package com.example.officebooking.adapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.net.URL;

public class MDhelper {
    public static String downloadFile(String fileName) {
        String baseUrl = "https://file.goffice.fun/d/opt/Goffice/MD_File/";
        String targetDirectory = "/Users/apple/Desktop/Untitled/OfficeBooking/app/src/main/assets/";

        try {
            URL fileUrl = new URL(baseUrl + fileName + ".md");
            Path targetPath = Paths.get(targetDirectory + fileName + ".md");
            Files.createDirectories(targetPath.getParent());
            Files.copy(fileUrl.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            return targetPath.toAbsolutePath().toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error downloading file";
        }
    }
/* 
    public static void main(String[] args) {
        String localFilePath = downloadFile("text");
        System.out.println("Downloaded file path: " + localFilePath);
    }
*/
}