package com.Quiz.Project.service;

import com.Quiz.Project.model.Question;
import com.Quiz.Project.model.QuestionWrapper;
import com.Quiz.Project.model.Quiz;
import com.Quiz.Project.repository.QuestionDAO;
import com.Quiz.Project.repository.QuizDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDAO quizDAO;
    @Autowired
    private QuestionDAO questionDAO;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDAO.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDAO.save(quiz);
        return new ResponseEntity<>("Suceesfully created quiz", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int quizId) {
        Optional<Quiz> quiz = quizDAO.findById(quizId);
        List<Question> questionsFromDAO = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForClient = new ArrayList<>();
        for(Question q: questionsFromDAO){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForClient.add(qw);
        }
        return new ResponseEntity<>(questionsForClient,HttpStatus.OK);
    }
}
