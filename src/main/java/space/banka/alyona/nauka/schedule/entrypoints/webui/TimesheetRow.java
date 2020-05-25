package space.banka.alyona.nauka.schedule.entrypoints.webui;

import space.banka.alyona.nauka.schedule.db.entities.Day;
import space.banka.alyona.nauka.schedule.db.entities.Employee;
import space.banka.alyona.nauka.schedule.db.entities.EmployeeDay;
import space.banka.alyona.nauka.schedule.db.entities.Presence;

import java.util.HashMap;
import java.util.Map;

public class TimesheetRow {

    private Employee employee;
    private Map<Day, EmployeeDay> employeeDays;

    public TimesheetRow(Employee employee, Map<Day, EmployeeDay> employeeDays) {
        this.employee = employee;
        this.employeeDays = employeeDays;
    }

    public Employee getEmployee() {
        return employee;
    }

    public EmployeeDay findDay(Day day) {
        return employeeDays.getOrDefault(day, createDefaultDay(day));
    }

    public EmployeeDay createDefaultDay(Day day) {
        EmployeeDay employeeDay = new EmployeeDay();
        employeeDay.setEmployee(employee);
        employeeDay.setDay(day);
        employeeDay.setPresence(Presence.DEFAULT);
        return employeeDay;
    }

    public PresenceScore getTotalPresenceScore() {
        Map<Presence, Integer> result = new HashMap<>();
        for (Map.Entry<Day, EmployeeDay> day : employeeDays.entrySet()) {
            if (result.containsKey(day.getValue().getPresence())) {
                result.put(day.getValue().getPresence(), result.get(day.getValue().getPresence()) + 1);
            } else {
                result.put(day.getValue().getPresence(), 1);
            }
        }
        return new PresenceScore(result);
    }

}
