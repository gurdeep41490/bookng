package com.org.movies.repositories;

import com.org.movies.model.UserFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFeedBackRepository extends JpaRepository<UserFeedback, Long> {
}
