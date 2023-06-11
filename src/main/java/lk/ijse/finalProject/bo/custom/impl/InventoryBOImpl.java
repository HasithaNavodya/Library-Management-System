package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.InventoryBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.InventoryDAO;
import lk.ijse.finalProject.model.InventoryDTO;
import lk.ijse.finalProject.entity.Inventory;

import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryBOImpl implements InventoryBO {
    InventoryDAO inventoryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.INVENTORY);


    public ArrayList<InventoryDTO> getAllItem() throws SQLException {
        ArrayList<Inventory> allItem = inventoryDAO.getAll();
        ArrayList<InventoryDTO> arrayList = new ArrayList<>();
        for (Inventory i : allItem) {
            arrayList.add(new InventoryDTO(i.getItem_id(),
                                           i.getItem_name(),
                                           i.getCategory(),
                                           i.getQuantity()));
        }
        return arrayList;
    }


    public boolean saveItem(InventoryDTO inventoryDTO) throws SQLException {
        return inventoryDAO.save(new Inventory(inventoryDTO.getItem_id(),
                                               inventoryDTO.getItem_name(),
                                               inventoryDTO.getCategory(),
                                               inventoryDTO.getQuantity()));
    }

    public boolean deleteItem(String Item_id) throws SQLException {
        return inventoryDAO.delete(Item_id);
    }

    public boolean updateItem(InventoryDTO inventoryDTO) throws SQLException {
        return inventoryDAO.update(new Inventory(inventoryDTO.getItem_id(),
                                                 inventoryDTO.getItem_name(),
                                                 inventoryDTO.getCategory(),
                                                 inventoryDTO.getQuantity()));
    }

    public String getNextItemId() throws SQLException {
        return inventoryDAO.getNextId();
    }
}
