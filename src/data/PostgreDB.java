package data;

import data.interfaces.IDB;

import java.sql.Connection;

public class PostgreDB implements IDB {
    private String host;
    private String username;
    private String password;
    private String dbName;


    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void close() {

    }
}
