package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.model.FineDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FineBO extends SuperBO {

    public ArrayList<FineDTO> getAllFine() throws SQLException;

    public boolean saveFine(FineDTO fineDTO) throws SQLException;

    public boolean deleteFineId(String fine_id) throws SQLException;

    public boolean updateFine(FineDTO fineDTO) throws SQLException;

    public String getNextFineId() throws SQLException;
}
