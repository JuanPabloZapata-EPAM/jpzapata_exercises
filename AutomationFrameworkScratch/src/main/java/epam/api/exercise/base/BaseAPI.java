package epam.api.exercise.base;

public abstract class BaseAPI {
    private String baseURI;
    private String basePath;

    public BaseAPI(){}
    public BaseAPI(String baseURI, String basePath){
        this.baseURI=baseURI;
        this.basePath=basePath;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getBaseURI() {
        return baseURI;
    }

    public void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
    }

}
