package repositories.interfaces;

import entities.Return;

import java.util.List;

public interface IReturnRepository {
    boolean createReturn(Return returnRequest);
    List<Return> getAllReturns();
    Return getReturnById(int id);
}
