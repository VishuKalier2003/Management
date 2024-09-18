package app.management.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.management.Model.StreamCount;

/*
    The Map Repository is used to access the MongoDB Database and perform CRUD Operations on the streamMap Collection. The instances
    (fields) are of type StreamCount with the Id of the Document being a String.
*/

public interface MapRepository extends MongoRepository<StreamCount, String> {
    
}
