package Controller;


import DAO.DaoEmpImplment;
import Model.Employe;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.persistence.PersistenceException;

import java.util.List;

@ManagedBean(name = "employeBean ")
@ViewScoped
public class EmployeBean {

    private List<Employe> employees;
    private DaoEmpImplment dip;


    public  EmployeBean() {
        // Initialize StudentDAO
        dip = new DaoEmpImplment();
    }

    public List<Employe> getEmployeess() {
        System.out.println("bean function is called");
        employees = dip.getAllemployes();
        return employees;
    }


    public void deleteEmploye(Employe employe) {
        System.out.println("2222222");
        try {
            dip.deleteEmploye(employe);
            employees = dip.getAllemployes();
            showMessage("Employee deleted successfully");
        } catch (PersistenceException e) {
            showMessage("Error deleting employee: Persistence issue");
            e.printStackTrace(); // Log the exception for debugging
        } catch (Exception e) {
            showMessage("Error deleting employee: " + e.getMessage());
            e.printStackTrace(); // Log the exception for debugging
        }
    }


    public static void showMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }


    private String name;
    private String email;
    private List<String> skills;
    public void addNewEmployee() {
        try {
            Employe employee = new Employe();
            employee.setName(name);
            employee.setEmail(email);
            employee.setSkills(skills);


            boolean added = dip.addEmployee(employee);

            if (added) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Employee added successfully!");
                FacesContext.getCurrentInstance().addMessage(null, message);

            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to add employee.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred while adding the employee.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            e.printStackTrace();
        }
    }



}
