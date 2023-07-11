package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

/**
 * The CompensationService interface provides methods to create and retrieve compensation objects.
 */
public interface CompensationService {
    /**
     * Creates a new compensation object.
     *
     * @param compensation The compensation object to be created.
     * @return The created compensation object.
     */
    Compensation create(Compensation compensation);

    /**
     * Retrieves a compensation object by employeeId.
     *
     * @param employeeId The employeeId associated with the compensation.
     * @return The retrieved compensation object, or null if not found.
     */
    Compensation read(String employeeId);
}
