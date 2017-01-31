package page;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

/**
 * Created by buddyarifin on 1/29/17.
 */
public class BaseApi {

    public BaseApi() {
        RestAssured.baseURI = Constans.BASE_URL;
        RestAssured.basePath = Constans.USER_PROFILE;
    }

    protected Response get(String path) {
        return RestAssured.get(path);
    }

    protected Response getProfile() {
        return RestAssured.get();
    }
}
