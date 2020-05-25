package space.banka.alyona.nauka.schedule.db.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
public class Calendar {
    @Id
    @Accessors
    Integer year;

    @OneToMany(mappedBy = "year")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<Day> days;
}
