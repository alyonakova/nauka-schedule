package space.banka.alyona.nauka.schedule.entrypoints.rest;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EmployeeUpdateRequest {
    String name;
    String surname;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate birthDate;
    String positionName;
    Boolean remoteWork;
    String address;
}
