import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class ApiRestAssuredTest {

    public static void setBasePath(String basePath) {
        RestAssured.basePath = RestAssured.baseURI + basePath;
    }

    @BeforeClass
    public static void setup() {
        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "https://reqres.in";
        }
        RestAssured.baseURI = baseHost;
    }
}
