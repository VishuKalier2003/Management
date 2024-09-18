package app.management.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import app.management.Model.StreamCount;
import app.management.Repository.MapRepository;

/*
    The Map Service is used to access the Map Repository and all the possible functionalities with the Map Repository are defined in
    this Service class. This class also has a static Map to store the Data locally to increase the throughput of the Application.
*/

@Service
public class MapService {
    @Autowired // Annotation for Dependency Injection...
    private MapRepository mapRepository; // MongoDB Repository called...

    @Autowired // Autowiring creates ejects the required Services and Dependencies (Dependency Injection)...
    private StreamCount streamCount; // StreamCount Model Class autowired...

    // ! MapService Methods...

    public void UpdateMap(String stream)            //* Function to Update the Map...
    {
        try
        {
            if(mapRepository.findAll().size() == 0)
            {
                streamCount.GenerateNewPair(stream);
                mapRepository.save(streamCount);
            }
            else
            {
                StreamCount existStreamCount = mapRepository.findAll().get(0);
                Map<String, Integer> dataMap = existStreamCount.getStreamMap();
                if(dataMap.containsKey(stream))
                    dataMap.put(stream, dataMap.get(stream) + 1);
                else
                    dataMap.put(stream, 1);
                existStreamCount.CopyMap(dataMap);
                mapRepository.save(existStreamCount);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e);
        }
    }

    public Map<String, Integer> GetMap() {
        List<StreamCount> streamCounts = mapRepository.findAll();

        if (!streamCounts.isEmpty()) {
            return streamCounts.get(0).getStreamMap();
        } else {
            // Handle the case when the list is empty (no elements in the repository)
            // You might want to return an empty map or handle it according to your
            // requirements.
            return Collections.emptyMap();
        }
    }

    public ResponseEntity<?> EmptyMap() // * Function to Empty the Map...
    {
        mapRepository.deleteAll(); // Clearing the Map Repository...
        return ResponseEntity.ok().body("Map Emptied Successfully!!");
    }
}
