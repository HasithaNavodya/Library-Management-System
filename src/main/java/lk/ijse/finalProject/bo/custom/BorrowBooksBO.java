package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dao.custom.impl.util.SQLUtil;
import lk.ijse.finalProject.db.DBConnection;
import lk.ijse.finalProject.dto.BorrowBooksDTO;
import lk.ijse.finalProject.dto.DonatorDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BorrowBooksBO extends SuperBO {

   public  List<String> loadMemberIds() throws SQLException;

   public  List<String> loadBookIDs() throws SQLException;

   public String getNextIssueId() throws SQLException;

   public  boolean placeIssue(BorrowBooksDTO dto) throws SQLException;
}
