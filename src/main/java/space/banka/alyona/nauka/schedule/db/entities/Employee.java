package space.banka.alyona.nauka.schedule.db.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue
    Integer id;

    String name;
    String surname;
    LocalDate birthDate;

    @ManyToOne
    Position position;

    boolean remoteWork;

    String address;

    @ManyToOne
    Department department;

    @OneToMany(mappedBy = "employee")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    public Collection<EmployeeDay> employeeDays;

    public long getAge() {
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }
}
