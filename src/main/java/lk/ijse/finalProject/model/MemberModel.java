package lk.ijse.finalProject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.dto.MemberDTO;
import lk.ijse.finalProject.view.tdm.MemberTM;
import lk.ijse.finalProject.dao.custom.impl.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberModel {

    public static boolean add(MemberDTO member) throws SQLException {
        String sql = "INSERT INTO members Values(?,?,?,?,?,?)";
        return SQLUtil.execute(sql,
                member.getMember_id(),
                member.getName(),
                member.getAddress(),
                member.getGrade(),
                member.getMember_fee(),
                member.getContact_no());
    }

    public static boolean update(MemberDTO member) throws SQLException {
        String sql = "Update members SET name =?,address =?,grade=?,member_fee=?,contact_no=? WHERE member_id = ?";
        return SQLUtil.execute(sql,

                member.getName(),
                member.getAddress(),
                member.getGrade(),
                member.getMember_fee(),
                member.getContact_no(),
                member.getMember_id());

    }

    public static boolean delete(String id) throws SQLException {
        String sql ="DELETE FROM members WHERE member_id = ?";
        return  SQLUtil.execute(sql,id);
    }

    public static ObservableList<MemberTM> getAll() throws SQLException {
        String sql = "SELECT * FROM members";
        ResultSet resultSet = SQLUtil.execute(sql);
        ObservableList<MemberTM> obList = FXCollections.observableArrayList();


        while(resultSet.next()){
            obList.add(new MemberTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6)

            ));
        }
        return obList;
    }

    public static ObservableList<String> loadIds() throws SQLException {
        String sql = "SELECT member_id FROM members";
        ResultSet resultSet = SQLUtil.execute(sql);
        ObservableList<String> data = FXCollections.observableArrayList();

        while (resultSet.next()){
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static List<String> loadMemberIds() throws SQLException {
        String sql = "SELECT member_id FROM members";
        ResultSet resultSet = SQLUtil.execute(sql);

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }
}



