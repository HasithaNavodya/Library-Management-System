package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.DonateBooks;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DonateBooksDAO extends CrudDAO<DonateBooks,String> {
    public ArrayList<DonateBooks> getAll() throws SQLException;
}
