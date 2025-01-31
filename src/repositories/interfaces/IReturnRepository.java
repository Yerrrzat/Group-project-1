package repositories.interfaces;

import entities.Return;

import java.util.List;

public interface IReturnRepository {
    boolean createReturn(Return returnRequest);

    boolean createReturn(Object returnRequest);

    List<models.Return> getAllReturns();
    models.Return getReturnById(int id);
}
