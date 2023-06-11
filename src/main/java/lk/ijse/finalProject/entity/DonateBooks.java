package lk.ijse.finalProject.entity;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class DonateBooks {
    private String donation_id;
    private String donator_id;
    private String book_id;
    private String name;
    private String author;
    private String category;
    private int cup_no;

    private List<DonateBooks> donateBooksList;

    public DonateBooks(String donation_id, String donator_id, String book_id) {
        this.donation_id = donation_id;
        this.donator_id = donator_id;
        this.book_id = book_id;
    }

    public DonateBooks(String donation_id, String donator_id, List<DonateBooks> donateBooksList) {
        this.donation_id = donation_id;
        this.donator_id = donator_id;
        this.donateBooksList = donateBooksList;
    }

    public DonateBooks(String id, String name, String author, String category, int cup_no) {
        this.book_id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.cup_no = cup_no;
    }
}
