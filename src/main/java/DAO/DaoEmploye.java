package DAO;

import Model.Employe;

import java.util.List;

public interface DaoEmploye {

    List<Employe> getAllemployes();

    void deleteEmploye(Employe emp);
    boolean addEmployee(Employe emp);

}
