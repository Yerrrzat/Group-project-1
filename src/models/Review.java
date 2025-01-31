package models;

public class Review {
    private int id;
    private int userId;
    private int deviceId;
    private int rating;
    private String comment;

    public Review(int userId, int deviceId, int rating, String comment) {
        this.userId = userId;
        this.deviceId = deviceId;
        this.rating = rating;
        this.comment = comment;
    }

    public Review(int id, int userId, int deviceId, int rating, String comment) {
        this(userId, deviceId, rating, comment);
        this.id = id;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getDeviceId() { return deviceId; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }

    @Override
    public String toString() {
        return "Review{" + "id=" + id + ", userId=" + userId + ", deviceId=" + deviceId + ", rating=" + rating + ", comment='" + comment + "'}";
    }
}
