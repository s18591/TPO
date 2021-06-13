package com.company;

import com.company.dtos.GroupDTO;
import com.company.dtos.UserDTO;

import java.sql.*;

public class GroupAndUserRepository {

    private Connection connection;
    private Savepoint savepoint;

    public GroupAndUserRepository() {
        getConnection();
    }

    public Connection getConnection() {
        try {
            if (connection != null) {
                return connection;
            }
            System.out.println("Connecting...");
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@db-oracle.pjwstk.edu.pl:1521:baza", "s18289", "oracle12");
            System.out.println("Connected");
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void add(UserDTO u_dto, GroupDTO g_dto) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO groups_users (user_id, group_id) "
                    + "VALUES (?,?)");
            statement.setInt(1, u_dto.getId());
            statement.setInt(2, g_dto.getId());
            statement.setQueryTimeout(10);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(UserDTO u_dto, GroupDTO g_dto) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM groups_users WHERE " +
                    "user_id = ? AND " +
                    "group_id = ?");
            statement.setInt(1, u_dto.getId());
            statement.setInt(2, g_dto.getId());
            statement.setQueryTimeout(10);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
            savepoint = getConnection().setSavepoint();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commitTransaction() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollbackTransaction() {
        try {
            connection.rollback(savepoint);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCount() {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT COUNT (*) FROM groups_users");
            statement.setQueryTimeout(10);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean exists(UserDTO dto) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM groups_users " + "WHERE user_id = ?");
            statement.setInt(1, dto.getId());
            statement.setQueryTimeout(10);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean exists(GroupDTO dto) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM groups_users " + "WHERE group_id = ?");
            statement.setInt(1, dto.getId());
            statement.setQueryTimeout(10);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}