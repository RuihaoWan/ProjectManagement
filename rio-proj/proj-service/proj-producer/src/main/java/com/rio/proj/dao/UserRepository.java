package com.rio.proj.dao;

import com.rio.proj.entity.Project;
import com.rio.proj.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
