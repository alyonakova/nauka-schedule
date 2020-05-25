package space.banka.alyona.nauka.schedule.db.crud;

import org.springframework.data.repository.CrudRepository;
import space.banka.alyona.nauka.schedule.db.entities.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {
}
