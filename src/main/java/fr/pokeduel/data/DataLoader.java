package fr.pokeduel.data;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataLoader<T> {
    private final Class<T> type;

    public DataLoader(Class<T> type) {
        this.type = type;
    }

    public List<T> loadList() {
        Map<String, T> valueMap = getMap();
        return new ArrayList<>(valueMap.values());
    }

    public T loadById(int id) {
        Map<String, T> valueMap = getMap();
        return valueMap.get(String.valueOf(id));
    }

    private Map<String, T> getMap(){
        String filePath = this.getClass().getClassLoader().getResource("json/"+ this.type.getSimpleName().toLowerCase() +".json").getFile();
        ObjectMapper mapper = new ObjectMapper();

        try {
            var mapType = mapper.getTypeFactory()
                    .constructMapType(Map.class, String.class, this.type);

            return mapper.readValue(new File(filePath), mapType);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
