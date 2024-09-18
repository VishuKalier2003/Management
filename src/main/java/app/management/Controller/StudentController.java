package app.management.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.management.Model.Feedback;
import app.management.Model.Student;
import app.management.Service.FeedbackService;
import app.management.Service.MapService;
import app.management.Service.StudentService;

/*
    The Student Controller is used to handle all the requests made to the specified Database. The Controller is responsible for handling
    all the requests and sending the appropriate response back to the client. The Mappings and APIs are defined in this class. The
    required services are called and rendered with the APIs here.
*/

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired // Dependency Injection...
    private StudentService studentService; // Accessing the Student Services for Student Repository...

    @Autowired // Dependency Injection...
    private MapService MapService; // Accessing the Map Services for Map Repository...

    @Autowired
    private FeedbackService feedbackService; // Accessing the Feedback Services for Feedback Repository...

    // ! Get Mapping Requests...

    @GetMapping("/getAll")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Student> getAllStudents() { // Accessing the Student Services...
        return studentService.getAllStudents();
    }

    @GetMapping("/showMap")
    public Map<String, Integer> showMap() { // Accessing the Map Services...
        return MapService.GetMap();
    }

    @GetMapping("/get/{name}/{degree}/{year}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Student GetStudent(@PathVariable String name, @PathVariable int degree, @PathVariable int year) { // Accessing the Student Services...
        return studentService.GetStudent(name, degree, year);
    }

    @GetMapping("/getFeedbacks")
    public List<Feedback> GetFeedbacks() {          // Accessing the Map Services...
        return feedbackService.GetFeedbacks();
    }

    // ! Post Mapping Requests...

    @PostMapping("/add") // The Student Data is bound to the body of the Web Request...
    public ResponseEntity<?> AddStudentData(@RequestBody Student student) {
        return studentService.AddStudentData(student); // Accessing the Student Services...
    }

    @PostMapping("/addAll")
    public ResponseEntity<?> AddAllStudentData(@RequestBody List<Student> studentList) {
        return studentService.AddAllStudentData(studentList); // Accessing the Student Services...
    }

    @PostMapping("/empty")
    public ResponseEntity<?> EmptyStudentData() {
        return studentService.EmptyDatabase(); // Accessing the Student Services...
    }

    @PostMapping("/emptyMap")
    public ResponseEntity<?> EmptyMap() {
        return MapService.EmptyMap(); // Accessing the Map Services...
    }

    @PostMapping("/feedback")
    public ResponseEntity<?> AddFeedback(@RequestBody Feedback feedback) {
        return feedbackService.AddFeedBack(feedback); // Accessing the Feedback Services...
    }
}
