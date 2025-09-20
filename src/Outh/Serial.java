package Outh;
import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;


public class Serial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
String ss=given().queryParam("place_id","e9fbcf81635fa730d1493f1d84c0492b").queryParam("key", "qaclick123").when().log().all().get("https://rahulshettyacademy.com//maps/api/place/get/json").asString();
System.out.println(ss);
	}

}
