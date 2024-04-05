package org.example.fileexample;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Controller
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${zip.password}")
    private String zipPassword;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Create a unique filename
            String fileName = UUID.randomUUID().toString() + ".txt";
            String filePath = uploadDir + File.separator + fileName;

            // Save the uploaded file
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath);
            Files.write(path, bytes);

            // Convert the file to a zip
            String zipFilePath = uploadDir + File.separator + fileName + ".zip";
            ZipFile zipFile = new ZipFile(zipFilePath,zipPassword.toCharArray());

            ZipParameters zipParameters = new ZipParameters();
            zipParameters.setCompressionMethod(net.lingala.zip4j.model.enums.CompressionMethod.DEFLATE);
            zipParameters.setCompressionLevel(net.lingala.zip4j.model.enums.CompressionLevel.NORMAL);
            zipParameters.setEncryptFiles(true);
            zipParameters.setEncryptionMethod(net.lingala.zip4j.model.enums.EncryptionMethod.ZIP_STANDARD);

            zipFile.addFile(new File(filePath), zipParameters);

            return ResponseEntity.ok().body("File uploaded and converted to ZIP successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to upload file: " + e.getMessage());
        }
    }
}

