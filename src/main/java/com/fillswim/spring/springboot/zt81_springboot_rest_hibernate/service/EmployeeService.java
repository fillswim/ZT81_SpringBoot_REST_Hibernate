package com.fillswim.spring.springboot.zt81_springboot_rest_hibernate.service;




import com.fillswim.spring.springboot.zt81_springboot_rest_hibernate.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

    public Employee getEmployee(int id);

    public void deleteEmployee(int id);
}
