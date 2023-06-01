package lk.ijse.finalProject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.dto.Fine;
import lk.ijse.finalProject.dto.tm.FineTM;
import lk.ijse.finalProject.dto.tm.MemberTM;
import lk.ijse.finalProject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FineModel {
    public static boolean add(Fine fine) throws SQLException {
        String sql ="INSERT  INTO fines Values(?,?,?,?,?)";
        return CrudUtil.execute(sql,
                fine.getFine_id(),
                fine.getAmount(),
                fine.getDate(),
                fine.getDesription(),
                fine.getMem_id());

    }

    public static boolean update(Fine fine) throws SQLException {
        String sql = "Update fines SET amount =?,date =?,description=?,member_id=? WHERE fine_id = ?";
        return CrudUtil.execute(sql,

                fine.getAmount(),
                fine.getDate(),
                fine.getDesription(),
                fine.getMem_id(),
                fine.getFine_id());

    }

    public static boolean delete(String id) throws SQLException {
        String sql ="DELETE FROM fines WHERE fine_id = ?";
        return  CrudUtil.execute(sql,id);
    }

    public static ObservableList<FineTM> getAll() throws SQLException {
        String sql = "SELECT * FROM fines";
        ResultSet resultSet = CrudUtil.execute(sql);
        ObservableList<FineTM> obList = FXCollections.observableArrayList();


        while(resultSet.next()){
            obList.add(new FineTM(
                    resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)


            ));
        }
        return obList;
    }
}
