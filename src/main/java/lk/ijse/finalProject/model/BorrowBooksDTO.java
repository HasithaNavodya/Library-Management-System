package lk.ijse.finalProject.model;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class BorrowBooksDTO {
    private String issue_id;
    private String member_id;
    private String book_id;
    private String date;

    private List<BorrowBooksDTO> borrowBooksList;

    public BorrowBooksDTO(String issue_id, String mem_id, String due_date, List<BorrowBooksDTO> borrowBooksList) {

    this.issue_id = issue_id;
    this.member_id = mem_id;
    this.date = due_date;
    this.borrowBooksList = borrowBooksList;
    }

    public BorrowBooksDTO(String issue_id, String member_id, String book_id, String date) {
        this.issue_id = issue_id;
        this.member_id = member_id;
        this.book_id = book_id;
        this.date = date;
    }
}
