package lk.ijse.finalProject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.dto.DonatorDTO;
import lk.ijse.finalProject.view.tdm.DonatorTM;

import lk.ijse.finalProject.dao.custom.impl.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonatorModel {

    public static ObservableList<DonatorTM> getAll() throws SQLException {

        String sql = "SELECT * FROM donators";

        ObservableList<DonatorTM> obList = FXCollections.observableArrayList();

        ResultSet resultSet = SQLUtil.execute(sql);

        while (resultSet.next()){

            obList.add(new DonatorTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));

        }
        return obList;
    }

    public static boolean save(DonatorDTO donator) throws SQLException {

        String sql= "INSERT INTO donators(donator_id,name,contact,date)" +
                "VALUES (?,?,?,?)";

        return SQLUtil.execute(
                sql,
                donator.getDonator_id(),
                donator.getName(),
                donator.getContact(),
                donator.getDate()
        );

    }

    public static List<String> loadDonatorIds() throws SQLException {
        String sql = "SELECT donator_id FROM donators";
        ResultSet resultSet = SQLUtil.execute(sql);

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static boolean delete(String donater_id) throws SQLException {
        String sql="DELETE FROM donators WHERE donator_id=?";

        return SQLUtil.execute(
                sql,
                donater_id
        );

    }

    public static boolean update(DonatorDTO donator) throws SQLException {
        String sql="UPDATE donators SET donator_id=?," +
                "name=?,contact=?,date=?";

        return SQLUtil.execute(

                sql,
                donator.getDonator_id(),
                donator.getName(),
                donator.getContact(),
                donator.getDate()

        );

    }



}
