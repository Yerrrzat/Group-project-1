package controllers.interfaces;

public interface IReturnController {
    String createReturn(int userId, int deviceId, String reason);
    String getAllReturns();
    String getReturnById(int id);
}
