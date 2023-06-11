package lk.ijse.finalProject.model;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class FineDTO {
    private String fine_id;
    private Double amount;
    private String date;
    private String description;
    private String member_id;
}
