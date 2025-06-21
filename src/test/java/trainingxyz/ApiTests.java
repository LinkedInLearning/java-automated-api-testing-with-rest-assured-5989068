package trainingxyz;

import org.junit.jupiter.api.Test;

import models.Product;

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

  @Test
  public void createProduct(){
    String endpoint = baseUrl + "product/create.php";
    String body = """
        {
          "name": "Water Bottle",
          "description": "Blue water bottle. Holds 64 ounces.",
          "price": 12,
          "category_id": 3
        }
        """;
    var response = given().body(body).when().post(endpoint).then();
    response.log().body();
  }

  @Test
  public void updateProduct(){
    String endpoint = baseUrl + "product/update.php";
    String body = """
        {
          "id": 19,
          "name": "Water Bottle",
          "description": "Blue water bottle. Holds 64 ounces.",
          "price": 15,
          "category_id": 3
        }
        """;
    var response = given().body(body).when().put(endpoint).then();
    response.log().body();
  }

  @Test
  public void deleteProduct(){
    String endpoint = baseUrl + "product/delete.php";
    String body = """
        {
          "id": 19
        }
        """;
    var response = given().body(body).when().delete(endpoint).then();
    response.log().body();
  }

  @Test
  public void createSerializedProduct(){
    String endpoint = baseUrl + "product/create.php";
    Product product = new Product(
      "Water Bottle", 
      "Blue water bottle. Holds 64 ounces",
      12.99,
      3);
    var response = given().body(product).when().post(endpoint).then();
    response.log().body();
  }
}
