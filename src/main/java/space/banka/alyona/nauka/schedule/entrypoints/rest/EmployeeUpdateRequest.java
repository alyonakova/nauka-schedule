package space.banka.alyona.nauka.schedule.entrypoints.rest;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeUpdateRequest {
    Integer id;
    String name;
    String surname;
    LocalDate birthDate;
    String positionName;
    boolean remoteWork;
    String address;
}
