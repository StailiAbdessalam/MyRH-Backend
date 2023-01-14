package com.veinsmoke.myrhbackend.repository;

import com.veinsmoke.myrhbackend.entity.Agent;
import com.veinsmoke.myrhbackend.repository.base.UserBaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource( exported = false )
public interface AgentRepository extends UserBaseRepository<Agent> {
}
