package lk.ijse.finalProject.model;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class EmployeeDTO {
    private String employee_id;
    private String employee_name;
    private String status;
    private String address;
    private String contact_no;


}
