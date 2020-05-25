package space.banka.alyona.nauka.schedule.db.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@Data
public class Position {
    @Id
    @GeneratedValue
    Integer id;

    String name;

    @OneToMany(mappedBy="position")
    Collection<Employee> employee;
}
