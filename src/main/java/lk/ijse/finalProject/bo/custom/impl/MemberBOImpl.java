package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.MemberDAO;
import lk.ijse.finalProject.dao.custom.impl.MemberDAOImpl;
import lk.ijse.finalProject.dto.InventoryDTO;
import lk.ijse.finalProject.dto.MemberDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class MemberBOImpl {
    MemberDAO memberDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.MEMBER);

    public ArrayList<MemberDTO> getAllMember() throws SQLException {
        return memberDAO.getAll();
    }

    public boolean saveMember(MemberDTO memberDTO) throws SQLException {
        return memberDAO.save(memberDTO);
    }

    public boolean deleteMember(String member_id) throws SQLException {
        return memberDAO.delete(member_id);
    }

    public boolean updateMember(MemberDTO memberDTO) throws SQLException {
        return memberDAO.update(memberDTO);
    }

    public String getNextMemberId() throws SQLException {
        return memberDAO.getNextId();
    }
}
