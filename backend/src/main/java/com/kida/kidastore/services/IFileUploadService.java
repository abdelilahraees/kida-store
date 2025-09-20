package com.kida.kidastore.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IFileUploadService {
    String storeFile(MultipartFile file);
}
