import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.get;

public class ApiTesteContratoTest extends ApiRestAssuredTest {

    @BeforeClass
    public static void setup() {
        ApiRestAssuredTest.setup();
        ApiRestAssuredTest.setBasePath("/api/users");
    }

    public static final String BODY = """
    {
        "data": {
            "id": 2,
            "email": "janet.weaver@reqres.in",
            "first_name": "Janet",
            "last_name": "Weaver",
            "avatar": "https://reqres.in/img/faces/2-image.jpg"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
    }""";

    @Test
    public void GET_TestaContratoUsersId2() {
        ResponseBody responseBody =
                get(RestAssured.basePath+"/2")
                .then()
                .statusCode(200)
                .extract().response()
                .getBody();

        Assert.assertNotNull(responseBody);
        Assert.assertEquals(BODY, responseBody.prettyPrint());
    }

}