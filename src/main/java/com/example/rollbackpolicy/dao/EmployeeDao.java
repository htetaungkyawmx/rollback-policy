package com.example.rollbackpolicy.dao;

import com.example.rollbackpolicy.ds.Employee;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class EmployeeDao {
    private JdbcTemplate jdbcTemplate;

    public List<Employee> findAllEmployee(){
        return jdbcTemplate.query("select * from employee"
                ,this::mapEmployee);
    }

    public void deleteEmployee(){
        jdbcTemplate.update("delete from employee");
    }
    public void createEmployee(Employee employee){
        jdbcTemplate.update("""
insert into employee(id,first_name,last_name,email,phone_number,hired_date,salary)
values 
(?,?,?,?,?,?,?)
""",employee.id(),
                employee.firstName(),
                employee.lastName(),
                employee.email(),
                employee.phoneNumber(),
                employee.hiredDate(),
                employee.salary());
    }

    @SneakyThrows
    public Employee mapEmployee(ResultSet rs,int i){
        return new Employee(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone_number"),
                rs.getDate("hired_date"),
                rs.getDouble("salary")
        );
    }

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

}
