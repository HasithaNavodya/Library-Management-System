package lk.ijse.finalProject.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class SalaryDTO {
    private String salary_id;
    private Double bonus;
    private String date;
    private Double amount;
    private String employee_id;



    public SalaryDTO(String salary_id, Double bonus, String date, Double amount) {
        this.salary_id = salary_id;
        this.bonus = bonus;
        this.date = date;
        this.amount = amount;
    }
}
