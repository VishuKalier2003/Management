package app.management.AdminServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import app.management.Admin.AdminAccess;
import app.management.Repository.AdminRepository;

@Service
public class AdminService {

    @Autowired // Dependency Injection...
    private AdminRepository adminRepository; // Repository for Administrators...

    @Autowired

    public List<AdminAccess> GetAllAdmins() // * Getting a List of All Administrators...
    {
        return adminRepository.findAll();
    }

    // ! Concept of Encryption...
    public ResponseEntity<?> AssignAdmin(AdminAccess admin) // * Assigning a New Administrator...
    {
        try {
            admin.GiveAdminName(admin);
            admin.setPassword(admin.EncodeKey(admin.getPassword())); // Encoding the Password (Encryption)...
            admin.setSecurityKey(admin.GenerateSecurityKey());
            adminRepository.save(admin); // Saving to the database...
            return ResponseEntity.ok().body("New Admin Assigned Successfully !!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to Assign a New Admin!!");
        }
    }

    public boolean CheckIfAdmin(AdminAccess admin) {
        try {
            for (AdminAccess adminIter : adminRepository.findAll()) {
                // If the userName and passwords match together for a single entry...
                if (adminIter.getAdminName().equals(admin.getAdminName()) &&
                        CheckPassword(admin.getPassword(), adminIter.getPassword())) {
                    return true;
                }
            }
            return false;
        } catch (Exception error) {
            return false;
        }
    }

    // Checks if raw password after encoding matches the encoded password...
    private boolean CheckPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, encodedPassword);
    }

    public ResponseEntity<?> DeleteAll() // * Deleting All Administrators...
    {
        try {
            adminRepository.deleteAll(); // Emptying the Database...
            return ResponseEntity.ok().body("All Admins Deleted Successfully !!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to Delete All Admins due to error : " + e.getMessage());
        }
    }

    public AdminAccess GetRequiredAdmin(String name) // * Getting a Required Administrator...
    {
        // This function must be used when we know that the administrator exists,
        // otherwise it will invoke null...
        try {
            for (AdminAccess adminAccess : adminRepository.findAll()) {
                if (adminAccess.getAdminName().equals(name)) // Iterating and getting the administrator...
                    return adminAccess;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean CheckAdminByAdminName(String adminName)
    {
        for(AdminAccess adminAccess : adminRepository.findAll())
        {
            if(adminAccess.getAdminName().equals(adminName))
                return true;
        }
        return false;
    }
}
