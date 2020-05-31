package space.banka.alyona.nauka.schedule.entrypoints.webui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import space.banka.alyona.nauka.schedule.db.crud.DepartmentRepository;
import space.banka.alyona.nauka.schedule.db.crud.EmployeeRepository;
import space.banka.alyona.nauka.schedule.db.crud.PositionRepository;
import space.banka.alyona.nauka.schedule.db.entities.Employee;
import space.banka.alyona.nauka.schedule.entrypoints.webui.formobjects.EmployeeForm;

@Controller
public class EmployeesInfoController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @RequestMapping("/employees")
    String showEmployeesInfo(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "employees_info";
    }

    @GetMapping("/employees/add")
    String addEmployee(Model model) {
        model.addAttribute("employee", new EmployeeForm());
        return "add_employee";
    }

    @PostMapping("/employees/add")
    String addEmployee(@ModelAttribute EmployeeForm employeeForm) {
        final Employee employee = new Employee();
        employee.setName(employeeForm.getName());
        employee.setSurname(employeeForm.getSurname());
        employee.setBirthDate(employeeForm.getBirthDate());
        employee.setRemoteWork(employeeForm.isRemoteWork());
        employee.setAddress(employeeForm.getAddress());

        String positionName = employeeForm.getPosition();
        String departmentName = employeeForm.getDepartment();

        employee.setPosition(positionRepository.findByName(positionName));
        employee.setDepartment(departmentRepository.findByName(departmentName));

        employeeRepository.save(employee);
        return "redirect:/employees";
    }
}
