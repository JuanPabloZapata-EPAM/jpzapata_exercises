package epam.api.exercise.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import epam.api.exercise.models.Posts;

import java.io.File;
import java.io.IOException;

public class PostsPayloadUtil {

    private static final String DEFAULT_JSON_PATH = "src/test/resources/PostsPayload.json";

    public static Posts loadPostsFromJson() {
        try{
            ObjectMapper objMap = new ObjectMapper();
            return objMap.readValue(new File(DEFAULT_JSON_PATH), Posts.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Posts loadPostsFromJson(String path){
        try{
            ObjectMapper objMap = new ObjectMapper();
            return objMap.readValue(new File(path), Posts.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Posts updatePostsPayload(Posts post, Integer userId, String title, String body){
        if(userId != null) post.setUserId(userId);
        if(title != null) post.setTitle(title);
        if(body != null) post.setBody(body);
        return post;
    }
}
