package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.model.BookDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookBO extends SuperBO {

    public ArrayList<BookDTO> getAllBooks() throws SQLException, ClassNotFoundException;
    public boolean saveBook(BookDTO bookDTO) throws SQLException;

    public boolean deleteBook(String book_id) throws SQLException;

    public boolean updateBook(BookDTO bookDTO) throws SQLException;

    public String getNextBookId() throws SQLException;
}
