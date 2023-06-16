package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.BorrowBooksBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.BookDAO;
import lk.ijse.finalProject.dao.custom.BorrowBooksDAO;
import lk.ijse.finalProject.dao.custom.MemberDAO;
import lk.ijse.finalProject.dao.custom.impl.BookDAOImpl;
import lk.ijse.finalProject.dao.custom.impl.BorrowBooksDAOImpl;
import lk.ijse.finalProject.dao.custom.impl.MemberDAOImpl;
import lk.ijse.finalProject.dao.custom.impl.util.SQLUtil;
import lk.ijse.finalProject.db.DBConnection;
import lk.ijse.finalProject.model.BorrowBooksDTO;
import lk.ijse.finalProject.model.DonatorDTO;
import lk.ijse.finalProject.entity.BorrowBooks;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowBooksBOImpl implements BorrowBooksBO {
    BorrowBooksDAO borrowBooksDAO = new BorrowBooksDAOImpl();
    MemberDAO memberDAO = new MemberDAOImpl();
    BookDAO bookDAO = new BookDAOImpl();

    public  List<String> loadMemberIds() throws SQLException{
        return memberDAO.loadMemberIds();
    }

    public  List<String> loadBookIDs() throws SQLException {
       return bookDAO.loadBookIDs();
    }


    public String getNextIssueId() throws SQLException {
        return borrowBooksDAO.getNextId();
    }

    public  boolean placeIssue(BorrowBooksDTO dto) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

//            boolean isBookIssuingSaved = saveDonation(issue_id,mem_id,due_date,borrowBooksList);
//            if (isBookIssuingSaved) {
//                System.out.println("isBookIssuingSaved");
//                boolean isBookRemoved = BookModel.deleteBooks(borrowBooksList);
//                if (isBookRemoved) {
//                    System.out.println("isBookRemoved");
//                    con.commit();
//                    return true;
//                }
//            }
            for (BorrowBooksDTO borrowBooksDTO : dto.getBorrowBooksList()) {
                boolean isBookIssuingSaved = borrowBooksDAO.save(new BorrowBooks(borrowBooksDTO.getIssue_id(),
                                                                                 dto.getMember_id(),
                                                                                 borrowBooksDTO.getBook_id(),
                                                                                 dto.getDate()));
                bookDAO.delete(borrowBooksDTO.getBook_id());
            }
            con.commit();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }
}