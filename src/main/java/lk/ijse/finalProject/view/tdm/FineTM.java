package lk.ijse.finalProject.view.tdm;

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
    private String description;
    private String mem_id;
}
