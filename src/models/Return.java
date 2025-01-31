package models;

public class Return {
    public static Return ReturnStatus;
    private int id;
    private int userId;
    private int deviceId;
    private String reason;
    private String status;
    private String createdAt;

    public Return(int id, int userId, int deviceId, String reason, String status, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.deviceId = deviceId;
        this.reason = reason;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Return(int userId, int deviceId, String reason, String pending) {
    }

    public Return(int id, int userId, int deviceId, String reason, Object status) {
    }

    public static String valueOf(String status) {

        return status;
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

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Return{" +
                "id=" + id +
                ", userId=" + userId +
                ", deviceId=" + deviceId +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}