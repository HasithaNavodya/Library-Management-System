package lk.ijse.finalProject.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Employee {
    private String employee_id;
    private String emplyee_name;
    private String status;
    private String address;
    private String contact;
}
