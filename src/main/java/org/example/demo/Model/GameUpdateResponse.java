package org.example.demo.Model;

public class GameUpdateResponse {

    private String gameId;
    private String answer;

    public GameUpdateResponse(String gameId, String answer) {
        this.gameId = gameId;
        this.answer = answer;
    }

    public GameUpdateResponse(){}

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
