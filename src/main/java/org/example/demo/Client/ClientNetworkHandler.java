package org.example.demo.Client;


import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;


public class ClientNetworkHandler {
    private static ClientNetworkHandler clientNetworkHandler;
    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final MediaType JSON = MediaType.get("application/json");
    private final ObjectMapper objectMapper = new ObjectMapper();
    private User currentUser;

    public static ClientNetworkHandler getClientNetworkHandler() {
        if(clientNetworkHandler == null) {
            clientNetworkHandler = new ClientNetworkHandler();
        }
        return clientNetworkHandler;
    }


    private ClientNetworkHandler() {}

    public void login(Form form) throws IOException {
        String json = objectMapper.convertValue(form, String.class); //  create json string from form object
        RequestBody body = RequestBody.create(json,JSON);// set up json body

        Request request = new Request.Builder().url("http://127.0.0.1:8080/login")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        System.out.println(response.body().string());
        response.close();
    }

    public void Register(Form form) {

        String json = objectMapper.convertValue(form, String.class); //  create json string from form object
        RequestBody body = RequestBody.create(json,JSON);// set up json body

        Request request = new Request.Builder().url("http://127.0.0.1:8080/create")
                .post(body)
                .build();
    }


}
