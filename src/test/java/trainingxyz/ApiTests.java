package trainingxyz;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {

  String baseUrl = "http://localhost:8888/";
 
  @Test
  public void getCategories(){
    String endpoint = baseUrl + "category/read.php";
    var response = given().when().get(endpoint).then();
    response.log().body();
  }  

  @Test
  public void getProduct(){
    String endpoint = baseUrl + "product/read_one.php";
    var response = 
      given()
        .queryParam("id", 2)
      .when()
        .get(endpoint)
      .then();
    response.log().body();
  }

}
