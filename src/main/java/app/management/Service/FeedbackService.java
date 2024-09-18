package app.management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import app.management.Model.Feedback;
import app.management.Repository.FeedbackRepository;

import java.util.*;

@Service
public class FeedbackService
{
    @Autowired
    private FeedbackRepository feedbackRepository;

    public ResponseEntity<?> AddFeedBack(Feedback feedback)
    {
        try
        {
            feedbackRepository.save(feedback);
            return ResponseEntity.ok().body("Feedback Added Successfully!!");
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("Error Occurred during Feedback Addition : " + e);
        }
    }

    public List<Feedback> GetFeedbacks()
    {
        return feedbackRepository.findAll();
    }
}
