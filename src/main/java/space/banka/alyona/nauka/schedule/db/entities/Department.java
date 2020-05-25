package space.banka.alyona.nauka.schedule.db.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Department {

    @Id
    @GeneratedValue
    Integer id;

    String name;

}
