package com.YvesJadTarek.EAI_AST.service;

import java.io.*;
import java.util.*;
import com.YvesJadTarek.EAI_AST.model.Supervisor;
import com.YvesJadTarek.EAI_AST.repository.SupervisorRepository;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Parser_Service {
    private SupervisorRepository repo;
//    private List<Supervisor> supervisors;

    @Autowired
    public Parser_Service(SupervisorRepository repo) {
        this.repo = repo;
    }
    public void parse(DelegateExecution delegateExecution) {
        //supervisors = new ArrayList<Supervisor>();
        WordExtractor extractor = null;
        try {
            File tmpFiles = new File(System.getProperty("user.dir") + "/src/main/resources/tmpFiles");
            File[] directoryListing = tmpFiles.listFiles();
            if (directoryListing != null) {
                for (File child : directoryListing) {

                    FileInputStream fis = new FileInputStream(child);
                    HWPFDocument document = new HWPFDocument(fis);
                    extractor = new WordExtractor(document);
                    String[] fileData = extractor.getParagraphText();


                    for (int i = 0; i < fileData.length; i++) {
                        if (fileData[i] != null &&
                                (fileData[i].contains("Supervisor"))    // Search by Title
                        ) {
                            while (!fileData[i].contains("Co-supervisor")) {
                                String firstname = "";
                                String lastname = "";
                                String email = "";
                                if (fileData[i+1].contains("Name")) {       // Search by Field
                                    String[] words = fileData[i+1].split(" ");
                                    for (int j = 0; j < words.length; j++) {
                                        if (words[j].contains("Name")) {
                                            firstname = words[j+1];
                                            // System.out.println(words[j+1]);
                                        }
                                    }
                                }
                                if (fileData[i+1].contains("Name")) {       // Search by Field
                                    String[] words = fileData[i+1].split(" ");
                                    for (int j = 0; j < words.length; j++) {
                                        if (words[j].contains("Name")) {
                                            lastname = words[j+2];
                                            //System.out.println(words[j+2]);
                                        }
                                    }
                                }
                                if (fileData[i+1].contains("@")) {       // Search by Field
                                    String[] words = fileData[i+1].split(" ");
                                    for (int j = 0; j < words.length; j++) {
                                        if (words[j].contains("@")) {
                                            email =  words[j];
                                            //System.out.println(words[j]);
                                        }
                                    }
                                }
                                Supervisor supervisor = new Supervisor();
                                supervisor.setFirstName(firstname);
                                supervisor.setLastName(lastname);
                                supervisor.setEmail(email);
                                delegateExecution.setVariable("name", "Jad");
//                                supervisors.add(supervisor);
                                repo.save(supervisor);
                                i++;
                            }

                        }
                    }

                }
            }



        } catch (Exception excep) {
            excep.printStackTrace();
        }
//        return supervisors;
    }
}

