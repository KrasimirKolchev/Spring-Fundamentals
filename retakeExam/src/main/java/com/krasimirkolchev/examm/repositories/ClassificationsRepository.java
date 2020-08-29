package com.krasimirkolchev.examm.repositories;

import com.krasimirkolchev.examm.models.entities.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationsRepository extends JpaRepository<Classification, String> {
}
