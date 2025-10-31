package epam.api.exercise.test.api;

import epam.api.exercise.base.BaseAPI;
import epam.api.exercise.base.BaseResponse;
import epam.api.exercise.clients.PostsClient;
import epam.api.exercise.models.Posts;
import epam.api.exercise.utils.PostsPayloadUtil;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiTest extends BaseAPI {
    private String endPointURI = "https://jsonplaceholder.typicode.com";
    private String endPointPath = "/posts";

    private ValidatableResponse response;

    private PostsClient postsClient = new PostsClient(endPointURI,endPointPath);
    private String endPoint = postsClient.getEndPointURI() + postsClient.getEndPointPath();

    @Test
    public void testGetPosts(){
        response = postsClient.getPosts(endPoint);
        response.log().body();
        response.
                assertThat().
                statusCode(200);
    }

    @Test
    public void testGetPostsId(){
        //String endPointId = endPoint + "/1";
       // response = baseResponse.getResponse(endPointId);
        response = postsClient.getPosts(endPoint + "/1");
        response.log().body();
        response.
            assertThat().
            statusCode(200);
    }

    @Test
    public void testGetPostsWithIdQryParam(){
        //response = baseResponse.getResponse(endPoint,"id",1);
        response = postsClient.getPostsQryParam(endPoint,"id",1);
        response.log().body();
        response.
            assertThat().
            statusCode(200).
            body("[0].userId",equalTo(1)).
            body("[0].id",equalTo(1)).
            body("[0].title",equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
            //body("[0].body",equalTo("quia et suscipit \\n suscipit recusandae consequuntur expedita et cum \\n reprehenderit molestiae ut ut quas totam \\n nostrum rerum est autem sunt rem eveniet architecto"));
    }

    @Test
    public void testGetPostsWithIdAndComments(){
        String endPointIdComments = endPoint + "/1/comments";
        System.out.println(endPointIdComments);

       // response = baseResponse.getResponse(endPointIdComments);
        response = postsClient.getPosts(endPoint + "/1/comments");
        response.log().body();
        response.
            assertThat().
            statusCode(200);

        //extract the response as a list of Maps
        List<Map<String,Object>> comments = response.
            extract().
            jsonPath().
            getList("$");

        // loop through and asset each comment
        for(int i=0;i<comments.size();i++){
            Map<String, Object> comment = comments.get(i);
            System.out.println("Comment # " + (i+1));
            System.out.println("Post ID: " + comment.get("postId"));
            System.out.println("ID: " + comment.get("id"));
            System.out.println("Name: " + comment.get("name"));
            System.out.println("Email: " + comment.get("email"));
            System.out.println("Body:" +comment.get("body"));
            System.out.println("============================================\n");

            // Assert postId is 1
            assertThat("postId should be 1", comment.get("postId"), equalTo(1));
            assertThat("id should not be null", comment.get("id"), notNullValue());
            assertThat("name should not be null", comment.get("name").toString(),allOf(notNullValue(),not(emptyOrNullString())));
            assertThat("Email should be correctly provided",comment.get("email").toString(),containsString("@"));
            assertThat("Body should not be empty", comment.get("body").toString(),allOf(notNullValue(),not(emptyOrNullString())));
        }

    }

    @Test
    public void testCreatePost() throws Exception{
        Posts postPayload = PostsPayloadUtil.loadPostsFromJson();
        System.out.println(endPoint);
        response = postsClient.postPosts(postPayload,endPoint);
        response.
            assertThat().
            statusCode(201).
            body("userId",equalTo(postPayload.getUserId())).
            body("title", equalTo(postPayload.getTitle())).
            body("body",equalTo(postPayload.getBody()));

        //response.log().body();
        //response.log().status();
        System.out.println(response.extract().asString());
    }

    @Test
    public void testUpdatePosts()throws Exception{
        String endPointId = endPoint + "/1";
        int id=1;

        Posts postPayload = PostsPayloadUtil.loadPostsFromJson();
        Posts updatedPostsPayload = PostsPayloadUtil.updatePostsPayload(postPayload,99,"Test 2","This is another test ");

        response = postsClient.putPosts(updatedPostsPayload,endPointId);
        response.
            assertThat().statusCode(200).
                body("userId",equalTo(postPayload.getUserId())).
                body("title", equalTo(postPayload.getTitle())).
                body("body",equalTo(postPayload.getBody())).
                body("id",equalTo(id));

        System.out.println(response.extract().asString());
    }

    @Test
    public void testPatchPost(){
        String endPointId = endPoint + "/1";
        int id=1;

        Posts postPayload = PostsPayloadUtil.loadPostsFromJson();
        Posts updatedPostsPayload = PostsPayloadUtil.updatePostsPayload(postPayload,100,"Test 3","This is another test 2 ");

        response = postsClient.patchPosts(updatedPostsPayload, endPointId);
        response.
            assertThat().statusCode(200).
                body("userId",equalTo(postPayload.getUserId())).
                body("title", equalTo(postPayload.getTitle())).
                body("body",equalTo(postPayload.getBody())).
                body("id",equalTo(id));

        System.out.println(response.extract().asString());
    }

    @Test
    public void testDeletePost(){
        String endPointId = endPoint + "/10";
        System.out.println(endPoint);
        int id=15;

        response = postsClient.deletePosts(endPoint);
        response.log().status();
        response.log().body();


    }

}
