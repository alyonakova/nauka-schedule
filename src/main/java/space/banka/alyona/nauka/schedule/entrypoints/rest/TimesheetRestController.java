package space.banka.alyona.nauka.schedule.entrypoints.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.banka.alyona.nauka.schedule.domain.Timesheet;
import space.banka.alyona.nauka.schedule.domain.TimesheetCreator;

@RestController
public class TimesheetRestController {

    @Autowired
    private TimesheetCreator timesheetCreator;

    @GetMapping("/api/departments/{departmentId}/timesheet")
    Timesheet generateTimesheet(@PathVariable Integer departmentId,
                                @RequestParam Integer year,
                                @RequestParam Integer month) {
        return timesheetCreator.createWithDepartment(year, month, departmentId);
    }

    @GetMapping("/api/departments/all/timesheet")
    Timesheet generateTimesheet(@RequestParam Integer year,
                                @RequestParam Integer month) {
        return timesheetCreator.createWithAllDepartments(year, month);
    }
}
