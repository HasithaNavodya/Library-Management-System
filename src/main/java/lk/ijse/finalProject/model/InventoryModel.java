package lk.ijse.finalProject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.dto.Donator;
import lk.ijse.finalProject.dto.Inventory;
import lk.ijse.finalProject.dto.tm.DonatorTM;
import lk.ijse.finalProject.dto.tm.InventoryTM;
import lk.ijse.finalProject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryModel {

    public static boolean Save(Inventory inventory) throws SQLException {

        String sql= "INSERT INTO inventory(item_id,item_name,catagory,quantity)" +
                "VALUES (?,?,?,?)";

        return CrudUtil.execute(
                sql,
                inventory.getItem_id(),
                inventory.getItem_name(),
                inventory.getCatagory(),
                inventory.getQuantity()
        );

    }

    public static boolean delete(String item_id) throws SQLException {
        String sql="DELETE FROM inventory WHERE item_id=?";

        return CrudUtil.execute(
                sql,
                item_id
        );

    }

    public static boolean update(Inventory inventory) throws SQLException {
        String sql="UPDATE inventory SET item_id=?," +
                "item_name=?,catagory=?,quantity=?";

        return CrudUtil.execute(

                sql,
                inventory.getItem_id(),
                inventory.getItem_name(),
                inventory.getCatagory(),
                inventory.getQuantity()

        );

    }

    public static ObservableList<InventoryTM> getAll() throws SQLException {

        String sql = "SELECT * FROM inventory";

        ObservableList<InventoryTM> obList = FXCollections.observableArrayList();

        ResultSet resultSet = CrudUtil.execute(sql);

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
