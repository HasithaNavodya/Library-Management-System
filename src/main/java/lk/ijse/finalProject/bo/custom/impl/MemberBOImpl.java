package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.MemberBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.MemberDAO;
import lk.ijse.finalProject.model.MemberDTO;
import lk.ijse.finalProject.entity.Member;

import java.sql.SQLException;
import java.util.ArrayList;

public class MemberBOImpl implements MemberBO {
    MemberDAO memberDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.MEMBER);

    public ArrayList<MemberDTO> getAllMember() throws SQLException {
        ArrayList<Member> allMember = memberDAO.getAll();
        ArrayList<MemberDTO> arrayList = new ArrayList<>();
        for (Member m : allMember) {
            arrayList.add(new MemberDTO(m.getMember_id(),
                                        m.getName(),
                                        m.getAddress(),
                                        m.getGrade(),
                                        m.getMember_fee(),
                                        m.getContact_no()));
        }
        return arrayList;    }

    public boolean saveMember(MemberDTO memberDTO) throws SQLException {
        return memberDAO.save(new Member(memberDTO.getMember_id(),
                                         memberDTO.getName(),
                                         memberDTO.getAddress(),
                                         memberDTO.getGrade(),
                                         memberDTO.getMember_fee(),
                                         memberDTO.getContact_no()));
    }

    public boolean deleteMember(String member_id) throws SQLException {
        return memberDAO.delete(member_id);
    }

    public boolean updateMember(MemberDTO memberDTO) throws SQLException {
        return memberDAO.update(new Member(memberDTO.getMember_id(),
                                           memberDTO.getName(),
                                           memberDTO.getAddress(),
                                           memberDTO.getGrade(),
                                           memberDTO.getMember_fee(),
                                           memberDTO.getContact_no()));
    }

    public String getNextMemberId() throws SQLException {
        return memberDAO.getNextId();
    }
}
