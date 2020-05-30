package space.banka.alyona.nauka.schedule.entrypoints.webui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import space.banka.alyona.nauka.schedule.db.crud.DepartmentRepository;
import space.banka.alyona.nauka.schedule.db.entities.Department;
import space.banka.alyona.nauka.schedule.entrypoints.webui.formobjects.DepartmentForm;

@Controller
public class DepartmentsInfoController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @RequestMapping("/departments")
    String showEmployeesInfo(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        return "departments_info";
    }

    @GetMapping("/departments/add")
    String addDepartment(Model model) {
        model.addAttribute("department", new DepartmentForm());
        return "add_department";
    }

    @PostMapping("/departments/add")
    String addDepartment(@ModelAttribute DepartmentForm departmentForm) {
        final Department department = new Department();
        department.setName(departmentForm.getName());
        departmentRepository.save(department);
        return "redirect:/departments";
    }
}
