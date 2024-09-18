package app.management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.management.Admin.AdminAccess;
import app.management.AdminServices.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService; // Admin Service called...

    // ! Get Mapping Requests...
    @GetMapping("/getAll")
    public List<AdminAccess> GetAllAdmins() // Getting a List of all Current Administrators...
    {
        return adminService.GetAllAdmins();
    }

    @GetMapping("/get/{name}") // Path variable annotation used...
    public AdminAccess GetRequiredAdmin(@PathVariable String name) {
        return adminService.GetRequiredAdmin(name); // Getting a Required Administrator...
    }

    // ! Post Mapping Requests...
    @PostMapping("/add")
    public ResponseEntity<?> AssignAdmin(@RequestBody AdminAccess admin) // Admin is passed as a Web Request Body...
    {
        return adminService.AssignAdmin(admin);
    }

    @PostMapping("/check") // Checking if the User is an Administrator...
    public boolean CheckIfAdmin(@RequestBody AdminAccess admin) {
        return adminService.CheckIfAdmin(admin);
    }

    @PostMapping("/check/{adminName}")
    public boolean CheckIfAdmin(@PathVariable String adminName) {
        return adminService.CheckAdminByAdminName(adminName);
    }

    // ! Delete Mapping Requests...
    @DeleteMapping("/deleteAll") // Deleting all Administrators...
    public ResponseEntity<?> DeleteAll() {
        return adminService.DeleteAll();
    }
}
