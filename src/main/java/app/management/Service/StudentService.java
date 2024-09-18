package app.management.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import app.management.Model.Student;
import app.management.Repository.StudentRepository;

/*
    The Student Service is used to access the Student Repository and all the possible functionalities with the Student Repository are
    defined in this Service class.
*/

@Service
public class StudentService {
    @Autowired // Dependency Injection...
    private StudentRepository studentRepository; // Autowiring the Student Database...

    @Autowired
    private MapService mapService; // Calling another Service in here...

    // ! Student Model Services...

    public List<Student> getAllStudents()          // * Function to Get the Entire Student Data...
    {
        return studentRepository.findAll(); // Return the Entire Student Data...
    }

    public Student GetStudent(String name, int degree, int year)        // *  Function to get a Single Student...
    {
        for(Student student: studentRepository.findAll())
        {
            if(student.getName().equals(name) && student.getDegree() == degree && student.getJoiningYear() == year)
                return student;
        }
        return null;
    }

    public ResponseEntity<?> AddStudentData(Student student)            // * Function to Add Student Data...
    {
        try
        {
            studentRepository.save(student);
            student.setRollNumber(GenerateRollNumber(student.getId())); // Generating the Roll Number (Function Call)...
            studentRepository.save(student); // Save the Student Data...
            
            return ResponseEntity.ok("Student data added successfully...");
        } catch (Exception e) // Exception Handling...
        {
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    public ResponseEntity<?> AddAllStudentData(List<Student> studentList)           // * Function to Add All Student Data...
    {
        try {
            studentRepository.saveAll(studentList);
            for (Student student : studentList) // Iterating every Student in the List...
            {
                String stream = String.valueOf(student.getJoiningYear()).substring(2)
                + (student.getDegree() == 1 ? "M" : "B"); // Generating the Stream...
                switch(student.getBranch()) // Switch Case for Branch...
                {
                    case "AI":
                        stream += "AI";
                        break;
                    case "computerScience":
                        stream += "CS";
                        break;
                    case "mechanical":
                        stream += "ME";
                        break;
                    case "civil":
                        stream += "CE";
                        break;
                    case "electrical":
                        stream += "EE";
                        break;
                    case "bioTechnology":
                        stream += "BT";
                        break;
                    case "cyber":
                        stream += "CY";
                        break;
                    case "aeronautics":
                        stream += "AE";
                        break;
                    default: // Error Handling...
                        return ResponseEntity.badRequest().body("Invalid Branch Provided...");
                }
                mapService.UpdateMap(stream); // Updating the Map...
                student.setRollNumber(GenerateRollNumber(student.getId())); // Generating the Roll Number...
            }
            studentRepository.saveAll(studentList); // Saving the Student Data...
            return ResponseEntity.ok("All Student Data Added Successfully...");
        } catch (Exception e) { // Exception Handling...
            return ResponseEntity.badRequest().body("Error: " + e);
        }
    }

    public String GenerateRollNumber(String id) // * Function to Generate Roll Number...
    {
        String rollNumber = "";
        try {
            Student student = studentRepository.findById(id).get(); // Get the Student Data...
            rollNumber = String.valueOf(student.getJoiningYear()).substring(2);
            rollNumber = rollNumber + (student.getDegree() == 1 ? "M" : "B");
            switch (student.getBranch()) {
                case "AI":
                    rollNumber += "AI";
                    break;
                case "computerScience":
                    rollNumber += "CS";
                    break;
                case "mechanical":
                    rollNumber += "ME";
                    break;
                case "civil":
                    rollNumber += "CE";
                    break;
                case "electrical":
                    rollNumber += "EE";
                    break;
                case "bioTechnology":
                    rollNumber += "BT";
                    break;
                case "cyber":
                    rollNumber += "CY";
                    break;
                case "aeronautics":
                    rollNumber += "AE";
                    break;
                default: // Error Handling...
                    return "Invalid Branch Provided...";
            }
            mapService.UpdateMap(rollNumber);
            int count = mapService.GetMap().get(rollNumber) + 1000;
            rollNumber = rollNumber + String.valueOf(count);
            return rollNumber;
        } catch (Exception e) { // Exception Handling...
            return e.toString();
        }
    }

    public ResponseEntity<?> EmptyDatabase()                       // * Function to Empty the Database...
    {
        studentRepository.deleteAll();
        return ResponseEntity.ok("Database Emptied Successfully...");
    }
}
