package lk.ijse.finalProject.model;

import lk.ijse.finalProject.db.DBConnection;
import lk.ijse.finalProject.dto.BorrowBooks;
import lk.ijse.finalProject.dto.DonateBooks;
import lk.ijse.finalProject.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BorrowBooksModel {

    public static String getNextIssueId() throws SQLException {
        String sql = "SELECT issue_id FROM borrow_books ORDER BY issue_id DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("ISS-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit = String.format("%03d", id);
            return "ISS-" + digit;
        }
        return "ISS-001";
    }

    public static boolean placeIssue(String issue_id, String mem_id, String due_date, List<BorrowBooks> borrowBooksList) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isBookIssuingSaved = saveDonation(issue_id,mem_id,due_date,borrowBooksList);
            if (isBookIssuingSaved) {
                System.out.println("isBookIssuingSaved");
                boolean isBookRemoved = BookModel.deleteBooks(borrowBooksList);
                if (isBookRemoved) {
                    System.out.println("isBookRemoved");
                    con.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }

    private static boolean saveDonation(String issue_id, String mem_id, String due_date, List<BorrowBooks> borrowBooksList) throws SQLException {
        for(BorrowBooks borrowBooks : borrowBooksList) {
            if(!save(issue_id,mem_id,due_date, borrowBooks)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(String issue_id, String mem_id, String due_date, BorrowBooks borrowBooks) throws SQLException {
        String sql = "INSERT INTO borrow_books(issue_id,member_id,book_id,due_date)" +
                "VALUES(?, ?, ?,?)";

        return CrudUtil.execute(
                sql,
                issue_id,
                mem_id,
                borrowBooks.getBook_id(),
                due_date
        );
    }
}
