package org.example.demo.Model;

import java.io.Serializable;

public class User implements Serializable {

    private String id;
    private String username;
    private int num_of_wins;

    public User(String id, String username, int num_of_wins) {
        this.id = id;
        this.username = username;
        this.num_of_wins = num_of_wins;
    }

    public User(){}

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getNum_of_wins() {
        return num_of_wins;
    }
    public void setNum_of_wins(int num_of_wins) {
        this.num_of_wins = num_of_wins;
    }

}
