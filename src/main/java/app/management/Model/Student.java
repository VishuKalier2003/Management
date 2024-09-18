package app.management.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
    The Student Model Class is used to define the properties and behavior the Students will have. Some properties need to be defined
    at the time of the instantiation and initialization of the Student Object, while some properties can be defined later on.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "studentData")       // MongoDB Collection Name...
public class Student {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class CoursePair {       //! Inner Class...
        @NonNull
        private int courseCode;     // Integer Course Code...
        @NonNull
        private String courseName;  // String Course Name...
    }

    //! Data Members...
    @Id
    private String id;
    @NonNull
    private String name;        // Student Name...
    @NonNull
    private String branch;      // Student Branch...
    @NonNull
    private int degree;         // Student Degree...
    @Nullable
    private String rollNumber;      // Student Roll Number (will be Null during initialization)...
    @Nullable
    private List<CoursePair> courses;       // List of Courses (Inner Class used)...
    @NonNull
    private int joiningYear;    // Student Joining Year...
}
