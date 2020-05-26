package space.banka.alyona.nauka.schedule.entrypoints.rest;

import lombok.Data;

@Data
public class TimesheetCellUpdateRequest {

    Integer month;
    Integer year;
    Integer day;
    Integer employeeId;
    String presence;

}
