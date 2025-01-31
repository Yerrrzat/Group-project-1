package models;

public class Return {
    private int id;
    private int userId;
    private int deviceId;
    private String reason;
    private String status;

    public Return(int userId, int deviceId, String reason) {
        this.userId = userId;
        this.deviceId = deviceId;
        this.reason = reason;
        this.status = "pending";
    }

    public Return(int id, int userId, int deviceId, String reason, String status) {
        this(userId, deviceId, reason);
        this.id = id;
        this.status = status;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getDeviceId() { return deviceId; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "Return{" + "id=" + id + ", userId=" + userId + ", deviceId=" + deviceId + ", reason='" + reason + "', status='" + status + "'}";
    }
}
