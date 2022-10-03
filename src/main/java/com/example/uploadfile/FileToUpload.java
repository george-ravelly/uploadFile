package com.example.uploadfile;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileToUpload {
    public ResponseEntity<String> upload (String nameDirector, String nameFile, InputStream inputFile) throws FileNotFoundException {
        String fileLocation = nameDirector + "/" + nameFile;
        File newFile = new File(fileLocation);
        FileOutputStream outputFile = new FileOutputStream(newFile);
        try {
            Files.copy(inputFile, Path.of(fileLocation), StandardCopyOption.REPLACE_EXISTING);
            return new ResponseEntity<String>("Arquivo carregado com sucesso!", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Erro ao carregar arquivo!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
