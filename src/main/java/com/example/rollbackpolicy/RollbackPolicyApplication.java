package com.example.rollbackpolicy;

import com.example.rollbackpolicy.dao.EmployeeDao;
import com.example.rollbackpolicy.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class RollbackPolicyApplication implements CommandLineRunner {
    private final EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(RollbackPolicyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        employeeService.deleteEmployee();
        try {
            employeeService.createEmployee();
        }catch (Exception e){
            System.out.println("Exception caught::MX7");
        }
        //employeeService.createEmployee();
        employeeService.findAllEmployee()
                .forEach(System.out::println);
    }
}
