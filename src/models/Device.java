package models;

public class  Device{
    private int id;
    private String name;
    private String description;
    private int category_id;
    private String brand;
    private double price;
    private int stock_quantity;
    private String release_date;
    private String specifications;

    public Device(){

    }

    public Device(String name, String description, int category_id, String brand, double price, int stock_quantity, String release_date, String specifications) {
        setName(name);
        setDescription(description);
        setCategory_id(category_id);
        setBrand(brand);
        setPrice(price);
        setStock_quantity(stock_quantity);
        setRelease_date(release_date);
        setSpecifications(specifications);

    }

    public Device (int id,String name, String description, int category_id, String brand, double price, int stock_quantity, String release_date, String specifications){
        this(name, description, category_id, brand, price, stock_quantity, release_date, specifications);
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    @Override
    public String toString() {
        return "\n Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category_id=" + category_id +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ",stock_quantity=" + stock_quantity +
                ",release_date='" + release_date + '\'' +
                ", specifications='" + specifications + '\'' +
                '}';
    }
}
