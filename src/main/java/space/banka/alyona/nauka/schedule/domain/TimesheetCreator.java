package space.banka.alyona.nauka.schedule.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.banka.alyona.nauka.schedule.db.crud.DayService;
import space.banka.alyona.nauka.schedule.db.crud.EmployeeDayRepository;
import space.banka.alyona.nauka.schedule.db.crud.EmployeeRepository;
import space.banka.alyona.nauka.schedule.db.entities.Day;
import space.banka.alyona.nauka.schedule.db.entities.Employee;
import space.banka.alyona.nauka.schedule.db.entities.EmployeeDay;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TimesheetCreator {

    @Autowired
    private DayService dayService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeDayRepository employeeDayRepository;

    public Timesheet createTimesheet(List<Employee> employees, Integer year, Integer month) {
        List<Day> days = dayService.findByYearAndMonth(year, month);
        YearMonth.of(year, month).lengthOfMonth();
        List<TimesheetRow> rows = new ArrayList<>();
        for (Employee employee : employees) {
            rows.add(createRow(employee, days, year, month));
        }
        return new Timesheet(rows, days);
    }

    public Timesheet createWithAllDepartments(Integer years, Integer month){
        List<Employee> employees = employeeRepository.findAll();
        return createTimesheet(employees, years, month);
    }

    public Timesheet createWithDepartment(Integer year, Integer month, Integer department) {
        List<Employee> employees = employeeRepository.findByDepartmentId(department);
        return createTimesheet(employees, year, month);
    }

    public TimesheetRow createRow(Employee employee, List<Day> days, Integer year, Integer month) {
        List<EmployeeDay> employeeDays = employeeDayRepository.findByEmployeeIdAndDayIdYearAndDayIdMonth(
                employee.getId(), year, month
        );
        Map<Day, EmployeeDay> map = new HashMap<>();
        for (Day day : days) {
            for (EmployeeDay employeeDay : employeeDays) {
                if (employeeDay.getDay().equals(day)) {
                    map.put(day, employeeDay);
                }
            }
        }
        return new TimesheetRow(employee, map);
    }

}
