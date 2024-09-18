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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Director")
public class Director
{
    @Id
    String id;
    @NonNull
    String userName;
    @NonNull
    String password;
    @Nullable
    String securityKey;         // Encryption key used during Encryption...
    @Nullable
    boolean access;         // Access Allowance...

    public Director(String name, String password) {      // Constructor...
        this.userName = name;
        this.password = password;
        this.access = true;            // Allowed from Start...
    }

    public String GenerateSecurityKey() {       // Generating a random Security Key...
        return java.util.UUID.randomUUID().toString();
    }

    public String EncodeKey(String key) {       // BCrypt Encryption...
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(key);         // The password gets encoded...
    }
}
