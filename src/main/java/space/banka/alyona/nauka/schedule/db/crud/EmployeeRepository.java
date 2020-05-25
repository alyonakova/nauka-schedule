package space.banka.alyona.nauka.schedule.db.crud;

import org.springframework.data.repository.CrudRepository;
import space.banka.alyona.nauka.schedule.db.entities.Employee;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    List<Employee> findAll();
    List<Employee> findByDepartmentId(Integer departmentId);
}
