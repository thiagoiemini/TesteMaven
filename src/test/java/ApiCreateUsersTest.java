import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.json.simple.JSONObject;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class ApiCreateUsersTest extends ApiRestAssuredTest{

    @BeforeClass
    public static void setup() {
        ApiRestAssuredTest.setup();
        ApiRestAssuredTest.setBasePath("/api/users");
    }

    @Test
    public void POST_Users_DeveCriarNameEJobComSucesso_Test() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Thiago");
        requestParams.put("job", "QAutomation");

        given().
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
        when().
                post(RestAssured.basePath).
        then().
                statusCode(201).
                body("name", equalTo("Thiago")).
                body("job", equalTo("QAutomation"));
    }

    @Test
    public void POST_Users_SemParametrosNomeEJobDeveCriarSomenteId_Test() {
        JSONObject requestParams = new JSONObject();

        ResponseBody responseBody = given().
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
        when().
                post(RestAssured.basePath).
        then().
                log().all().
                statusCode(201).
                extract().response().getBody();

        Assert.assertNull(responseBody.path("name"));
        Assert.assertNull(responseBody.path("job"));
    }
}
