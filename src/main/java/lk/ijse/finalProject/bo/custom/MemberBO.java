package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.MemberDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MemberBO extends SuperBO {

    public ArrayList<MemberDTO> getAllMember() throws SQLException;

    public boolean saveMember(MemberDTO memberDTO) throws SQLException;

    public boolean deleteMember(String member_id) throws SQLException;

    public boolean updateMember(MemberDTO memberDTO) throws SQLException;

    public String getNextMemberId() throws SQLException;

}
