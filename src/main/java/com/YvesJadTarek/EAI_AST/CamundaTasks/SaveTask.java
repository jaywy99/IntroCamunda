package com.YvesJadTarek.EAI_AST.CamundaTasks;

import com.YvesJadTarek.EAI_AST.model.Supervisor;
import com.YvesJadTarek.EAI_AST.repository.SupervisorRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class SaveTask implements JavaDelegate {

    @Autowired
    private SupervisorRepository repo;

    @Override
    public void execute(DelegateExecution execution) throws Exception{

        List<Supervisor> supervisors = (List<Supervisor>) execution.getVariable("supervisors");

        for(Supervisor supervisor: supervisors) {
            System.out.println(supervisor);
            repo.save(supervisor);
        }
    }
}
