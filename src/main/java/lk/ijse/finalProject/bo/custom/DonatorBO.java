package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.model.DonatorDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DonatorBO extends SuperBO {

    public ArrayList<DonatorDTO> getAllDonator() throws SQLException;

    public boolean saveDonator(DonatorDTO donatorDTO) throws SQLException;

    public boolean deleteDonator(String donator_id) throws SQLException;

    public boolean updateDonator(DonatorDTO donatorDTO) throws SQLException;

    public String getNextDonatorId() throws SQLException;
}
