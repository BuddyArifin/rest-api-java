package page;

import com.jayway.restassured.module.jsv.JsonSchemaValidator;
import com.jayway.restassured.response.Response;
import net.minidev.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import static org.testng.Assert.*;
import static com.jayway.restassured.path.json.JsonPath.*;
import static org.hamcrest.MatcherAssert.assertThat;
/**
 * Created by buddyarifin on 1/30/17.
 */
public class GithubRepoList extends BaseApi{

    public static final String NAME = "name";
    public static final String ALL_ARRAYPath = "$.[*]";
    public static final String OWNER = "owner";
    private Response response;
    private ArrayList<String> amountOfRepo;
    private JSONArray repositoriList;

    public GithubRepoList() {
        this.response = get("/repos");
        this.amountOfRepo = from(response.asString()).get(NAME);
        this.repositoriList = com.jayway.jsonpath.JsonPath.parse(response.asString()).read(ALL_ARRAYPath);
    }

    public void verify_numberOfRepoEntries() {
        assertEquals(amountOfRepo.size(), 30);
    }

    public void assertionAllResponse() {
        Set<String> keys;
        for (int i = 0 ; i < amountOfRepo.size() ; i ++) {

            // convert all repo details to Maps
            Map<String, Object> repositories = (Map<String, Object>) repositoriList.get(i);
            keys = getAllKeys(repositories);

            for (String key : keys){

                if (repositories.get(key) != null){

                    assertNotNull(repositories.get(key));

                    assertionJSONObjectOwnerKey(repositories, key); // assert owner key as Json Object
                }
            }
        }
    }

    private void assertionJSONObjectOwnerKey(Map<String, Object> repositories, String repoKeys) {
        Set<String> keys;
        if (repoKeys.equalsIgnoreCase(OWNER)){

            // catch owner key then convert to new JSONObject
            Map<String, Object> ownerJsonObjectAsMap = (Map<String, Object>) repositories.get(repoKeys);
            keys = getAllKeys(ownerJsonObjectAsMap);

            for (String key : keys) {

                assertNotNull(ownerJsonObjectAsMap.get(key));
            }
        }
    }

    private Set<String> getAllKeys(Map<String, Object> map) {
        return map.keySet();
    }

    public void verify_Responses_scheme() {
        for (int i = 0 ; i < amountOfRepo.size() ; i++){

            org.json.JSONArray object = new org.json.JSONArray(response.asString());

            assertThat(object.get(i).toString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("repo-scheme.json"));

        }
    }

    public void verifyResponse_ShouldReturn_StatusCode200() {
        assertEquals(200, response.statusCode());
    }

    public void verifyResponse_KeyAsInt() {
        for (int i = 0 ; i < amountOfRepo.size() ; i++){

            org.json.JSONArray object = new org.json.JSONArray(response.asString());
            org.json.JSONObject obj = new JSONObject(object.get(i).toString());

            assertEquals(obj.get("id").getClass(), Integer.class);
            assertEquals(obj.get("stargazers_count").getClass(), Integer.class);
            assertEquals(obj.get("watchers_count").getClass(), Integer.class);
            assertEquals(obj.get("forks_count").getClass(), Integer.class);
            assertEquals(obj.get("open_issues_count").getClass(), Integer.class);
            assertEquals(obj.get("open_issues").getClass(), Integer.class);
            assertEquals(obj.get("watchers").getClass(), Integer.class);
            assertEquals(obj.get("size").getClass(), Integer.class);

        }
    }

    public void verifyResponse_KeyAsBoolean() {
        for (int i = 0 ; i < amountOfRepo.size() ; i++){

            org.json.JSONArray object = new org.json.JSONArray(response.asString());
            org.json.JSONObject obj = new JSONObject(object.get(i).toString());

            assertEquals(obj.get("has_issues").getClass(), Boolean.class);
            assertEquals(obj.get("private").getClass(), Boolean.class);
            assertEquals(obj.get("fork").getClass(), Boolean.class);
            assertEquals(obj.get("has_downloads").getClass(), Boolean.class);
            assertEquals(obj.get("has_wiki").getClass(), Boolean.class);
            assertEquals(obj.get("has_pages").getClass(), Boolean.class);

        }
    }

    public void verifyResponse_OwnerKeyAsJsonObject() {
        for (int i = 0 ; i < amountOfRepo.size() ; i++){

            org.json.JSONArray object = new org.json.JSONArray(response.asString());
            org.json.JSONObject obj = new JSONObject(object.get(i).toString());

            assertEquals(obj.get("owner").getClass(), JSONObject.class);

        }
    }
}
