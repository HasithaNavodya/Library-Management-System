package lk.ijse.finalProject.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class SalaryTM {
    private String salary_id;
    private Double bonus;
    private String date;
    private Double amount;
}
