package space.banka.alyona.nauka.schedule.db.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class EmployeeDay {

    @Embeddable
    @Data
    public static class Id implements Serializable {

        public Day.Id dayId;

        public Integer employeeId;
    }

    @EmbeddedId
    Id id;

    @MapsId("dayId")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "year", referencedColumnName = "year"),
            @JoinColumn(name = "month", referencedColumnName = "month"),
            @JoinColumn(name = "day", referencedColumnName = "day"),
    })
    Day day;

    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    @ManyToOne
    Employee employee;

    @Column(nullable = false)
    Presence presence;
}
