package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO extends CrudDAO<Book,String> {
    public  List<String> loadBookIDs() throws SQLException;
    public List<String> getNextBookId() throws SQLException;
}
