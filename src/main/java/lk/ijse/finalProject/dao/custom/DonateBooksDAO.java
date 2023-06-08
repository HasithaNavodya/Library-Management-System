package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.dto.BorrowBooksDTO;
import lk.ijse.finalProject.dto.DonateBooksDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DonateBooksDAO extends CrudDAO<DonateBooksDTO,String> {
    public ArrayList<DonateBooksDTO> getAll() throws SQLException;
}
