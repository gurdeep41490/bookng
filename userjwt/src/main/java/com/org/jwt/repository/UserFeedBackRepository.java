package com.org.jwt.repository;

import com.org.jwt.model.UserFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFeedBackRepository extends JpaRepository<UserFeedback, Long> {
}
