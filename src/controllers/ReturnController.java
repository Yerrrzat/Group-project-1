package controllers;

import controllers.interfaces.IReturnController;
import models.Return;
import repositories.interfaces.IReturnRepository;

import java.util.List;

public class ReturnController implements IReturnController {
    private final IReturnRepository repo;

    public ReturnController(IReturnRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createReturn(int userId, int deviceId, String reason) {
        Return returnRequest = new Return(userId, deviceId, reason);
        return repo.createReturn(returnRequest) ? "Return created successfully" : "Error creating return";
    }

    @Override
    public String getReturnById(int id) {
        Return returnRequest = repo.getReturnById(id);
        return (returnRequest == null) ? "Return not found" : returnRequest.toString();
    }

    @Override
    public String getAllReturns() {
        List<Return> returns = repo.getAllReturns();
        return returns.toString();
    }
}
