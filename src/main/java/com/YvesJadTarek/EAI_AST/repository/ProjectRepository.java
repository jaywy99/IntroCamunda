package com.YvesJadTarek.EAI_AST.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.YvesJadTarek.EAI_AST.model.FYPproject;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends JpaRepository<FYPproject, Long> {
}
