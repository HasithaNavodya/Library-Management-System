package lk.ijse.finalProject.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class BorrowBooks {
    private String issue_id;
    private String member_id;
    private String book_id;
    private String date;
}