package apiTests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.GithubRepoList;

/**
 * Created by buddyarifin on 1/30/17.
 */
public class GithubRepoListTest {
    private GithubRepoList githubRepoList;

    @BeforeClass
    public void setup() {
        githubRepoList = new GithubRepoList();
    }

    @Test
    public void test_JsonResponse() {
        githubRepoList.assertionAllResponse();
    }

    @Test
    public void test_JsonResponse_Scheme() {
        githubRepoList.verify_Responses_scheme();
    }

    // add minimum test

    @Test
    public void test_responseCode_shouldbe200() {
        githubRepoList.verifyResponse_ShouldReturn_StatusCode200();
    }

    @Test
    public void test_ResponseKeys_ShouldwithValueInt() {
        githubRepoList.verifyResponse_KeyAsInt();
    }

    @Test
    public void test_ResponseKeys_ShouldwithValueBoolean() {
        githubRepoList.verifyResponse_KeyAsBoolean();
    }

    @Test
    public void test_ResponseKeys_OwnerKeyAsJsonObject() {
        githubRepoList.verifyResponse_OwnerKeyAsJsonObject();
    }

    @Test
    public void test_AmountOfRepository_ShouldBe30() {
        githubRepoList.verify_numberOfRepoEntries();
    }
}
