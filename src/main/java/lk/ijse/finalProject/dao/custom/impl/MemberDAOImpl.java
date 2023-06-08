package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.MemberDAO;
import lk.ijse.finalProject.dto.MemberDTO;
import lk.ijse.finalProject.dao.custom.impl.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {
    public ArrayList<MemberDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM members";

        ArrayList<MemberDTO> allMember = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            allMember.add(new MemberDTO(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getDouble(5),
                resultSet.getString(6)
            ));
        }
        return allMember;
    }

    public  List<String> loadMemberIds() throws SQLException {
        String sql = "SELECT member_id FROM members";
        ResultSet resultSet = SQLUtil.execute(sql);

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    @Override
    public boolean save(MemberDTO memberDTO) throws SQLException {
        return SQLUtil.execute("INSERT INTO members (member_id,name,address,grade,member_fee,contact_no) VALUES (?,?,?,?,?,?)", memberDTO.getMember_id(),memberDTO.getName(),memberDTO.getAddress(),memberDTO.getGrade(),memberDTO.getMember_fee(),memberDTO.getContact_no());
    }

    @Override
    public boolean delete(String member_id) throws SQLException {
        return SQLUtil.execute("DELETE FROM members WHERE member_id=?", member_id);
    }

    @Override
    public boolean update(MemberDTO memberDTO) throws SQLException {
        return SQLUtil.execute("UPDATE members SET name=?, address=?, grade=?, member_fee=?, contact_no=? WHERE member_id=?", memberDTO.getName(),memberDTO.getAddress(),memberDTO.getGrade(),memberDTO.getMember_fee(),memberDTO.getContact_no(),memberDTO.getMember_id());
    }

    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT member_id FROM members ORDER BY member_id DESC LIMIT 1;");
        if (rst.next()) {
            String member_id = rst.getString("member_id");
                int newCustomerId = Integer.parseInt(member_id.replace("M-", "")) + 1;
            return String.format("M-%03d", newCustomerId);
        } else {
            return "M-001";
        }
    }
}
