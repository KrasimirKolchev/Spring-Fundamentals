package com.judgever2.repositories;

import com.judgever2.models.entities.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, String> {
    List<Homework> getAllByAuthor_Id(String authorId);
}
