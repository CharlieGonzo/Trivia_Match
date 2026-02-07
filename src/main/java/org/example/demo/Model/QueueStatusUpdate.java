package org.example.demo.Model;



import java.io.Serializable;

public class QueueStatusUpdate implements Serializable {
    private String status;
    private int playersInQueue;
    private String message;

    // 1. CRITICAL: Default constructor for Jackson deserialization
    public QueueStatusUpdate() {
    }

    // 2. Argument constructor for easy use on the server
    public QueueStatusUpdate(String status, int playersInQueue, String message) {
        this.status = status;
        this.playersInQueue = playersInQueue;
        this.message = message;
    }

    // 3. CRITICAL: Getters (Jackson uses these to build the JSON string)
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getPlayersInQueue() { return playersInQueue; }
    public void setPlayersInQueue(int playersInQueue) { this.playersInQueue = playersInQueue; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}

