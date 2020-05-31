package space.banka.alyona.nauka.schedule.entrypoints.webui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import space.banka.alyona.nauka.schedule.db.crud.EmployeeRepository;
import space.banka.alyona.nauka.schedule.entrypoints.webui.formobjects.EmployeeForm;

@Controller
public class EmployeesInfoController {

    @Autowired
    private EmployeeRepository employeeRepository;

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
}
