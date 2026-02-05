package org.example.demo.ApplicationManagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import org.example.demo.Model.User;
import org.example.demo.Util.JsonHelper;

import java.io.IOException;

public class ApplicationManager {

    private static ApplicationManager instance;
    private User currentUser;
    private ObjectMapper mapper = new ObjectMapper();
    public static ApplicationManager getInstance() {
        if (instance == null) {
            instance = new ApplicationManager();
        }
        return instance;
    }

    public void setCurrentUser(Response response) throws IOException {
        currentUser = JsonHelper.toUser(response.body().string());
    }

    public User getCurrentuser(){
        return currentUser;
    }





}
