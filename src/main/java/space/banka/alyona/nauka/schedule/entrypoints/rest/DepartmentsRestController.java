package space.banka.alyona.nauka.schedule.entrypoints.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.banka.alyona.nauka.schedule.db.crud.DepartmentRepository;
import space.banka.alyona.nauka.schedule.db.entities.Department;

@RestController
public class DepartmentsRestController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping("/api/departments")
    void updateDepartmentsList(@RequestBody DepartmentUpdateRequest request) {
        final Department department = departmentRepository.findById(request.id).orElseThrow();
        department.setName(request.name);
        departmentRepository.save(department);
    }

    @DeleteMapping("/api/departments/{id}")
    void deleteDepartment(@PathVariable("id") Integer id) {
        departmentRepository.deleteById(id);
    }
}
