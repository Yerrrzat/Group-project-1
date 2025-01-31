package repositories;

import data.interfaces.IDB;
import entities.Return;
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
        try (Connection con = db.getConnection()) {
            String sql = "INSERT INTO Returns (user_id, device_id, reason, status) VALUES (?, ?, ?, 'pending')";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, returnRequest.getUserId());
            st.setInt(2, returnRequest.getDeviceId());
            st.setString(3, returnRequest.getReason());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Return> getAllReturns() {
        List<Return> returns = new ArrayList<>();
        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM Returns";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                returns.add(new Return(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("device_id"),
                        rs.getString("reason"), rs.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returns;
    }

    @Override
    public Return getReturnById(int id) {
        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM Returns WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Return(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("device_id"),
                        rs.getString("reason"), rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
