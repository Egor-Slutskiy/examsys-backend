package com.examsys.repository;

import com.examsys.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findAllByUser_id(Long id);
    List<Result> findAllByExam_id(Long id);
}
