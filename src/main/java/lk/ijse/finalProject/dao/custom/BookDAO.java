package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.dto.BookDTO;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO extends CrudDAO<BookDTO,String> {
    public  List<String> loadBookIDs() throws SQLException;
    public List<String> getNextBookId() throws SQLException;
}
