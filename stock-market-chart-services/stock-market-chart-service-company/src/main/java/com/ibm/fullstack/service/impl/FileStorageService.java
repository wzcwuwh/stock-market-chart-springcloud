package com.ibm.fullstack.service.impl;

import com.ibm.fullstack.file.FileStorageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties){
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectory(this.fileStorageLocation);
        } catch (Exception e) {
            log.error("Could not create the directory where the uploaded files will be stored." + e.getMessage());
        }
    }

    public String storeFile(MultipartFile file){
        //normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try{
            if(fileName.contains("..")){
                log.info("filename contains invalid path sequence " + fileName);
            }
            //copy file to the target location(replacing existing one)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            log.error("could not store file with file name: " + fileName);
        }
        return fileName;
    }

    public Resource loadFileAsResources(String fileName){
        Resource resource = null;

        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            } else {
                log.info("file not found :" + fileName);
            }
        }catch (Exception e){
            log.error("file not found :" + fileName, e);
        }
        return resource;
    }
}
