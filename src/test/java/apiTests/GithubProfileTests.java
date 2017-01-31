package apiTests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.GithubProfile;

/**
 * Created by buddyarifin on 1/29/17.
 */
public class GithubProfileTests {
    private GithubProfile githubProfile;

    @BeforeClass
    public void setup() {
        githubProfile = new GithubProfile();
    }

    @Test
    public void test_JsonResponse() {
        githubProfile.verify_Response();
    }

    @Test
    public void test_JsonResponse_Scheme() {
        githubProfile.verifyResponse_JsonSchema();
    }

    // Add Minimum Test

    @Test
    public void test_responseCode_shouldbe200() {
        githubProfile.verifyResponse_ShouldReturn_StatusCode200();
    }

    @Test
    public void test_ResponseKeys_ShouldwithValueInt() {
        githubProfile.verifyResponse_KeyAsInt();
    }

    @Test
    public void test_ResponseKeys_ShouldwithValueBoolean() {
        githubProfile.verifyResponse_KeyAsBoolean();
    }

    @Test
    public void test_ResponseKeys_ShouldwithValueNull() {
        githubProfile.verifyResponse_KeyswithNull();
    }

    @Test
    public void test_ResponseNumberofEntry_Should30() {
        githubProfile.verifyResponse_NumOfEntries();
    }
}
