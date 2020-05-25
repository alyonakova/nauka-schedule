package space.banka.alyona.nauka.schedule.entrypoints.webui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import space.banka.alyona.nauka.schedule.db.crud.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
public class IndexPageController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TimesheetCreator timesheetCreator;

    /*
    @RequestMapping("/")
    String showIndexPage(Model model) {
        final LocalDate currentDate = LocalDate.now();
        final int currentYear = currentDate.getYear();
        final int currentMonth = currentDate.getMonthValue();

        List<String> months = Arrays.stream(Month.values())
                .map(month -> month.getDisplayName(
                TextStyle.FULL_STANDALONE, new Locale("ru", "ru")))
                .collect(Collectors.toList());

        model.addAttribute("months", months);

        model.addAttribute("days", dayRepository.findByYearYearAndIdMonth(currentYear, currentMonth));

        model.addAttribute("departments", departmentRepository.findAll());
        // TODO: Календарь не нужен, использовать DayRepository и поиск по году и месяцу (см. "days" выше)
        model.addAttribute("calendar", calendarRepository.findByYear(currentYear));

        model.addAttribute("employees", employeeRepository.findAll());

        return "schedule";
    }
    */

    @RequestMapping("/")
    String showIndexPage(Model model) {
        final LocalDate currentDate = LocalDate.now();
        final int currentYear = currentDate.getYear();
        final int currentMonth = currentDate.getMonthValue();

        List<String> months = Arrays.stream(Month.values())
                .map(month -> month.getDisplayName(
                        TextStyle.FULL_STANDALONE, new Locale("ru", "ru")))
                .collect(Collectors.toList());

        model.addAttribute("months", months);
        model.addAttribute("departments", departmentRepository.findAll());
        Timesheet timesheet = timesheetCreator.createWithAllDepartments(currentYear, currentMonth);

        model.addAttribute("timesheet", timesheet);
        return "schedule";
    }

}
