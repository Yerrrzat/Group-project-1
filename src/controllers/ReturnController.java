package controllers;

import controllers.interfaces.IReturnController;
import entities.Return;
import repositories.interfaces.IReturnRepository;

import java.util.List;

public class ReturnController implements IReturnController {
    private final IReturnRepository returnRepository;

    public ReturnController(IReturnRepository returnRepository) {
        this.returnRepository = returnRepository;
    }

    @Override
    public String createReturn(int userId, int deviceId, String reason) {
        Return returnRequest = new Return(0, userId, deviceId, reason, "pending");
        return returnRepository.createReturn(returnRequest) ? "Return created successfully" : "Error creating return";
    }

    @Override
    public String getAllReturns() {
        List<Return> returns = returnRepository.getAllReturns();
        return returns.toString();
    }

    @Override
    public String getReturnById(int id) {
        Return returnRequest = returnRepository.getReturnById(id);
        return (returnRequest != null) ? returnRequest.toString() : "Return not found";
    }
}
