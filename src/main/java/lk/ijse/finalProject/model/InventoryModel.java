package lk.ijse.finalProject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.dto.InventoryDTO;
import lk.ijse.finalProject.view.tdm.InventoryTM;

import lk.ijse.finalProject.dao.custom.impl.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryModel {

    public static boolean Save(InventoryDTO inventory) throws SQLException {

        String sql= "INSERT INTO inventory(item_id,item_name,category,quantity)" +
                "VALUES (?,?,?,?)";

        return SQLUtil.execute(
                sql,
                inventory.getItem_id(),
                inventory.getItem_name(),
                inventory.getCategory(),
                inventory.getQuantity()
        );

    }

    public static boolean delete(String item_id) throws SQLException {
        String sql="DELETE FROM inventory WHERE item_id=?";

        return SQLUtil.execute(
                sql,
                item_id
        );

    }

    public static boolean update(InventoryDTO inventory) throws SQLException {
        String sql="UPDATE inventory SET item_id=?," +
                "item_name=?,category=?,quantity=?";

        return SQLUtil.execute(

                sql,
                inventory.getItem_id(),
                inventory.getItem_name(),
                inventory.getCategory(),
                inventory.getQuantity()

        );

    }

    public static ObservableList<InventoryTM> getAll() throws SQLException {

        String sql = "SELECT * FROM inventory";

        ObservableList<InventoryTM> obList = FXCollections.observableArrayList();

        ResultSet resultSet = SQLUtil.execute(sql);

        while (resultSet.next()){

            obList.add(new InventoryTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4)
            ));

        }
        return obList;
    }
}
