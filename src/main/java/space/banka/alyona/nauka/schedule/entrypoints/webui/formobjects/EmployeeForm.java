package space.banka.alyona.nauka.schedule.entrypoints.webui.formobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeForm {
    String name;
    String surname;
    String birthDate;
    String position;
    boolean remoteWork;
    String address;
    String department;
}
