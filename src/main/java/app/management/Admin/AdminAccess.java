package app.management.Admin;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
  AdminAccess class is used to store the Admin Details in the Database, the Hierarchy descends from the ChairMan to Administrator to
  Users. The ChairMan can edit Admins which in turn can edit Users...
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "admin") // Database where the Admin Details will be stored...
public class AdminAccess {
    @Id
    private String id;
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @Nullable
    private String securityKey;         // Encryption key used during Encryption...
    @Nullable
    private boolean access;         // Access Allowance...
    @Nullable
    private String adminName;           // Name of the Admin (Assigned by the Backend)...

    public AdminAccess(String userName, String password) {      // Constructor...
        this.userName = userName;
        this.password = password;
        this.access = false;            // Denied in Starting...
    }

    public void GiveAdminName(AdminAccess admin)
    {
        String code = GenerateCode();
        admin.setAdminName("Admin" + code);     // Admin Name is assigned...
    }

    public String GenerateCode()
    {
        String d1 = String.valueOf((int)(Math.random() * 10));
        String d2 = String.valueOf((int)(Math.random() * 10));
        String d3 = String.valueOf((int)(Math.random() * 10));
        String d4 = String.valueOf((int)(Math.random() * 10));
        String d5 = String.valueOf((int)(Math.random() * 10));
        int randomFormat = (int)(Math.random() * 3);
        if(randomFormat == 0)
            return d1 + d3 + d2 + d5 + d4;
        else if(randomFormat == 1)
            return d2 + d4 + d1 + d3 + d5;
        else
            return d3 + d5 + d4 + d2 + d1;
    }

    public String GenerateSecurityKey() {       // Generating a random Security Key...
        return java.util.UUID.randomUUID().toString();
    }

    // ! Use Spring-Security-Crypto version 5.7.0 as the lastest-release for 2023...
    public String EncodeKey(String key) {       // BCrypt Encryption...
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(key);         // The password gets encoded...
    }
}
