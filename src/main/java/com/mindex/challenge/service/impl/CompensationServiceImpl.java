package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {

    private final CompensationRepository compensationRepository;

    @Autowired
    public CompensationServiceImpl(CompensationRepository compensationRepository) {
        this.compensationRepository = compensationRepository;
    }

    @Override
    public Compensation create(Compensation compensation) {
        // Implement the logic to create a compensation object in the persistence layer
        return compensationRepository.save(compensation);
    }

    @Override
    public Compensation read(String employeeId) {
        // Implement the logic to retrieve a compensation object by employeeId from the persistence layer
        return compensationRepository.findByEmployee_EmployeeId(employeeId);
    }
}
