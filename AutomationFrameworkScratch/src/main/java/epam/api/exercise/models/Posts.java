package epam.api.exercise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Posts {

    private int userId;

    private String title;
    private String body;

    //public Posts(){}
    public Posts(@JsonProperty("userId") int userId,
                 @JsonProperty("title") String title,
                 @JsonProperty("body") String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
