package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.model.DonateBooksDTO;

import java.sql.SQLException;
import java.util.List;

public interface DonateBooksBO extends SuperBO {

    public String getNextDonationId() throws SQLException;

    public String getNextBookId() throws SQLException;

    public  List<String> loadDonatorIds() throws SQLException;

    public  boolean placeDonation(DonateBooksDTO dto) throws SQLException;
}
