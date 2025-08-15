package com.splitmaster.com.repository;

import com.splitmaster.com.domain.UserAuthModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserAuthModel, Long> {
    @Query("select u from UserAuthModel u where u.username = :username")
    UserAuthModel getUserDetailsByUsername(String username);
}
