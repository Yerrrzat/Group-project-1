package repositories;

import data.interfaces.IDB;
import models.Return;
import repositories.interfaces.IReturnRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReturnRepository implements IReturnRepository {
    private final IDB db;

    public ReturnRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createReturn(Return returnRequest) {
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement("INSERT INTO Returns (user_id, device_id, reason, status) VALUES (?, ?, ?, ?)")) {
            st.setInt(1, returnRequest.getUserId());
            st.setInt(2, returnRequest.getDeviceId());
            st.setString(3, returnRequest.getReason());
            st.setString(4, returnRequest.getStatus());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public Return getReturnById(int id) {
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement("SELECT * FROM Returns WHERE id = ?")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Return(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("device_id"), rs.getString("reason"), rs.getString("status"));
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    @Override
    public List<Return> getAllReturns() {
        List<Return> returns = new ArrayList<>();
        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Returns")) {
            while (rs.next()) {
                returns.add(new Return(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("device_id"), rs.getString("reason"), rs.getString("status")));
            }
        } catch (SQLException e) {
            return null;
        }
        return returns;
    }
}
