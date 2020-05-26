package space.banka.alyona.nauka.schedule.entrypoints.webui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import space.banka.alyona.nauka.schedule.db.crud.DepartmentRepository;
import space.banka.alyona.nauka.schedule.domain.Timesheet;
import space.banka.alyona.nauka.schedule.domain.TimesheetCreator;
import space.banka.alyona.nauka.schedule.entrypoints.webui.objects.Month;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TimesheetController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TimesheetCreator timesheetCreator;

    @GetMapping("/departments/all/timesheet")
    String showTimesheetForAllDepartments(@RequestParam(name = "year", required = false) Integer yearParam,
                                          @RequestParam(name = "month", required = false) Integer monthParam,
                                          Model model) {
        LocalDate currentDate = LocalDate.now();
        int year = (yearParam != null) ? yearParam : currentDate.getYear();
        int month = (monthParam != null) ? monthParam : currentDate.getMonthValue();

        List<Month> months = new ArrayList<>();
        for (java.time.Month oneMonth : java.time.Month.values()) {
            Month fromJavaTime = Month.fromJavaTime(oneMonth);
            months.add(fromJavaTime);
        }

        model.addAttribute("month", month);
        model.addAttribute("year", year);
        model.addAttribute("months", months);
        model.addAttribute("departments", departmentRepository.findAll());

        Timesheet timesheet = timesheetCreator.createWithAllDepartments(year, month);
        model.addAttribute("timesheet", timesheet);
        return "schedule";
    }

    @GetMapping("/departments/{id}/timesheet")
    String showTimesheetForAllDepartments(@PathVariable("id") Integer departmentId,
                                          @RequestParam(name = "year", required = false) Integer yearParam,
                                          @RequestParam(name = "month", required = false) Integer monthParam,
                                          Model model) {
        LocalDate currentDate = LocalDate.now();
        int year = (yearParam != null) ? yearParam : currentDate.getYear();
        int month = (monthParam != null) ? monthParam : currentDate.getMonthValue();

        List<Month> months = new ArrayList<>();
        for (java.time.Month oneMonth : java.time.Month.values()) {
            Month fromJavaTime = Month.fromJavaTime(oneMonth);
            months.add(fromJavaTime);
        }

        model.addAttribute("month", month);
        model.addAttribute("year", year);
        model.addAttribute("departmentId", departmentId);
        model.addAttribute("months", months);
        model.addAttribute("departments", departmentRepository.findAll());
        Timesheet timesheet = timesheetCreator.createWithDepartment(year, month, departmentId);

        model.addAttribute("timesheet", timesheet);
        return "schedule";
    }
}
