package lk.ijse.finalProject.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class FineTM {
    private String fine_id;
    private Double amount;
    private String date;
    private String desription;
    private String mem_id;
}
