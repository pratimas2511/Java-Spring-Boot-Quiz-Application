package com.Quiz.Project.repository;

import com.Quiz.Project.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  interface QuestionDAO extends JpaRepository<Question,Integer> {
     List<Question> findByCategory(String category);

     @Query(value = "SELECT * FROM question q where q.category = :category limit :numQ",nativeQuery = true)
     List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
