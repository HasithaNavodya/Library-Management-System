package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.BookDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookBO extends SuperBO {

    ArrayList<BookDTO> getAllBooks() throws SQLException, ClassNotFoundException;

    boolean saveBook(BookDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(BookDTO dto) throws SQLException, ClassNotFoundException;

    boolean existByID(String id) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    String generateNewCustomerID() throws SQLException, ClassNotFoundException;
}
