package lk.ijse.finalProject.entity;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class BorrowBooks {
    private String issue_id;
    private String member_id;
    private String book_id;
    private String due_date;

    private List<BorrowBooks> borrowBooksList;

    public BorrowBooks(String issue_id, String mem_id, String due_date, List<BorrowBooks> borrowBooksList) {
        this.issue_id = issue_id;
        this.member_id = mem_id;
        this.due_date = due_date;
        this.borrowBooksList = borrowBooksList;
    }


    public BorrowBooks(String issue_id, String member_id, String book_id, String due_date) {
        this.issue_id = issue_id;
        this.member_id = member_id;
        this.book_id = book_id;
        this.due_date = due_date;
    }


    public BorrowBooks(String issue_id, String member_id, String due_date) {
        this.issue_id = issue_id;
        this.member_id = member_id;
        this.due_date = due_date;
    }
}