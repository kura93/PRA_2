import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import model.Address;
import model.Doctor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by Kura on 19.11.2017.
 */
public class Serialization {

    private ObjectMapper jsonMapper;
    private ObjectMapper xmlMapper;

    public Serialization() {
        this.jsonMapper = new ObjectMapper();
        this.xmlMapper = new XmlMapper();

        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        jsonMapper.registerModule(new JodaModule());
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.registerModule(new JodaModule());
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public <T> void serializeDataToJson(List<T> data, String file) throws IOException {
        jsonMapper.writeValue(new File("./src/main/resources/"+file), data);
        String jsonString = jsonMapper.writeValueAsString(data);
        System.out.println(jsonString);
    }

    public <T> void serializeDataToXml(List<T> data, String file ) throws IOException {
        xmlMapper.writeValue(new File("./src/main/resources/"+file), data);
        String jsonString = xmlMapper.writeValueAsString(data);
        System.out.println(jsonString);
    }

    public <T> List<T> deserializeFromXml(String fileName, Class<T> tClass) throws IOException {
        List<T> res;
        try {
            CollectionType listType = xmlMapper.getTypeFactory().
                    constructCollectionType(List.class, tClass);
            InputStream inputStream = Serialization.class.getClassLoader().
                    getResourceAsStream(fileName);
            res = xmlMapper.readValue(inputStream, listType);
            return res;
        } catch (Throwable exc) {
            System.out.println("Niepoprawny format pliku xml lub brak pliku" + exc);
            res = new ArrayList<T>();
            return res;
        }
    }

    public <T> List<T> deserializeFromJson(String fileName, Class<T> tClass) throws IOException {
        List<T> res;
        try {
            CollectionType listType = jsonMapper.getTypeFactory().
                    constructCollectionType(List.class, tClass);
            InputStream inputStream = Serialization.class.getClassLoader().
                    getResourceAsStream(fileName);
            res = jsonMapper.readValue(inputStream, listType);
            return res;
        } catch (Throwable exc) {
            System.out.println("Niepoprawny format pliku json lub brak pliku" + exc);
            res = new ArrayList<T>();
            return res;
        }
    }

}
