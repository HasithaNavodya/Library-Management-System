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

    public Book(String name, String author, String category, int cupboard_no, String book_id) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.cupboard_no = cupboard_no;
        this.book_id = book_id;

    }
}
