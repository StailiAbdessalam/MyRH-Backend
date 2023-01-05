package com.veinsmoke.myrhbackend.repository.base;

import com.veinsmoke.myrhbackend.entity.superclass.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface UserBaseRepository<T> extends JpaRepository<T, Long> {
    Optional<T> findByEmail(String email);
}
