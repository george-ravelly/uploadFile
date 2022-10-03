package com.example.uploadfile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@Controller
@RequestMapping("uploadFile")
public class Arquivo {
    @PostMapping
    public void upload (String nameDirector, String nameFile, InputStream inputFile) throws FileNotFoundException {
        String fileLocation = nameDirector + "/" + nameFile;
        File newFile = new File(fileLocation);
        FileOutputStream outputFile = new FileOutputStream(newFile);
        try {
            copyFile(inputFile, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyFile (InputStream origin, OutputStream destiny) throws IOException {
        int bite = 0;
        byte[] maxTam = new byte[(1024*1024)*10];
        while ((bite = origin.read(maxTam)) >= 0) {
            destiny.write(maxTam, bite, 0);
        }
    }
}
