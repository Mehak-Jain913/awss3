package com.s3learn.awss3.controller;

import com.s3learn.awss3.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            s3Service.uploadFile(file);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) {
        byte[] data = s3Service.downloadFile(filename);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + filename + "\"")
                .body(data);
    }
}
