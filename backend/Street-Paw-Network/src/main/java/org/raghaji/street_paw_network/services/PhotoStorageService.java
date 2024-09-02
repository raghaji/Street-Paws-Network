package org.raghaji.street_paw_network.services;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PhotoStorageService {

    @Value("${raghaji.application.upload.path}")
    private String UPLOAD_DIR;

    public String savePhoto(MultipartFile photo) {
        try {
            // Create the upload directory if it doesn't exist
            Files.createDirectories(Paths.get(UPLOAD_DIR));

            // Get the file's original name
            String fileName = photo.getOriginalFilename();

            // Create a file path for saving the photo
            Path filePath = (Paths.get(UPLOAD_DIR)).resolve(fileName);

            // Save the file locally
            Files.write(filePath, photo.getBytes());

            // Return the file URL (could be a relative path, or a full URL if hosted elsewhere)
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save photo", e);
        }
    }
}
