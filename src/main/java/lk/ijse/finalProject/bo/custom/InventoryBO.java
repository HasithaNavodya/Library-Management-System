package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.model.InventoryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InventoryBO extends SuperBO {

    public ArrayList<InventoryDTO> getAllItem() throws SQLException;

    public boolean saveItem(InventoryDTO inventoryDTO) throws SQLException;

    public boolean deleteItem(String Item_id) throws SQLException;

    public boolean updateItem(InventoryDTO inventoryDTO) throws SQLException;

    public String getNextItemId() throws SQLException;
}
