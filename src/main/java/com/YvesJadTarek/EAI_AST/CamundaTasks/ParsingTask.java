package com.YvesJadTarek.EAI_AST.CamundaTasks;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import com.YvesJadTarek.EAI_AST.model.Supervisor;
import com.YvesJadTarek.EAI_AST.repository.SupervisorRepository;
import com.YvesJadTarek.EAI_AST.service.Parser_Service;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

public class ParsingTask implements JavaDelegate {

    private List<Supervisor> supervisors;

    @Override
    public void execute(DelegateExecution execution) throws Exception{
        supervisors = new ArrayList<Supervisor>();
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
                                supervisors.add(supervisor);
                                // repo.save(supervisor);
                                i++;
                            }

                        }
                    }

                }
            }



        } catch (Exception excep) {
            excep.printStackTrace();
        }
        execution.setVariable("supervisors", supervisors);
    }
}
