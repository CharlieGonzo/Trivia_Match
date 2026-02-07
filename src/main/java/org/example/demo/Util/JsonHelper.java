package org.example.demo.Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.demo.Model.GameUpdateResponse;
import org.example.demo.Model.User;

import java.io.IOException;
import java.io.Serializable;

public class JsonHelper {

    public static User toUser(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json,User.class);
    }

    public static GameUpdateResponse toResponse(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json,GameUpdateResponse.class);
    }

    public static <T extends Serializable> String JsonToString(T obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
