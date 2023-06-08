package lk.ijse.finalProject.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class DonateBooks {
    private String donation_id;
    private String donator_id;
    private String book_id;
    private String name;
    private String author;
    private String category;
    private int cup_no;
}
