package repositories.interfaces;

import models.Return;

import java.util.List;

public interface IReturnRepository {
    default boolean createReturn(Return returnRequest) // kept the original method
    {
        return false;
    }


    boolean createReturn(Object returnRequest);

    List<models.Return> getAllReturns();
    models.Return getReturnById(int id);
}
