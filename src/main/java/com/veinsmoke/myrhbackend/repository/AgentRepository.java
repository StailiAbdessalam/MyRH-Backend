package com.veinsmoke.myrhbackend.repository;

import com.veinsmoke.myrhbackend.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
}
