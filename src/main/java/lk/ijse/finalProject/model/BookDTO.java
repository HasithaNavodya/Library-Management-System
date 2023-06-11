package lk.ijse.finalProject.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class BookDTO {
    private String book_id;
    private String name;
    private String author;
    private String category;
    private int cupboard_no;


}
