package lk.ijse.finalProject.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Fine {
    private String fine_id;
    private Double amount;
    private String date;
    private String desription;
    private String mem_id;
}
