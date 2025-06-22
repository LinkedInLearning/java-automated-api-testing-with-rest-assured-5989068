package trainingxyz;

import org.junit.jupiter.api.Test;

import models.Product;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

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
    given()
        .queryParam("id", 2)
      .when()
        .get(endpoint)
      .then()
        .assertThat()
          .statusCode(200)
          .body("id", equalTo("2"))
          .body("name", equalTo("Cross-Back Training Tank"))
          .body("description", equalTo("The most awesome phone of 2013!"))
          .body("price", equalTo("299.00"))
          .body("category_id", equalTo(2))
          .body("category_name", equalTo("Active Wear - Women"));
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


  @Test
  public void createSweatband()
  {
      String endpoint = baseUrl + "product/create.php";
      Product product = new Product(
        "Sweatband",
        "White sweatband. One size fits all.",
        5,
        3
      );
      var response = given().body(product).when().post(endpoint).then();
      response.log().body();
  }

  @Test
  public void updateSweatband() {
    String endpoint = baseUrl + "product/update.php";
    Product product = new Product(
      20,
      "Sweatband",
      "White sweatband. One size fits all.",
      5.99,
      3,
      "Active Wear - Unisex");
    
    var response = given().body(product).when().put(endpoint).then();
    response.log().body();
  }

  @Test
  public void getSweatband() {
    String endpoint = baseUrl + "product/read_one.php";
    var response = 
      given()
        .queryParam("id", 20)
      .when()
        .get(endpoint)
      .then();
    response.log().body();
  }

  @Test
  public void deleteSweatband() {
    String endpoint = baseUrl + "product/delete.php";
    String body = """
        {
          "id": 20
        }
        """;
    var response = given().body(body).when().delete(endpoint).then();
    response.log().body();
  }

  @Test
  public void getProducts(){
    String endpoint = baseUrl + "product/read.php";
    given()
    .when()
      .get(endpoint)
    .then()
      .log()
      .body()
        .assertThat()
          .statusCode(200)
          .body("records.size()", greaterThan(0))
          .body("records.id", everyItem(notNullValue()))
          .body("records.name", everyItem(notNullValue()))
          .body("records.description", everyItem(notNullValue()))
          .body("records.price", everyItem(notNullValue()))
          .body("records.category_id", everyItem(notNullValue()))
          .body("records.category_name", everyItem(notNullValue()))
          .body("records.id[0]", equalTo(21));
  }

}