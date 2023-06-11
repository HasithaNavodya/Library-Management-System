package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.BorrowBooksDAO;
import lk.ijse.finalProject.dao.custom.impl.util.SQLUtil;
import lk.ijse.finalProject.dto.BorrowBooksDTO;
import lk.ijse.finalProject.dto.DonatorDTO;
import lk.ijse.finalProject.entity.BorrowBooks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BorrowBooksDAOImpl implements BorrowBooksDAO {

    @Override
    public ArrayList<BorrowBooks> getAll() throws SQLException {
        String sql = "SELECT * FROM borrow_books";

        ArrayList<BorrowBooks> allIssueBook = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            allIssueBook.add(new BorrowBooks(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return allIssueBook;
    }

    @Override
    public boolean save(BorrowBooks book) throws SQLException {
        return SQLUtil.execute("INSERT INTO borrow_books(issue_id,member_id,book_id,due_date)" + "VALUES(?, ?, ?,?)", book.getIssue_id(),book.getMember_id(),book.getBook_id(),book.getDate());
    }

    @Override
    public boolean delete(String book_id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(BorrowBooks book) throws SQLException {
        return false;
    }

    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT issue_id FROM borrow_books ORDER BY issue_id DESC LIMIT 1");
        if (rst.next()) {
            String issue_id = rst.getString("issue_id");
            int newCustomerId = Integer.parseInt(issue_id.replace("I-", "")) + 1;
            return String.format("I-%03d", newCustomerId);
        } else {
            return "I-001";
        }

    }


}
