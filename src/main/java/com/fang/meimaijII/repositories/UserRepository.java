package com.fang.meimaijII.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fang.meimaijII.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    List<User> findByRoleId(Long id);
    
    Optional<User> findByAccount(String account);
    
    Optional<User> findByRefreshToken(String refreshToken);
    
    Optional<User> findByUuid(String uuid);
}
