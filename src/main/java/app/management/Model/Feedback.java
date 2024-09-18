package app.management.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "feedback")
public class Feedback
{
    @Id
    private String id;
    @NonNull
    private String roll;
    @NonNull
    private String text;
    @Nonnull
    private int ratings;

    public Feedback(String roll, String text, int ratings)
    {
        this.roll = roll;
        this.text = text;
        this.ratings = ratings;
    }
}
