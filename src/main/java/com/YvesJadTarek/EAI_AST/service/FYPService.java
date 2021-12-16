package com.YvesJadTarek.EAI_AST.service;

import com.YvesJadTarek.EAI_AST.model.FYPproject;
import com.YvesJadTarek.EAI_AST.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class FYPService {
    private ProjectRepository repo;
    private String fileBasePath = "/src/main/resources/files";

    @Autowired
    public FYPService(ProjectRepository repo) {
        this.repo = repo;
    }

    public List<FYPproject> getProjects() {
        return repo.findAll();
    }

    public Optional<FYPproject> getProjects(Long id) {
        return repo.findById(id);
    }


    public FYPproject createProject(FYPproject newProject) {

        return repo.save(newProject);

    }





//
//    public ResponseEntity uploadToLocalFileSystem(MultipartFile file) {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        Path path = Paths.get(fileBasePath + fileName);
//        try {
//            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("path")
//                .toUriString();
//        return ResponseEntity.ok(fileDownloadUri);
//    }

}
