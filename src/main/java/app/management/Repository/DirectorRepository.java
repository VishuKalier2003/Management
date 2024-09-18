package app.management.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.management.Admin.Director;

public interface DirectorRepository extends MongoRepository<Director, String>
{
    
}
