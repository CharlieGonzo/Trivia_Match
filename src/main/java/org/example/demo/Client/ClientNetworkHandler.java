package org.example.demo.Client;


import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.example.demo.ApplicationManagement.ApplicationManager;
import org.example.demo.Model.Form;
import org.example.demo.Model.User;
import org.example.demo.Util.JsonHelper;
import org.example.demo.Util.NotificationHelper;
import org.example.demo.Util.SceneHelper;

import java.io.IOException;


public class ClientNetworkHandler {
    private static ClientNetworkHandler clientNetworkHandler;
    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final MediaType JSON = MediaType.get("application/json");
    private final ApplicationManager applicationManager =  ApplicationManager.getInstance();


    public static ClientNetworkHandler getClientNetworkHandler() {
        if(clientNetworkHandler == null) {
            clientNetworkHandler = new ClientNetworkHandler();
        }
        return clientNetworkHandler;
    }


    private ClientNetworkHandler() {}

    public int login(Form form) throws IOException {
        String json = JsonHelper.JsonToString(form);//  create json string from form object
        RequestBody body = RequestBody.create(json,JSON);// set up json body

        Request request = new Request.Builder().url("http://127.0.0.1:8080/api/login")
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();

        int statusCode = response.code();
        if (statusCode == 200) {
            applicationManager.setCurrentUser(response);
        }
        response.close();
        return statusCode;

    }

    public int Register(Form form) throws IOException {
        // api call stuff
        String json = JsonHelper.JsonToString(form); //  create json string from form object
        RequestBody body = RequestBody.create(json,JSON);// set up json body
        Request request = new Request.Builder().url("http://127.0.0.1:8080/api/create")
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();

        return response.code();

    }

    public int updateUser() throws IOException {
        String json = JsonHelper.JsonToString(applicationManager.getCurrentuser());//  create json string from form object
        RequestBody body = RequestBody.create(json,JSON);// set up json body

        Request request = new Request.Builder().url("http://127.0.0.1:8080/api/update")
                .patch(body)
                .build();

        Call call = okHttpClient.newCall(request);

        Response response = call.execute();

        return response.code();
    }


}
