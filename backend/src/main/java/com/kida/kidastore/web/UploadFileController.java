package com.kida.kidastore.web;

import com.kida.kidastore.services.IFileUploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadFileController {
    private IFileUploadService fileUploadService;

    public UploadFileController(IFileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

//    @PostMapping("/file")
//    public String uploadFile(@RequestParam(name = "file") MultipartFile file) {
//        return this.fileUploadService.storeFile(file);
//    }

}
