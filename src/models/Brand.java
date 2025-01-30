package models;

public class Brand {
    private int id;
    private String name;
    private String description;


    // Constructors
    public Brand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Brand(int id, String name, String description) {
        this(name, description);
        this.id = id;
    }

    // Getters and Setters
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



    // toString method
    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}