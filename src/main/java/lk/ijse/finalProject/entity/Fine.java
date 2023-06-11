package lk.ijse.finalProject.entity;

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
    private String description;
    private String member_id;

    public Fine(Double amount, String date, String description, String member_id, String fine_id) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.member_id = member_id;
        this.fine_id = fine_id;
    }
}