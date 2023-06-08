package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.DonatorDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DonatorBO extends SuperBO {

    ArrayList<DonatorDTO> getAllDonator() throws SQLException, ClassNotFoundException;

    boolean saveDonator(DonatorDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateDonator(DonatorDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteDonator(String id) throws SQLException, ClassNotFoundException;

    String generateNewDonatorID() throws SQLException, ClassNotFoundException;
}
