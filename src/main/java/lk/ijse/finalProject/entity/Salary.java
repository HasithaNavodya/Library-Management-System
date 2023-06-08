package lk.ijse.finalProject.entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class Salary {
    private String salary_id;
    private Double bonus;
    private String date;
    private Double amount;
    private String employee_id;
}
