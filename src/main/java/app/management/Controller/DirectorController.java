package app.management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.management.Admin.AdminAccess;
import app.management.Admin.Director;
import app.management.AdminServices.DirectorService;

@RestController
@RequestMapping("/director")
public class DirectorController
{
    @Autowired
    private DirectorService directorService;                   // Director Service called...

    @PostMapping("/grantAccess/{adminName}")
    public AdminAccess GrantAccess(@PathVariable String adminName) {
        return directorService.GrantAccess(adminName);
    }

    @PostMapping("/denyAccess/{adminName}")
    public AdminAccess DenyAccess(@PathVariable String adminName) {
        return directorService.DenyAccess(adminName);
    }

    @PostMapping("/add")
    public void AddDirector(@RequestBody Director director) {
        directorService.AddDirector(director);
    }

    @PostMapping("/check")
    public boolean CheckDirector(@RequestBody Director director) {
        return directorService.CheckDirector(director);
    }

    @GetMapping("/getAll")
    public List<Director> GetDirectorsList() {
        return directorService.GetAllDirectors();
    }
}
