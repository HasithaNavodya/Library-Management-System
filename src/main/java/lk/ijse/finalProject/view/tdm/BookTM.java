package lk.ijse.finalProject.view.tdm;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class BookTM {

    private String book_id;
    private String name;
    private String author;
    private String category;
    private int cupboard_no;
}
