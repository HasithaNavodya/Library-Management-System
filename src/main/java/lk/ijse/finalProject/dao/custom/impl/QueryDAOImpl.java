package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.QueryDAO;
import lk.ijse.finalProject.dao.custom.impl.util.SQLUtil;
import lk.ijse.finalProject.entity.MemberEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<MemberEntity> searchBorrowBooksByBook_id(String book_id) throws SQLException, ClassNotFoundException {
        ArrayList<MemberEntity> allRecords = new ArrayList<>();
        String sql = "select borrow_books.issue_id , borrow_books.member_id , borrow_books.book_id , borrow_books.due_date , book_donation.donation_id , book_donation.donator_id , book_donation.book_id from  borrow_books  inner join book_donation on borrow_books.book_id = book_donation.book_id where borrow_books.book_id = ?";
        ResultSet rst = SQLUtil.execute(sql, book_id);
        while (rst.next()) {
            String issue_id = rst.getString(1);
            String member_id = rst.getString(2);
            String book_Id = rst.getString(3);
            String due_date = rst.getString(5);
            String donation_id = rst.getString(6);
            String donator_id = rst.getString(7);
            MemberEntity memberEntity = new MemberEntity(issue_id, member_id, book_Id, due_date, donation_id, donator_id );
            allRecords.add(memberEntity);
        }
        return allRecords;
    }
}
