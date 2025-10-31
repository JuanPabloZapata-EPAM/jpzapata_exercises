package epam.api.exercise.base;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseResponse extends BaseAPI{



    public BaseResponse(String URI, String path){
        super(URI,path);
    }

    public RequestSpecification getRequestSpecification(){
        return given().
            header("Content-Type","application/json");
    }

    public RequestSpecification getRequestSpecification(Object obj){
        return given().
            header("Content-Type","application/json").
            body(obj);
    }

    public ValidatableResponse getResponse(String endPoint){
        return given().
            when().
                get(endPoint).
            then();
    }

    public ValidatableResponse getResponse(String endPoint, String parameterName, Object... parameterValues){
        return given().
                queryParam(parameterName,parameterValues).
            when().
                get(endPoint).
            then();
    }

    public ValidatableResponse postResponse(Object payloadBody,String endPoint){
        return this.getRequestSpecification(payloadBody).
            when().
                post(endPoint).
            then();
    }

    public ValidatableResponse putResponse(Object payloadBody,String endPoint){
        return this.getRequestSpecification(payloadBody).
            when().
                put(endPoint).
            then();
    }

    public ValidatableResponse patchResponse(Object payloadBody,String endPoint){
        return this.getRequestSpecification(payloadBody).
            when().
                patch(endPoint).
            then();
    }

    public ValidatableResponse deleteResponse(String endPoint, Object... pathParameters){
        return given().
            when().
                delete(endPoint).
            then();
    }

    public String buildEndPoint(){
        return this.getBaseURI() + this.getBasePath();
    }

}
