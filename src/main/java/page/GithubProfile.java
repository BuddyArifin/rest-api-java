package page;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.module.jsv.JsonSchemaValidator;
import com.jayway.restassured.response.Response;
import javafx.scene.control.Tab;

import java.util.*;

import static org.testng.Assert.*;
import static com.jayway.restassured.path.json.JsonPath.*;

/**
 * Created by buddyarifin on 1/29/17.
 */
public class GithubProfile extends BaseApi{
    private Response response;

    public GithubProfile() {
        this.response = getProfile();
    }

    public void verifyResponse_KeyswithNull(){
        assertEquals(response.path("email"), null);
        assertEquals(response.path("company"), null);
        assertEquals(response.path("bio"), null);
        assertEquals(response.path("hireable"), null);
    }

    public void verify_Response() {
        HashMap<String, ?> jsonarray = from(response.asString()).get("");
        Set<String> keys = jsonarray.keySet();

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("email");
        arrayList.add("company");
        arrayList.add("bio");
        arrayList.add("hireable");

        for (String key : keys ) {
            if (arrayList.contains(key)){
                assertNotNull(true);
            } else {
                assertNotNull(jsonarray.get(key));
            }
        }
    }

    public void verifyResponse_NumOfEntries(){
        HashMap<String, ?> jsonarray = from(response.asString()).get("");
        assertEquals(jsonarray.size(), 30);
    }

    public void verifyResponse_KeyAsInt(){
        assertEquals(response.path("id").getClass(), Integer.class);
        assertEquals(response.path("public_repos").getClass(), Integer.class);
        assertEquals(response.path("public_gists").getClass(), Integer.class);
        assertEquals(response.path("followers").getClass(), Integer.class);
        assertEquals(response.path("following").getClass(), Integer.class);
    }

    public void verifyResponse_KeyAsBoolean(){
        assertEquals(response.path("site_admin").getClass(), Boolean.class);
    }

    public void verifyResponse_ShouldReturn_StatusCode200(){
        assertEquals(200, response.getStatusCode());
    }

    public void verifyResponse_ContentType_ShouldNotNull(){
        assertTrue(response.getContentType().contains("application/json"));
    }

    public void verifyResponse_Cookies_ShouldEmpty(){
        assertTrue(response.getCookies().isEmpty());
    }

    public void verifyResponse_Session_ShouldEmpty(){
        assertNull(response.getSessionId());
    }

    public void verifyResponse_JsonSchema() {
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json-schema.json"));
    }


}
