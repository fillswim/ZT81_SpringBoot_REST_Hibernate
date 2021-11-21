package com.fillswim.spring.springboot.zt81_springboot_rest_hibernate.dao;

import com.fillswim.spring.springboot.zt81_springboot_rest_hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

// В Spring Boot дефолтной реализацией JPA является Hibernate
// В Hibernate мы работаем с Session
// Hibernate Session = JPA EntityManager
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    // В Spring Boot приложениях необходимо использовать EntityManager из JPA
    // Бин EntityManager будет создаваться автоматически
    private final EntityManager entityManager;

    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> getAllEmployees() {

        // EntityManager - обёртка класса Session
        Session session = entityManager.unwrap(Session.class);

        List<Employee> employees = session.createQuery("from Employee", Employee.class).getResultList();

        return employees;
    }

    // Метод или сохраняет нового работника или обновляет старого
    @Override
    public void saveEmployee(Employee employee) {
        Session session = entityManager.unwrap(Session.class);

        // Работник будет сохраняться в БД, если id у него 0, в ином случае он будет обновляться
        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {

        Session session = entityManager.unwrap(Session.class);

        Employee employee = session.get(Employee.class, id);

        return employee;
    }

    @Override
    public void deleteEmployee(int id) {

        Session session = entityManager.unwrap(Session.class);

        Query<Employee> query = session.createQuery("delete from Employee where id =:employeeId");
        query.setParameter("employeeId", id);

        query.executeUpdate();
    }
}
