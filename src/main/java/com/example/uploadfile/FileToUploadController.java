package com.example.uploadfile;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.observer.upload.UploadSizeLimit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Controller
public class FileToUploadController {

    @UploadSizeLimit(sizeLimit=40 * 1024 * 1024, fileSizeLimit=50 * 1024 * 1024)
    @PostMapping("/file")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile upload) throws IOException {
        FileToUpload arquivo = new FileToUpload();
        return arquivo.upload("C:\\Code\\projetos\\files", upload.getOriginalFilename(), upload.getInputStream());
    }

    @GetMapping("/teste")
    public String Hello() {
        return "Hello world";
    }
}
