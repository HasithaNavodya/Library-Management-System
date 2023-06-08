package lk.ijse.finalProject.dao.custom;

import lk.ijse.finalProject.dao.CrudDAO;

import lk.ijse.finalProject.dto.DonatorDTO;

import java.sql.SQLException;
import java.util.List;

public interface DonatorDAO extends CrudDAO<DonatorDTO,String> {
    public List<String> loadDonatorIds() throws SQLException;
}
