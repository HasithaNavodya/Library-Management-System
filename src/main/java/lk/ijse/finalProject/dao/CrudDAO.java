package lk.ijse.finalProject.dao;


import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T, t> extends SuperDAO{

      ArrayList<T> getAll() throws SQLException;
     
     boolean save(T t) throws SQLException;

     boolean delete(String id) throws SQLException;

     boolean update(T t) throws SQLException;

     String getNextId() throws SQLException;


}
