package lk.ijse.finalProject.dto.tm;
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
    private String catagory;
    private int cupboard_no;
}
