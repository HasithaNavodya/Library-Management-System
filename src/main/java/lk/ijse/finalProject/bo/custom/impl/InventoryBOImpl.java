package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.InventoryDAO;
import lk.ijse.finalProject.dao.custom.impl.InventoryDAOImpl;
import lk.ijse.finalProject.dto.InventoryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryBOImpl {
    InventoryDAO inventoryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.INVENTORY);


    public ArrayList<InventoryDTO> getAllItem() throws SQLException {
        return inventoryDAO.getAll();
    }


    public boolean saveItem(InventoryDTO inventoryDTO) throws SQLException {
        return inventoryDAO.save(inventoryDTO);
    }

    public boolean deleteItem(String Item_id) throws SQLException {
        return inventoryDAO.delete(Item_id);
    }

    public boolean updateItem(InventoryDTO inventoryDTO) throws SQLException {
        return inventoryDAO.update(inventoryDTO);
    }

    public String getNextItemId() throws SQLException {
        return inventoryDAO.getNextId();
    }
}
