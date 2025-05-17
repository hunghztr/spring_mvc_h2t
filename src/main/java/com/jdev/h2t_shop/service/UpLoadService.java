package com.jdev.h2t_shop.service;

import jakarta.servlet.ServletContext;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Component
public class UpLoadService {
    private ServletContext servletContext;

    public UpLoadService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String handleUpLoadFile(MultipartFile file, String target) {
        String finalName = "";
        try {
            byte[] bytes = file.getBytes();

            String rootPath = this.servletContext.getRealPath("/resources/images");
            File dir = new File(rootPath + File.separator + target);
            if (!dir.exists())
                dir.mkdirs();
            // Create the file on server
            finalName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            File serverFile = new File(dir.getAbsolutePath() + File.separator + finalName);
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalName;
    }
}
