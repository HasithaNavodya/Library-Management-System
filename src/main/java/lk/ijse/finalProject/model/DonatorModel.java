package lk.ijse.finalProject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.dto.Donator;
import lk.ijse.finalProject.dto.tm.DonatorTM;
import lk.ijse.finalProject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonatorModel {

    public static boolean Save(Donator donator) throws SQLException {

        String sql= "INSERT INTO donators(donater_id,name,contact,date)" +
                "VALUES (?,?,?,?)";

        return CrudUtil.execute(
                sql,
                donator.getDonator_id(),
                donator.getDonator_name(),
                donator.getContact(),
                donator.getDate()
        );

    }

    public static List<String> loadDonatorIds() throws SQLException {
        String sql = "SELECT donater_id FROM donators";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static boolean delete(String donater_id) throws SQLException {
        String sql="DELETE FROM donators WHERE donater_id=?";

        return CrudUtil.execute(
                sql,
                donater_id
        );

    }

    public static boolean update(Donator donator) throws SQLException {
        String sql="UPDATE donators SET donater_id=?," +
                "name=?,contact=?,date=?";

        return CrudUtil.execute(

                sql,
                donator.getDonator_id(),
                donator.getDonator_name(),
                donator.getContact(),
                donator.getDate()

        );

    }

    public static ObservableList<DonatorTM> getAll() throws SQLException {

        String sql = "SELECT * FROM donators";

        ObservableList<DonatorTM> obList = FXCollections.observableArrayList();

        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()){

            obList.add(new DonatorTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));

        }
        return obList;
    }

}
