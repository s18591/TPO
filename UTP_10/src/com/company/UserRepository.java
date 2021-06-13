package com.company;

import com.company.dtos.UserDTO;
import com.company.repositories.IUserRepository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {

    private Connection connection;
    private Savepoint savepoint;


    public UserRepository() {
        getConnection();
    }

    @Override
    public List<UserDTO> findByName(String username) {
        try {
            List<UserDTO> byname = new ArrayList<UserDTO>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE users.user_login LIKE ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserDTO user = new UserDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                byname.add(user);
            }
            return byname;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Connection getConnection() {
        try {
            if (connection != null) {
                return connection;
            }
            System.out.println("Connecting...");
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@db-oracle.pjwstk.edu.pl:1521:baza", "s18591", "oracle12");
            System.out.println("Connected");
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(UserDTO dto) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO users (user_id, user_login, user_password) "
                    + "VALUES (?,?,?)");
            statement.setInt(1, dto.getId());
            statement.setString(2, dto.getLogin());
            statement.setString(3, dto.getPassword());
            statement.setQueryTimeout(10);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserDTO dto) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE users SET " +
                    "user_login = ?" + " ," +
                    "user_password = ?" + " " +
                    "WHERE user_id = ?");
            preparedStatement.setString(1, dto.getLogin());
            preparedStatement.setString(2, dto.getPassword());
            preparedStatement.setInt(3, dto.getId());
            preparedStatement.setQueryTimeout(10);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOrUpdate(UserDTO dto) {
        if (exists(dto)) {
            update(dto);
        } else {
            add(dto);
        }
    }

    @Override
    public void delete(UserDTO dto) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM users WHERE " +
                    "user_id = ? AND " +
                    "user_login = ? AND " +
                    "user_password = ?");
            statement.setInt(1, dto.getId());
            statement.setString(2, dto.getLogin());
            statement.setString(3, dto.getPassword());
            statement.setQueryTimeout(10);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserDTO findById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE users.user_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserDTO user = new UserDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
            savepoint = getConnection().setSavepoint();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commitTransaction() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollbackTransaction() {
        try {
            connection.rollback(savepoint);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT COUNT (*) FROM users");
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

    @Override
    public boolean exists(UserDTO dto) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM users " + "WHERE user_id = ?");
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

    /*public void closeConnection()
    {
        try
        {
            if (connection != null)
            {
                if (!connection.isClosed())
                {
                    connection.close();
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }*/
}