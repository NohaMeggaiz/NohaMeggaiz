package Controller;

import DAO.DaoEmpImplment;
import Model.Employe;
import Model.Project;
import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.*;

@ManagedBean(name = "projectsBean")
@ViewScoped
public class ProjectBean implements Serializable {
    private Set<Project> employeeProjects;

    @PersistenceContext
    private EntityManager entityManager;

    private Map<Employe, Set<Project>> employeeProjectsMap;

    @PostConstruct
    public void init() {
        DaoEmpImplment employeeDao = new DaoEmpImplment();
        List<Employe> employees = employeeDao.getAllemployes();
        employeeProjectsMap = new HashMap<>();
        for (Employe employee : employees) {
            employeeProjectsMap.put(employee, new HashSet<>(employee.getProjects()));
        }
    }

    public Map<Employe, Set<Project>> getEmployeeProjectsMap() {
        return employeeProjectsMap;
    }


}

