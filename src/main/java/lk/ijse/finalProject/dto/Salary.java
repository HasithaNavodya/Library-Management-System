package lk.ijse.finalProject.dto;

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
}
