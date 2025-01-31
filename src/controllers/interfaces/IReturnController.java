package controllers.interfaces;

public interface IReturnController {
    String createReturn(int user_id, int device_id, String reason);
    String getReturnById(int id);
    String getAllReturns();
}
