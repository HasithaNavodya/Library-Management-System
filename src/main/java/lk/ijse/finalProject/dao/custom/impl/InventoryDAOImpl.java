package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.InventoryDAO;
import lk.ijse.finalProject.dto.InventoryDTO;
import lk.ijse.finalProject.dao.custom.impl.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryDAOImpl implements InventoryDAO {

    public ArrayList<InventoryDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM inventory";

        ArrayList<InventoryDTO> allInventories = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            allInventories.add(new InventoryDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4)
            ));
        }
        return allInventories;
    }

    @Override
    public boolean save(InventoryDTO inventoryDTO) throws SQLException {
        return SQLUtil.execute("INSERT INTO inventory (Item_id,Item_name,category,quantity) VALUES (?,?,?,?)", inventoryDTO.getItem_id(),inventoryDTO.getItem_name(),inventoryDTO.getCategory(),inventoryDTO.getQuantity());
    }

    @Override
    public boolean delete(String Item_id) throws SQLException {
        return SQLUtil.execute("DELETE FROM inventory WHERE Item_id=?", Item_id);
    }

    @Override
    public boolean update(InventoryDTO inventoryDTO) throws SQLException {
        return SQLUtil.execute("UPDATE inventory SET Item_name=?, category=?, quantity=? WHERE Item_id=?", inventoryDTO.getItem_name(),inventoryDTO.getCategory(),inventoryDTO.getQuantity(),inventoryDTO.getItem_id());
    }

    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT Item_id FROM inventory ORDER BY Item_id DESC LIMIT 1;");
        if (rst.next()) {
            String item_id = rst.getString("Item_id");
            int newCustomerId = Integer.parseInt(item_id.replace("ITEM-", "")) + 1;
            return String.format("ITEM-%03d", newCustomerId);
        } else {
            return "ITEM-001";
        }
    }
}
