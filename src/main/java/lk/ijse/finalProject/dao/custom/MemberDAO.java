package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.dto.MemberDTO;

import java.sql.SQLException;
import java.util.List;

public interface MemberDAO extends CrudDAO<MemberDTO,String> {
    public List<String> loadMemberIds() throws SQLException;
}
