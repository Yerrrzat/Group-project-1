package models;

public class Review {
    private int id;
    private int userId;
    private int deviceId;
    private int rating;
    private String comment;
    private String createdAt;

    public Review(int id, int userId, int deviceId, int rating, String comment, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.deviceId = deviceId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userId=" + userId +
                ", deviceId=" + deviceId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}