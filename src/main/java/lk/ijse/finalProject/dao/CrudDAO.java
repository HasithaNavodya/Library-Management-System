package lk.ijse.finalProject.dao;


import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T, t> extends SuperDAO{

     ArrayList<T> getAll() throws SQLException;
     boolean save(T book) throws SQLException;

     boolean delete(T book_id) throws SQLException;

     boolean update(T book) throws SQLException;

     String getNextId() throws SQLException;


}
