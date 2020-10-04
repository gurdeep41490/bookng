package com.org.booking.repository;

import com.org.booking.model.BookingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingModelRepository extends JpaRepository<BookingModel, Long> {
}
