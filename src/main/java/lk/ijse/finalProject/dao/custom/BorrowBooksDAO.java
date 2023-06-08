package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.dao.custom.impl.util.SQLUtil;
import lk.ijse.finalProject.dto.BorrowBooksDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BorrowBooksDAO extends CrudDAO<BorrowBooksDTO,String> {
    public ArrayList<BorrowBooksDTO> getAll() throws SQLException;

}
