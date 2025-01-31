package repositories.interfaces;

import models.Return;
import java.util.List;

public interface IReturnRepository {
    boolean createReturn(Return returnRequest);
    Return getReturnById(int id);
    List<Return> getAllReturns();
}
