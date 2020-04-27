package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;
    @Transactional(readOnly = true)
    @Override
    public void doWrite(final OutputStream outputStream) {
        try (Stream<Employee> employeeList = repository.streamAllEmployees()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {

                employeeList.forEach(emp -> {
                    try {
                        oos.write(emp.toString().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
