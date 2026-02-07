package org.example.demo.Client;


import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.example.demo.ApplicationManagement.ApplicationManager;
import org.example.demo.Model.Form;
import org.example.demo.Model.GameUpdateResponse;
import org.example.demo.Model.QueueStatusUpdate;
import org.example.demo.Model.User;
import org.example.demo.Util.JsonHelper;
import org.example.demo.Util.NotificationHelper;
import org.example.demo.Util.SceneHelper;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.io.IOException;
import java.lang.reflect.Type;


public class ClientNetworkHandler {
    private static ClientNetworkHandler clientNetworkHandler;
    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final MediaType JSON = MediaType.get("application/json");
    private final ApplicationManager applicationManager =  ApplicationManager.getInstance();
    private final WebSocketStompClient client = new WebSocketStompClient(new StandardWebSocketClient());;


    public static ClientNetworkHandler getClientNetworkHandler() {
        if(clientNetworkHandler == null) {
            clientNetworkHandler = new ClientNetworkHandler();


        }
        return clientNetworkHandler;
    }


    private ClientNetworkHandler() {

        client.setMessageConverter(new MappingJackson2MessageConverter());
    }

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

            client.connect("ws://localhost:8080/ws", new StompSessionHandlerAdapter() {
                @Override
                public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
                    System.err.println("STOMP Error: " + exception.getMessage());
                    exception.printStackTrace();
                }

                @Override
                public void handleTransportError(StompSession session, Throwable exception) {
                    System.err.println("Transport Error: " + exception.getMessage());
                }

                @Override
                public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                    System.out.println("Connected");
                    System.out.println(applicationManager.getCurrentuser().getId());
                    session.subscribe("/topic/player/" + applicationManager.getCurrentuser().getId(), new StompFrameHandler() {
                        @Override
                        public Type getPayloadType(StompHeaders headers) {
                            return QueueStatusUpdate.class;
                        }

                        @Override
                        public void handleFrame(StompHeaders headers, Object payload) {
                            System.out.println(payload);
                            System.out.println("found match!");
                        }

                    });
                }
            });

//            client.connect("ws://localhost:8080/ws", new StompSessionHandlerAdapter() {
//                @Override
//                public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
//                    System.err.println("STOMP Error: " + exception.getMessage());
//                    exception.printStackTrace();
//                }
//
//                @Override
//                public void handleTransportError(StompSession session, Throwable exception) {
//                    System.err.println("Transport Error: " + exception.getMessage());
//                }
//
//                @Override
//                public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
//                    System.out.println("Connected");
//                    session.send("/app/queue/"+ applicationManager.getCurrentuser().getId(),null); // send to server
//
//                }
//            });





        }

        response.close();
        return statusCode;
    }

    public void joinQueue(){
        client.connect("ws://localhost:8080/ws", new StompSessionHandlerAdapter() {
                @Override
                public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
                    System.err.println("STOMP Error: " + exception.getMessage());
                    exception.printStackTrace();
                }

                @Override
                public void handleTransportError(StompSession session, Throwable exception) {
                    System.err.println("Transport Error: " + exception.getMessage());
                }

                @Override
                public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                    System.out.println("Connected");
                    session.send("/app/queue/"+ applicationManager.getCurrentuser().getId(),null); // send to server

                }
            });
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
