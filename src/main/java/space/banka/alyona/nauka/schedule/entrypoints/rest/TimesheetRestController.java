package space.banka.alyona.nauka.schedule.entrypoints.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.banka.alyona.nauka.schedule.db.converters.PresenceConverter;
import space.banka.alyona.nauka.schedule.db.crud.DepartmentRepository;
import space.banka.alyona.nauka.schedule.db.crud.EmployeeDayRepository;
import space.banka.alyona.nauka.schedule.db.crud.EmployeeRepository;
import space.banka.alyona.nauka.schedule.db.crud.PositionRepository;
import space.banka.alyona.nauka.schedule.db.entities.*;
import space.banka.alyona.nauka.schedule.domain.Timesheet;
import space.banka.alyona.nauka.schedule.domain.TimesheetCreator;

import java.util.Optional;

@RestController
public class TimesheetRestController {

    @Autowired
    private TimesheetCreator timesheetCreator;

    @Autowired
    private EmployeeDayRepository employeeDayRepository;

    @Autowired
    private PresenceConverter presenceConverter;

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

    @PostMapping("/api/timesheet")
    void updateTimesheetCell(@RequestBody TimesheetCellUpdateRequest request) {
        final EmployeeDay employeeDay = employeeDayRepository.findByEmployeeIdAndDayId(request.employeeId, Day.Id.builder()
                .year(request.year)
                .month(request.month)
                .day(request.day)
                .build());
        final Presence presence = presenceConverter.convertToEntityAttribute(request.presence);
        employeeDay.setPresence(presence);
        employeeDayRepository.save(employeeDay);
    }
}
