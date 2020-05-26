package space.banka.alyona.nauka.schedule.db.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Data
public class Day {

    @Embeddable
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Id implements Serializable {

        @Column(name = "year")
        public Integer year;

        @Column(name = "month")
        public Integer month;

        @Column(name = "day")
        public Integer day;
    }

    @EmbeddedId
    Id id;

    @MapsId("year")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "year", referencedColumnName = "year")
    })
    Calendar year;

    DayType type;

    @OneToMany(mappedBy = "day")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Collection<EmployeeDay> employeeDays = new HashSet<>();

    public LocalDate toLocalDate() {
        return LocalDate.of(year.year, id.month, id.day);
    }
}
