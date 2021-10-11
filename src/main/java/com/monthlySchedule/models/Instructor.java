package com.monthlySchedule.models;

import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;

/**
 * Represents an instructor
 * @author Veronica Lucena
 */
@Entity
@Table(name = "instructor")
public class Instructor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name="id_instructor")
    //Primary key
    private Integer id;
    
    private String firstName;
    
    private String lastName;
    
    private String birthday;
    
    @OneToMany(mappedBy="instructor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Event> events;

    public Instructor() {
    }

    public Instructor(String firstName, String lastName, String birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Instructor other = (Instructor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Instructor{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday + ", events=" + events + '}';
    }
    
    
}
