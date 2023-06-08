package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.DonatorDTO;
import lk.ijse.finalProject.dto.FineDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FineBO extends SuperBO {

    ArrayList<FineDTO> getAllFine() throws SQLException, ClassNotFoundException;

    boolean saveFine(FineDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateFine(FineDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteFine(String id) throws SQLException, ClassNotFoundException;

    String generateNewFineID() throws SQLException, ClassNotFoundException;
}
