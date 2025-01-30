package repositories;

import data.interfaces.IDB;
import models.User;
import repositories.interfaces.IUserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;
    private List<User> users;

    public UserRepository(IDB db) {
        this.db = db;
    }


    @Override
    public boolean createUser(User user) {
        Connection conn = null;
        try{
            conn=db.getConnection();
            String sql="INSERT INTO users(name,surname,email,password,address,phone) VALUES (?,?,?,?,?,?)";
            PreparedStatement st= conn.prepareStatement(sql);

            st.setString(1,user.getName());
            st.setString(2, user.getSurname());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());
            st.setString(5, user.getAddress());
            st.setString(6, user.getPhone());

            st.execute();
            return true;

        }catch(SQLException e){
            System.out.println("sql error"+e.getMessage());

        }
        return false;
    }

    @Override
    public User getUserById(int id) {
        Connection conn = null;
        try{
            conn=db.getConnection();
            String sql="SELECT * FROM users WHERE id=?";
            PreparedStatement st=conn.prepareStatement(sql);

            st.setInt(1,id);

            ResultSet rs = st.executeQuery();
            if (rs.next()){
                return new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("address"),
                        rs.getString("phone"));
            }


        } catch (SQLException e) {
            System.out.println("sql error"+e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        Connection conn = null;
        try{
            conn=db.getConnection();
            String sql="SELECT * FROM users";
            Statement st=conn.createStatement();
            ResultSet rs= st.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (rs.next()){
                User user=new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("address"),
                        rs.getString("phone"));
                users.add(user);
            }
            return users;
        }catch (SQLException e){
            System.out.println("sql error"+e.getMessage());
        }
        return null;
    }
}
