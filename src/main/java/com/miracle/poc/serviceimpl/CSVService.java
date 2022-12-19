package com.miracle.poc.serviceimpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.miracle.poc.config.CSVHelper;
import com.miracle.poc.model.EmployeeInfo;
import com.miracle.poc.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {
    @Autowired
    EmployeeRepository repository;

    public void save(MultipartFile file) {
        try {
            List<EmployeeInfo> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<EmployeeInfo> tutorials = repository.findAll();

        ByteArrayInputStream in = CSVHelper.tutorialsToCSV(tutorials);
        return in;
    }

    public List<EmployeeInfo> getAllTutorials() {
        return repository.findAll();
    }
}
