package models;

public class Review {
    private int id;
    private int user_id;
    private int device_id;
    private int rating;
    private String comment;

    public Review() {

    }


    public Review(int user_id, int device_id, int rating, String comment) {
        setUser_id(user_id);
        setDevice_id(device_id);
        setRating(rating);
        setComment(comment);

    }

    public Review(int id, int user_id, int device_id, int rating, String comment) {
        this(user_id, device_id, rating, comment);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    @Override
    public String toString() {
        return "\n Review{" + "id=" + id + ", userId=" + user_id + ", deviceId=" + device_id + ", rating=" + rating + ", comment='" + comment + "'}";
    }
}
