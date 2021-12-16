package com.YvesJadTarek.EAI_AST.controller;


import com.YvesJadTarek.EAI_AST.model.Supervisor;
import com.YvesJadTarek.EAI_AST.repository.SupervisorRepository;
import com.YvesJadTarek.EAI_AST.service.Parser_Service;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/supervisors")
public class SupervisorController {


    private SupervisorRepository supervisorRepository;
    private Parser_Service parser;

    @Autowired
    public SupervisorController(SupervisorRepository supervisorRepository, Parser_Service parser) {
        this.supervisorRepository = supervisorRepository;
        this.parser = parser;
    }

    @PostMapping
    public ResponseEntity<?> createSupervisor(@RequestBody Supervisor supervisor) {
        Supervisor supervisor1 = supervisorRepository.save(supervisor);

        return ResponseEntity.status(HttpStatus.CREATED).body(supervisor1);
    }

//    @PostMapping("/test")
//    public void createSupervisor() throws Exception {
//        parser.parse();
//
//    }
}

