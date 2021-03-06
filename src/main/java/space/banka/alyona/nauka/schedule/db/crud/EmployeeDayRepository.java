package space.banka.alyona.nauka.schedule.db.crud;

import org.springframework.data.repository.CrudRepository;
import space.banka.alyona.nauka.schedule.db.entities.Day;
import space.banka.alyona.nauka.schedule.db.entities.EmployeeDay;

import java.util.List;
import java.util.Optional;

public interface EmployeeDayRepository extends CrudRepository<EmployeeDay, EmployeeDay.Id> {

    Iterable<EmployeeDay> findByEmployeeId(Integer employeeId);

    Optional<EmployeeDay> findByEmployeeIdAndDayId(Integer employeeId, Day.Id dayId);

    List<EmployeeDay> findByEmployeeIdAndDayIdYearAndDayIdMonth(Integer employeeId, Integer yearId, Integer monthId);

}
