package com.splitmaster.com.repository;

import com.splitmaster.com.domain.UserGroupAssociation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupAssociationRepository extends JpaRepository<UserGroupAssociation, Long> {
}
