package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.FineBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.FineDAO;
import lk.ijse.finalProject.dao.custom.impl.FineDAOImpl;
import lk.ijse.finalProject.model.EmployeeDTO;
import lk.ijse.finalProject.model.FineDTO;
import lk.ijse.finalProject.entity.Employee;
import lk.ijse.finalProject.entity.Fine;

import java.sql.SQLException;
import java.util.ArrayList;

public class FineBOImpl implements FineBO {
    FineDAO fineDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.FINE);


    public ArrayList<FineDTO> getAllFine() throws SQLException {
        ArrayList<Fine> allFine = fineDAO.getAll();
        ArrayList<FineDTO> arrayList = new ArrayList<>();
        for (Fine f : allFine) {
            arrayList.add(new FineDTO(f.getFine_id(),
                                      f.getAmount(),
                                      f.getDate(),
                                      f.getDescription(),
                                      f.getMember_id()));
        }
        return arrayList;
    }

    public boolean saveFine(FineDTO fineDTO) throws SQLException {
        return fineDAO.save(new Fine(fineDTO.getFine_id(),
                                     fineDTO.getAmount(),
                                     fineDTO.getDate(),
                                     fineDTO.getDescription(),
                                     fineDTO.getMember_id()));
    }

    public boolean deleteFineId(String fine_id) throws SQLException {
        return fineDAO.delete(fine_id);
    }

    public boolean updateFine(FineDTO fineDTO) throws SQLException {
        return fineDAO.update(new Fine(fineDTO.getAmount(),
                                       fineDTO.getDate(),
                                       fineDTO.getDescription(),
                                       fineDTO.getMember_id(),
                                       fineDTO.getFine_id()));
    }

    public String getNextFineId() throws SQLException {
        return fineDAO.getNextId();
    }
}
