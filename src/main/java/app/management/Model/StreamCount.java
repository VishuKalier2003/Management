package app.management.Model;

import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.mongodb.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
    The StreamCount Model Class is used to define the properties of the StreamCount Document. Its main functionality is to keep track
    of the number of students in each stream, which be further be exploited to generate a unique Roll Number for each student.
*/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Document(collection = "streams")
public class StreamCount {
    @Id
    private String id; // Id of the Document...
    @Nullable
    private Map<String, Integer> streamMap = new HashMap<>(); // Storing the Stream and its Count as Instances...

    public void GenerateNewPair(String value) {     // Function to Generate a new Pair...
        if(streamMap == null) {
            streamMap = new HashMap<>();
        }
        streamMap.put(value, 1);
    }

    public void CopyMap(Map<String, Integer> newMap) {      // Function to Copy the Map...
        if(streamMap == null) {
            streamMap = new HashMap<>();
        }
        streamMap = newMap;
    }
}
