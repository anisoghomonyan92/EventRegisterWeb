package manager;

import db.DBConectionProvider;
import model.Event;
import model.EventType;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private Connection connection = DBConectionProvider.getInstance().getConnection();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public EventManager() {
    }

    public void addEvent(Event event) {
        String sql = "Insert into event(name,place,is_online,price,event_type,event_date)Values(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, 1);

            preparedStatement.setString(1, event.getName());
            preparedStatement.setString(2, event.getPlace());
            preparedStatement.setBoolean(3, event.isOnline());
            preparedStatement.setDouble(4, event.getPrice());
            preparedStatement.setString(5, event.getEventType().name());
            preparedStatement.setString(6, sdf.format(event.getEventDate()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                event.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Event> showEvents() {
        String sql = "SELECT * FROM event";
        List<Event> events = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                events.add(getEventFromResultSet(resultSet));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return events;
    }

    public Event getById(int id) {
        String sql = "SELECT * FROM event where id= " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getEventFromResultSet(resultSet);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Event getEventFromResultSet(ResultSet resultSet) throws SQLException, ParseException {
        Event event = Event.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .place(resultSet.getString("place"))
                .isOnline(resultSet.getBoolean("is_online"))
                .price(resultSet.getDouble("price"))
                .eventType(EventType.valueOf(resultSet.getString("event_type")))
                .eventDate(resultSet.getString("event_date") == null ? null : sdf.parse(resultSet.getString("event_date")))
                .build();
        return event;

    }
}
