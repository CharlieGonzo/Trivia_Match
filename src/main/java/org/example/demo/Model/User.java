package org.example.demo.Model;

import java.io.Serializable;

public class User implements Serializable {

    private Long id;
    private String username;
    private int num_of_wins;

    public User(Long id, String username, int num_of_wins) {
        this.id = id;
        this.username = username;
        this.num_of_wins = num_of_wins;
    }

    public User(){}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
