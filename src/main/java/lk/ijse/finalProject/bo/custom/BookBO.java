package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.BookDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookBO extends SuperBO {

    ArrayList<BookDTO> getAllBooks() throws SQLException, ClassNotFoundException;

    boolean saveBook(BookDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateBook(BookDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteBook(String id) throws SQLException, ClassNotFoundException;

    String generateNewBookID() throws SQLException, ClassNotFoundException;

}
