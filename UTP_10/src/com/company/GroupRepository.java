package com.company;

import com.company.dtos.GroupDTO;
import com.company.repositories.IGroupRepository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupRepository implements IGroupRepository {

    private Connection connection;
    private Savepoint savepoint;


    public GroupRepository() {
        getConnection();
    }

    @Override
    public List<GroupDTO> findByName(String name) {
        try {
            List<GroupDTO> byname = new ArrayList<GroupDTO>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM groups WHERE groups.group_name LIKE ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                GroupDTO group = new GroupDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                byname.add(group);
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
    public void add(GroupDTO dto) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO groups (group_id, group_name, group_description) "
                    + "VALUES (?,?,?)");
            statement.setInt(1, dto.getId());
            statement.setString(2, dto.getName());
            statement.setString(3, dto.getDescription());
            statement.setQueryTimeout(10);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GroupDTO dto) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE groups SET " +
                    "group_name = ?" + " ," +
                    "group_description = ?" + " " +
                    "WHERE group_id = ?");
            preparedStatement.setString(1, dto.getName());
            preparedStatement.setString(2, dto.getDescription());
            preparedStatement.setInt(3, dto.getId());
            preparedStatement.setQueryTimeout(10);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOrUpdate(GroupDTO dto) {
        if (exists(dto)) {
            update(dto);
        } else {
            add(dto);
        }
    }

    @Override
    public void delete(GroupDTO dto) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM groups WHERE " +
                    "group_id = ? AND " +
                    "group_name = ? AND " +
                    "group_description = ?");
            statement.setInt(1, dto.getId());
            statement.setString(2, dto.getName());
            statement.setString(3, dto.getDescription());
            statement.setQueryTimeout(10);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GroupDTO findById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM groups WHERE groups.group_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                GroupDTO group = new GroupDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                return group;
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollbackTransaction() {
        try {
            connection.rollback(savepoint);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT COUNT (*) FROM groups");
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
    public boolean exists(GroupDTO dto) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM groups " + "WHERE group_id = ?");
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