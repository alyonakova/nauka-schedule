package space.banka.alyona.nauka.schedule.entrypoints.webui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import space.banka.alyona.nauka.schedule.db.crud.DepartmentRepository;

@Controller
public class DepartmentsInfoController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @RequestMapping("/departments")
    String showEmployeesInfo(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        return "departments_info";
    }

    @RequestMapping("/departments/add")
    String addDepartment(Model model) {
        return "add_department";
    }
}
