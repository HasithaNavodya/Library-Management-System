package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.DonateBooksDTO;

import java.sql.SQLException;
import java.util.List;

public interface DonateBooksBO extends SuperBO {

    public String getNextDonationId() throws SQLException;
    public List<String> getNextBookId() throws SQLException;
    public List<String> loadDonatorIds() throws SQLException;
    public boolean placeDonation(DonateBooksDTO dto) throws SQLException;
}
