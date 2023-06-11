package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.BorrowBooks;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BorrowBooksDAO extends CrudDAO<BorrowBooks,String> {
    public ArrayList<BorrowBooks> getAll() throws SQLException;

}
