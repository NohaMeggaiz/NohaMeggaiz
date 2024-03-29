package DAO;

import Model.Employe;
import Model.Project;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

public class DaoEmpImplment implements DaoEmploye{


    public DaoEmpImplment() {
        // Initialize EntityManagerFactory
        entityManagerFactory = Persistence.createEntityManagerFactory("exemplePU");
        this.entityManager = this.entityManagerFactory.createEntityManager();

    }

    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    private Set<Project> projects;

    @Override
    public List<Employe> getAllemployes() {
        System.out.println("get all is called");
        // Create EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // Begin transaction
            entityManager.getTransaction().begin();

            // Create query to retrieve all students
            TypedQuery<Employe> query = entityManager.createQuery("SELECT e FROM Employe e", Employe.class);

            // Execute query and get result list
            List<Employe> employees = query.getResultList();

            // Commit transaction
            entityManager.getTransaction().commit();

            return employees;
        } catch (Exception ex) {
            // If any exception occurs, rollback the transaction
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            // Close EntityManager
            entityManager.close();
        }    }


    @Override
    public void deleteEmploye(Employe employe) {
        EntityTransaction transaction = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Merge the provided employe entity if it's not managed
            if (!entityManager.contains(employe)) {
                employe = entityManager.merge(employe);
            }

            // Remove the entity
            entityManager.remove(employe);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            // Handle or log the exception
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public boolean addEmployee(Employe emp) {

            try {
                entityManager.getTransaction().begin();
                entityManager.persist(emp);
                entityManager.getTransaction().commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }




}
