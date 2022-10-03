package com.example.uploadfile;

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
    @PostMapping("/file")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile upload) throws IOException {
        FileToUpload arquivo = new FileToUpload();
//        return arquivo.upload("Code/projetos/files", upload.getOriginalFilename(), upload.getInputStream());
        var path = "c:\\Code\\projetos\\files\\";
        var location = path + UUID.randomUUID() + upload.getOriginalFilename();
        try {
            Files.copy(upload.getInputStream(), Path.of(location), StandardCopyOption.REPLACE_EXISTING);
            return new ResponseEntity<String>("Arquivo carregado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Erro ao carregar arquivo!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
