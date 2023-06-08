package lk.ijse.finalProject.entity;

public class MemberEntity {
    private String issue_id;
    private String member_id;
    private String book_Id;
    private String due_date;
    private String donation_id;
    private String donator_id;

    public MemberEntity(){

    }

    public MemberEntity(String issue_id, String member_id, String book_Id, String due_date, String donation_id, String donator_id) {
        this.issue_id = issue_id;
        this.member_id = member_id;
        this.book_Id = book_Id;
        this.due_date = due_date;
        this.donation_id = donation_id;
        this.donator_id = donator_id;
    }

    public String getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(String issue_id) {
        this.issue_id = issue_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getBook_Id() {
        return book_Id;
    }

    public void setBook_Id(String book_Id) {
        this.book_Id = book_Id;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getDonation_id() {
        return donation_id;
    }

    public void setDonation_id(String donation_id) {
        this.donation_id = donation_id;
    }

    public String getDonator_id() {
        return donator_id;
    }

    public void setDonator_id(String donator_id) {
        this.donator_id = donator_id;
    }
}
