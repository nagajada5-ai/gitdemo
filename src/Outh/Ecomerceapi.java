package Outh;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.Loginrequest;
import pojo.Loginresponse;
import pojo.OrderDetails;
import pojo.Orders;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class Ecomerceapi {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
RequestSpecification rq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
Loginrequest log=new Loginrequest();
log.setUserEmail("nagajada5@gmail.com");
log.setUserPassword("Naga@123");

RequestSpecification rqlogin=given().log().all().spec(rq).body(log);
Loginresponse logrep=rqlogin.when().post("/api/ecom/auth/login").then().log().all().extract().response().as(Loginresponse.class);
System.out.println(logrep.getToken());
String token=logrep.getToken();
System.out.println(logrep.getUserId());
String userId=logrep.getUserId();
	
	//add product
RequestSpecification rqadd=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token).build();
RequestSpecification rqaddpro=given().log().all().spec(rqadd).param("productName","laptop").param("productAddedBy",userId).param("productCategory","fashion ").param("productSubCategory","shirts ").param("productPrice","11500").param("productDescription","Addias Originals ").param("productFor", "women").
multiPart("productImage",new File("C:\\Users\\User\\Documents\\Zoom\\laptop.png"));
	String addprores=rqaddpro.when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();
	JsonPath js=new JsonPath(addprores);
	String pId=js.get("productId");
String mesg=js.get("message");
	
	
//create order
RequestSpecification createrder=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token).setContentType(ContentType.JSON).build();
OrderDetails orderdetil=new OrderDetails();
orderdetil.setCountry("India");
orderdetil.setProductId(pId);
List<OrderDetails>orderlist=new ArrayList<OrderDetails>();
orderlist.add(orderdetil);
Orders oddd=new Orders();
oddd.setOrders(orderlist);
RequestSpecification reqcreaterder=given().log().all().spec(createrder).body(oddd);
String responseaddorder=reqcreaterder.when().post("/api/ecom/order/create-order").then().log().all().extract().asString();
System.out.println(responseaddorder);

RequestSpecification delteorder=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token).setContentType(ContentType.JSON).build();
RequestSpecification delteorderreq=given().log().all().spec(delteorder).pathParam("productId", pId);
String deleteproductres=delteorderreq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().extract().response().asString();
JsonPath js1=new JsonPath(deleteproductres);

	}

}
