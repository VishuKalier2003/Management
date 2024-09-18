package app.management.AdminServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import app.management.Admin.AdminAccess;
import app.management.Admin.Director;
import app.management.Repository.AdminRepository;
import app.management.Repository.DirectorRepository;

@Service
public class DirectorService {
    @Autowired
    private DirectorRepository directorRepository; // Repository for Directors...

    @Autowired
    private AdminRepository adminRepository; // Repository for Administrators...

    @Autowired
    private AdminService adminService; // Admin Service called...

    public AdminAccess GrantAccess(String adminName)
    {
        try {
            AdminAccess admin = adminService.GetRequiredAdmin(adminName);
            admin.setAccess(true);                  // Access Granted by the Director...
            adminRepository.save(admin);
            return admin;
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Failed to Grant Access to the Director due to error : " + e.getMessage());
            return null;
        }
    }

    public AdminAccess DenyAccess(String adminName)
    {
        try {
            AdminAccess admin = adminService.GetRequiredAdmin(adminName);
            admin.setAccess(false);                 // Access Denied by the Director...
            adminRepository.save(admin);
            return admin;
        }
        catch(Exception error)
        {
            ResponseEntity.badRequest().body("Failed to Deny Access to the Director due to error : " + error.getMessage());
            return null;
        }
    }

    public ResponseEntity<?> AddDirector(Director director) {
        try {
            director.setPassword(director.EncodeKey(director.getPassword())); // Encoding the Password (Encryption)...
            director.setSecurityKey(director.GenerateSecurityKey());
            director.setAccess(true);
            directorRepository.save(director); // Saving to the database...
            return ResponseEntity.ok().body("New Director Assigned Successfully !!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to Assign a New Director!!");
        }
    }

    public List<Director> GetAllDirectors() // * Getting a List of All Directors...
    {
        return directorRepository.findAll();
    }

    public boolean CheckDirector(Director director)
    {
        try
        {
            for(Director directorIter : directorRepository.findAll())
            {
                // If the userName and passwords match together for a single entry...
                if(directorIter.getUserName().equals(director.getUserName()) &&
                        CheckPassword(director.getPassword(), directorIter.getPassword()))
                {
                    return true;
                }
            }
            return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    private boolean CheckPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, encodedPassword);
    }
}
