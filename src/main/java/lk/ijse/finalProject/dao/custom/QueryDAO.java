package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.SuperDAO;
import lk.ijse.finalProject.entity.MemberEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    public ArrayList<MemberEntity> searchBorrowBooksByBook_id(String book_id) throws SQLException, ClassNotFoundException;
}
