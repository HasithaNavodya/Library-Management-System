package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.model.BorrowBooksDTO;

import java.sql.SQLException;
import java.util.List;

public interface BorrowBooksBO extends SuperBO {

   public  List<String> loadMemberIds() throws SQLException;

   public  List<String> loadBookIDs() throws SQLException;

   public String getNextIssueId() throws SQLException;

   public  boolean placeIssue(BorrowBooksDTO dto) throws SQLException;
}
