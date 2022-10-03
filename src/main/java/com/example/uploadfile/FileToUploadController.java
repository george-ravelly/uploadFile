package com.example.uploadfile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
public class FileToUploadController {

    private String pathFile;

    public FileToUploadController (@Value("${app.file.path}") String pathFile){
        this.pathFile = pathFile;
    }

    @PostMapping("/file")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile upload){
        FileToUpload arquivo = new FileToUpload();
//        return arquivo.upload("Code/projetos/files", upload.getOriginalFilename(), upload.getInputStream());
        var location = pathFile + UUID.randomUUID() + upload.getOriginalFilename();
        try {
            Files.copy(upload.getInputStream(), Path.of(location), StandardCopyOption.REPLACE_EXISTING);
            return new ResponseEntity<String>("Arquivo carregado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Erro ao carregar arquivo!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
