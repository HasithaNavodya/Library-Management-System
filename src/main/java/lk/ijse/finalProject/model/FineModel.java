package lk.ijse.finalProject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.dto.FineDTO;
import lk.ijse.finalProject.view.tdm.FineTM;
import lk.ijse.finalProject.dao.custom.impl.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FineModel {
    public static boolean add(FineDTO fine) throws SQLException {
        String sql ="INSERT  INTO fines Values(?,?,?,?,?)";
        return SQLUtil.execute(sql,
                fine.getFine_id(),
                fine.getAmount(),
                fine.getDate(),
                fine.getDescription(),
                fine.getMember_id());

    }

    public static boolean update(FineDTO fineDTO) throws SQLException {
        String sql = "Update fines SET amount =?,date =?,description=?,member_id=? WHERE fine_id = ?";
        return SQLUtil.execute(sql,

                fineDTO.getAmount(),
                fineDTO.getDate(),
                fineDTO.getDescription(),
                fineDTO.getMember_id(),
                fineDTO.getFine_id());

    }

    public static boolean delete(String id) throws SQLException {
        String sql ="DELETE FROM fines WHERE fine_id = ?";
        return  SQLUtil.execute(sql,id);
    }

    public static ObservableList<FineTM> getAll() throws SQLException {
        String sql = "SELECT * FROM fines";
        ResultSet resultSet = SQLUtil.execute(sql);
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
