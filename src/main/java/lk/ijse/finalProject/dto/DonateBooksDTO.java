package lk.ijse.finalProject.dto;
import javafx.scene.control.Button;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class DonateBooksDTO {
    private String donation_id;
    private String donator_id;
    private String book_id;
    private String name;
    private String author;
    private String category;
    private int cup_no;

    private List<DonateBooksDTO> donateBooksList;

    public DonateBooksDTO(String id, String name, String author, String category, int cup_no) {
        this.book_id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.cup_no = cup_no;
    }

    public DonateBooksDTO(String donation_id, String donator_id, String book_id) {
        this.donation_id = donation_id;
        this.donator_id = donator_id;
        this.book_id = book_id;
    }

    public DonateBooksDTO(String donation_id, String donator_id, List<DonateBooksDTO> donateBooksList) {
        this.donation_id = donation_id;
        this.donator_id = donator_id;
        this.donateBooksList = donateBooksList;
    }
}
