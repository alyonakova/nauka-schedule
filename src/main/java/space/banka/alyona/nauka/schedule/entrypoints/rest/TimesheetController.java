package space.banka.alyona.nauka.schedule.entrypoints.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.banka.alyona.nauka.schedule.entrypoints.webui.Timesheet;
import space.banka.alyona.nauka.schedule.entrypoints.webui.TimesheetCreator;

@RestController
public class TimesheetController {

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
