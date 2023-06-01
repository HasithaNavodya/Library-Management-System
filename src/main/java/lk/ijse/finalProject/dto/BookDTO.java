package lk.ijse.finalProject.dto;

public class BookDTO {
    private String book_id;
    private String name;
    private String author;
    private String category;
    private int cupboard_no;

    public BookDTO(){

    }

    public BookDTO(String book_id, String name, String author, String category, int cupboard_no) {
        this.book_id = book_id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.cupboard_no = cupboard_no;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCupboard_no() {
        return cupboard_no;
    }

    public void setCupboard_no(int cupboard_no) {
        this.cupboard_no = cupboard_no;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "book_id='" + book_id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", cupboard_no=" + cupboard_no +
                '}';
    }
}
