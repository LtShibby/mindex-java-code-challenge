package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The CompensationServiceImpl class is an implementation of the CompensationService interface.
 * It provides methods to create and retrieve compensation objects from the persistence layer.
 */
@Service
public class CompensationServiceImpl implements CompensationService {

    private final CompensationRepository compensationRepository;

    /**
     * Constructor for the CompensationServiceImpl class.
     *
     * @param compensationRepository    The repository responsible for accessing the persistence layer for compensations.
     */
    @Autowired
    public CompensationServiceImpl(CompensationRepository compensationRepository) {
        this.compensationRepository = compensationRepository;
    }

    /**
     * Creates a new compensation object in the persistence layer.
     *
     * @param compensation    The compensation object to be created.
     * @return The created compensation object.
     */
    @Override
    public Compensation create(Compensation compensation) {
        // Implement the logic to create a compensation object in the persistence layer
        return compensationRepository.save(compensation);
    }

    /**
     * Retrieves a compensation object by employeeId from the persistence layer.
     *
     * @param employeeId    The employeeId associated with the compensation.
     * @return The retrieved compensation object, or null if not found.
     */
    @Override
    public Compensation read(String employeeId) {
        // Implement the logic to retrieve a compensation object by employeeId from the persistence layer
        return compensationRepository.findByEmployee_EmployeeId(employeeId);
    }
}
