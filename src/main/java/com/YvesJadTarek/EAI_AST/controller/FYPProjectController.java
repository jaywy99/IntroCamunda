package com.YvesJadTarek.EAI_AST.controller;

import com.YvesJadTarek.EAI_AST.model.FYPproject;
import com.YvesJadTarek.EAI_AST.repository.ProjectRepository;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class FYPProjectController {

    @Autowired
    private ProjectRepository repo;

    private final RuntimeService runtimeService;

    private String fileBasePath = "/src/main/resources/files";

    public FYPProjectController( RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @GetMapping
    public List<FYPproject> getProjects() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<FYPproject> getProjects(@PathVariable Long id) {
        return repo.findById(id);
    }


    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody FYPproject newProject) {

        FYPproject project = repo.save(newProject);

        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }




//
//    @RequestMapping(value="/upload", method=RequestMethod.POST)
//        public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
//                                                     @RequestParam("file") MultipartFile file){
//            if (!file.isEmpty()) {
//                try {
//                    byte[] bytes = file.getBytes();
//                    BufferedOutputStream stream =
//                            new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
//                    stream.write(bytes);
//                    stream.close();
//                    return "You successfully uploaded " + name + " into " + name + "-uploaded !";
//                } catch (Exception e) {
//                    return "You failed to upload " + name + " => " + e.getMessage();
//                }
//            } else {
//                return "You failed to upload " + name + " because the file was empty.";
//            }
//        }



    /**
     * Upload single file using Spring Controller.
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFileHandler(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("user.dir");
                File dir = new File(rootPath + "/src/main/resources/tmpFiles");
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                runtimeService.startProcessInstanceByKey("my-project-process");
                System.out.println("Server File Location=" + serverFile.getAbsolutePath());

                return ResponseEntity.status(HttpStatus.CREATED).body("");
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }



//    @PostMapping("/upload")
//    public ResponseEntity uploadToLocalFileSystem(@RequestParam("file") MultipartFile file) {
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
