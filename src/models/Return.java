package models;

public class Return {
    private int id;
    private int user_id;
    private int device_id;
    private String reason;
    private String status;

    public Return(int user_id, int device_d, String s, String reason) {
        this.user_id = user_id;
        this.device_id = device_id;
        this.reason = reason;
        this.status = "pending";
    }

    public Return(int id, int userId, int device_id, String reason, String status) {
        this(userId, device_id, reason, reason);
        this.id = id;
        this.status = status;
    }

    public int getId() { return id; }
    public int getUserId() { return user_id; }
    public int getDeviceId() { return device_id; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "Return{" + "id=" + id + ", userId=" + user_id + ", deviceId=" + device_id + ", reason='" + reason + "', status='" + status + "'}";
    }
}
