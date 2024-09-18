package app.management.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.management.Model.Feedback;

public interface FeedbackRepository extends MongoRepository<Feedback, String> {
    
}
