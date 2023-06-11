package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;
import lk.ijse.finalProject.entity.Member;

import java.sql.SQLException;
import java.util.List;

public interface MemberDAO extends CrudDAO<Member,String> {
    public List<String> loadMemberIds() throws SQLException;
}
