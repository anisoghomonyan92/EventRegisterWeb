package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private Event eventId;

    public User(String name, String surname, String email, Event eventId) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.eventId = eventId;
    }
}
