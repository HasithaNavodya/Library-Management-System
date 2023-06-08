package lk.ijse.finalProject.entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class Book {
    private String book_id;
    private String name;
    private String author;
    private String category;
    private int cupboard_no;
}
