package space.banka.alyona.nauka.schedule.db.crud;

import org.springframework.data.repository.CrudRepository;
import space.banka.alyona.nauka.schedule.db.entities.Day;

import java.util.List;

public interface DayRepository extends CrudRepository<Day, Day.Id> {

    List<Day> findByYearYearAndIdMonth(Integer year, Integer month);
}
