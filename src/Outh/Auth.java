package Outh;
import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;

public class Auth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
String response=given().formParam("clinet_id", "692183103107-\r\n"
		+ "p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type","client_credentials")
.formParam("scope","trust").when().log().all()
.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
System.out.println(response);
JsonPath jsonpath=new JsonPath(response);
jsonpath.get("error");
String response2=given().when().log().all().get("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
System.out.println(response2
		);


	}

}
