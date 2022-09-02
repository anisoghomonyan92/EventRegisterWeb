package manager;

import db.DBConectionProvider;
import model.Event;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private Connection connection = DBConectionProvider.getInstance().getConnection();
    private EventManager eventManager=new EventManager();

    public UserManager() {
    }

    public void addUser(User user) {
        String sql = "Insert into user(name,surname,email,event_id)Values(?,?,?,?))";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, 1);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getEventId().getId());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                user.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> showUsers() {
        String sql = "SELECT * FROM user";
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getById(int id)  {
        String sql = "SELECT * FROM user where id= " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
               return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user= new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setEmail(resultSet.getString("email"));
        int eventId = resultSet.getInt("event_id");
        Event event = eventManager.getById(eventId);
        user.setEventId(event);

        return user;

    }
}
