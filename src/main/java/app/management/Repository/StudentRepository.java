package app.management.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.management.Model.Student;

/*
    The Student Repository is used to access the MongoDB Database and perform CRUD Operations on the studentData Collection. The 
    instances (fields) are of type Student with the Id of the Document being a String.
*/

public interface StudentRepository extends MongoRepository<Student, String>
{
    
}
