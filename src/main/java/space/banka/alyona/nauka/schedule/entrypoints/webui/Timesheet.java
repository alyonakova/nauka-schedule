package space.banka.alyona.nauka.schedule.entrypoints.webui;

import space.banka.alyona.nauka.schedule.db.entities.Day;

import java.util.List;

public class Timesheet {
    List<TimesheetRow> rows;
    List<Day> days;

    public Timesheet(List<TimesheetRow> rows, List<Day> days) {
        this.rows = rows;
        this.days = days;
    }

    public List<TimesheetRow> getRows() {
        return rows;
    }

    public List<Day> getDays() {
        return days;
    }
}
