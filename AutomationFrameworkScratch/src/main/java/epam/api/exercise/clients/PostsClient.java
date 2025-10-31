package epam.api.exercise.clients;

import epam.api.exercise.base.BaseResponse;
import epam.api.exercise.models.Posts;
import io.restassured.response.ValidatableResponse;

public class PostsClient {
    private String endPointURI;
    private String endPointPath;

    protected BaseResponse baseResponse = new BaseResponse(endPointURI,endPointPath);
    private String endPoint = baseResponse.buildEndPoint();
    private ValidatableResponse response;

    public PostsClient(){}
    public PostsClient(String endPointURI, String endPointPath){
        this.endPointURI=endPointURI;
        this.endPointPath=endPointPath;
    }

    public String getEndPointURI() {
        return endPointURI;
    }

    public void setEndPointURI(String endPointURI) {
        this.endPointURI = endPointURI;
    }

    public String getEndPointPath() {
        return endPointPath;
    }

    public void setEndPointPath(String endPointPath) {
        this.endPointPath = endPointPath;
    }

    public ValidatableResponse getPosts(String endPoint){
        return baseResponse.getResponse(endPoint);
    }

    public ValidatableResponse getPostsQryParam(String endPoint, String parameterName, Object... parameterValues){
        return baseResponse.getResponse(endPoint,parameterName,parameterValues);
    }

    public ValidatableResponse postPosts(Posts postsPayload, String endPoint){
        return baseResponse.postResponse(postsPayload,endPoint);
    }

    public ValidatableResponse putPosts(Posts postsPayload, String endPoint){
        return baseResponse.putResponse(postsPayload,endPoint);
    }

    public ValidatableResponse patchPosts(Posts postsPayload, String endPoint){
        return baseResponse.patchResponse(postsPayload,endPoint);
    }

    public ValidatableResponse deletePosts(String endPoint, Object... parameterValues){
        return baseResponse.deleteResponse(endPoint, parameterValues);
    }
}
