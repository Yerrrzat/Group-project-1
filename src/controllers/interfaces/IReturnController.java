package controllers.interfaces;

public interface IReturnController {
    String createReturn(int userId, int deviceId, String reason);
    String getReturnById(int id);
    String getAllReturns();
}
