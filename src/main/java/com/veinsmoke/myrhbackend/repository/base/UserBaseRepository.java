package com.veinsmoke.myrhbackend.repository.base;

import com.veinsmoke.myrhbackend.entity.superclass.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBaseRepository<T> extends JpaRepository<T, Long> {
    Optional<T> findByEmail(String email);
}
