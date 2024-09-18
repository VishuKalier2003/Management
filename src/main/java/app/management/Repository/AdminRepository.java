package app.management.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.management.Admin.AdminAccess;

public interface AdminRepository extends MongoRepository<AdminAccess, String> {
    // Add custom methods if needed
    
}
