package com.example.rollbackpolicy.service;

import com.example.rollbackpolicy.dao.EmployeeDao;
import com.example.rollbackpolicy.ds.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeDao employeeDao;

    public void deleteEmployee(){
        employeeDao.deleteEmployee();
    }
    public List<Employee> findAllEmployee(){
        return employeeDao.findAllEmployee();
    }
    @Transactional(rollbackFor = InterruptedException.class)
    public void createEmployee()throws InterruptedException{
        employeeDao.createEmployee(new Employee(1,"MX","Peter",
                "MX@gmail.com","55-555-55",
                Date.valueOf("2023-12-13"),2000));
        employeeDao.createEmployee(new Employee(2,"Neo","Peter",
                "MX@gmail.com","55-555-55",
                Date.valueOf("2023-12-13"),2500));
        employeeDao.createEmployee(new Employee(3,"Souldy","Peter",
                "MX@gmail.com","55-555-55",
                Date.valueOf("2023-12-13"),3000));

        throw new InterruptedException();

    }

}
