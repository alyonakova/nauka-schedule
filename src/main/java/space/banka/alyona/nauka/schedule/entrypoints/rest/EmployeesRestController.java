package space.banka.alyona.nauka.schedule.entrypoints.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.banka.alyona.nauka.schedule.db.crud.EmployeeRepository;
import space.banka.alyona.nauka.schedule.db.crud.PositionRepository;
import space.banka.alyona.nauka.schedule.db.entities.Employee;
import space.banka.alyona.nauka.schedule.db.entities.Position;

import java.util.Objects;

@RestController
public class EmployeesRestController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @PatchMapping("/api/employees/{id}")
    void updateEmployee(@PathVariable("id") Integer id,
                        @RequestBody EmployeeUpdateRequest request) {
        final Employee employee = employeeRepository.findById(id).orElseThrow();
        if (request.name != null)
            employee.setName(request.name);
        if (request.surname != null)
            employee.setSurname(request.surname);
        if (request.birthDate != null)
            employee.setBirthDate(request.birthDate);
        if (request.positionName != null)
            employee.setPosition(Objects.requireNonNull(positionRepository.findByName(request.positionName)));
        if (request.remoteWork != null)
            employee.setRemoteWork(request.remoteWork);
        if (request.address != null)
            employee.setAddress(request.address);
        employeeRepository.save(employee);
    }

    @DeleteMapping("/api/employees/{id}")
    void deleteEmployee(@PathVariable("id") Integer id) {
        employeeRepository.deleteById(id);
    }
}
