package lk.ijse.finalProject.dto;

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
