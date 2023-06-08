package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.FineDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InventoryBO extends SuperBO {

    ArrayList<FineDTO> getAllItem() throws SQLException, ClassNotFoundException;

    boolean saveItem(FineDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateItem(FineDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;

    String generateNewItemID() throws SQLException, ClassNotFoundException;
}
