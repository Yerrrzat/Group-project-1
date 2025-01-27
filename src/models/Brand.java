package models;

public class Brand {
    private int id;
    private String name;
    private String description;
    private String country;
    private String establishedDate;

    // Constructors
    public Brand(String name, String description, String country, String establishedDate) {
        this.name = name;
        this.description = description;
        this.country = country;
        this.establishedDate = establishedDate;
    }

    public Brand(int id, String name, String description, String country, String establishedDate) {
        this(name, description, country, establishedDate);
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEstablishedDate() {
        return establishedDate;
    }

    public void setEstablishedDate(String establishedDate) {
        this.establishedDate = establishedDate;
    }


    // toString method
    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", country='" + country + '\'' +
                ", establishedDate='" + establishedDate + '\'' +
                '}';
    }
}