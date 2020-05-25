package space.banka.alyona.nauka.schedule.db.crud;

import org.springframework.data.repository.CrudRepository;
import space.banka.alyona.nauka.schedule.db.entities.Calendar;

public interface CalendarRepository extends CrudRepository<Calendar, Integer> {

    Calendar findByYear(Integer year);
}
