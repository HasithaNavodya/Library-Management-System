package lk.ijse.finalProject.entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class Employee {
    private String employee_id;
    private String employee_name;
    private String status;
    private String address;
    private String contact_no;
}
