package space.banka.alyona.nauka.schedule.entrypoints.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.banka.alyona.nauka.schedule.db.converters.PresenceConverter;
import space.banka.alyona.nauka.schedule.db.crud.*;
import space.banka.alyona.nauka.schedule.db.entities.*;
import space.banka.alyona.nauka.schedule.domain.Timesheet;
import space.banka.alyona.nauka.schedule.domain.TimesheetCreator;

@RestController
public class TimesheetRestController {

    @Autowired
    private TimesheetCreator timesheetCreator;

    @Autowired
    private EmployeeDayRepository employeeDayRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PresenceConverter presenceConverter;

    @Autowired
    private DayService dayService;

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
        final Day.Id dayId = Day.Id.builder()
                .year(request.year)
                .month(request.month)
                .day(request.day)
                .build();
        final EmployeeDay employeeDay = employeeDayRepository
                .findByEmployeeIdAndDayId(request.employeeId, dayId)
                .orElseGet(() -> makeDefaultEmployeeDay(dayId, request.employeeId));
        final Presence presence = presenceConverter.convertToEntityAttribute(request.presence);
        employeeDay.setPresence(presence);
        employeeDayRepository.save(employeeDay);
    }

    private EmployeeDay makeDefaultEmployeeDay(Day.Id dayId, Integer employeeId) {
        return EmployeeDay.builder()
                .id(EmployeeDay.Id.builder()
                        .dayId(dayId)
                        .employeeId(employeeId)
                        .build())
                .day(dayService.findOrCreate(dayId.year, dayId.month, dayId.day))
                .employee(employeeRepository.findById(employeeId).orElseThrow())
                .build();
    }
}
