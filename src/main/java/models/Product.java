package models;

public record Product(
  int id,
  String name,
  String description,
  double price,
  int category_id,
  String category_name
) {

  public Product(String name, String description, double price, int category_id){
    this(0, name, description, price, category_id, null);
  }

}
