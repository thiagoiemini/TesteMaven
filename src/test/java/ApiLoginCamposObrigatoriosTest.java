import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.CoreMatchers.containsString;

public class ApiLoginCamposObrigatoriosTest extends ApiRestAssuredTest{

    @BeforeClass
    public static void setup() {
        ApiRestAssuredTest.setup();
        ApiRestAssuredTest.setBasePath("/api/login");
    }

    @Test
    public void POST_Login_DeveLogarComSucesso() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "eve.holt@reqres.in");
        requestParams.put("password", "cityslicka");

        given().
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
        when().
                post(RestAssured.basePath).
        then().
                statusCode(200).
                body(containsString("token")).
                log().body().extract().response();
    }

    @Test
    public void POST_Login_DevePedirCampoObrigatorioPassword() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "eve.holt@reqres.in");

        given().
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
        when().
                post(RestAssured.basePath).
        then().
                statusCode(400).
                body("error", equalTo("Missing password")).
                log().body();
    }
}
