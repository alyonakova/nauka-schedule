package space.banka.alyona.nauka.schedule.entrypoints.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.banka.alyona.nauka.schedule.db.crud.EmployeeRepository;
import space.banka.alyona.nauka.schedule.db.crud.PositionRepository;
import space.banka.alyona.nauka.schedule.db.entities.Employee;
import space.banka.alyona.nauka.schedule.db.entities.Position;

@RestController
public class EmployeesRestController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @PostMapping("/api/employees")
    void updateEmployeesList(@RequestBody EmployeeUpdateRequest request) {

        final Employee employee = employeeRepository.findById(request.id).orElseThrow();
        employee.setName(request.name);
        employee.setSurname(request.surname);
        employee.setBirthDate(request.birthDate);

        Position oldPosition = employee.getPosition();
        oldPosition.getEmployee().remove(employee);

        Position newPosition = positionRepository.findByName(request.positionName);
        employee.setPosition(newPosition);
        newPosition.getEmployee().add(employee);

        employee.setRemoteWork(request.remoteWork);
        employee.setAddress(request.address);
        employeeRepository.save(employee);

    }

    @DeleteMapping("/api/employees/{id}")
    void deleteEmployee(@PathVariable("id") Integer id) {
        employeeRepository.deleteById(id);
    }
}
