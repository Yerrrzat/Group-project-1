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
    public String createReturn(int user_id, int device_id, String reason) {
        Return returnRequest = new Return(user_id, device_id, reason, "pending");
        boolean created = repo.createReturn(returnRequest);
        return created ? "Return created successfully" : "Return creation failed";
    }

    @Override
    public String getReturnById(int id) {
        Return returnRequest = repo.getReturnById(id);
        return (returnRequest == null) ? "Return not found" : returnRequest.toString();
    }

    @Override
    public String getAllReturns() {
        List<Return> returns = repo.getAllReturns();
        StringBuilder response = new StringBuilder();
        for (Return returnRequest : returns) {
            response.append(returnRequest.toString()).append("\n");
        }
        return response.toString();
    }
}
