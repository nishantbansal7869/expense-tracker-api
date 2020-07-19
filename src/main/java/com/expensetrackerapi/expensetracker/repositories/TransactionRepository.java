package com.expensetrackerapi.expensetracker.repositories;

import com.expensetrackerapi.expensetracker.domain.Transaction;
import com.expensetrackerapi.expensetracker.exceptions.EtBadRequestException;
import com.expensetrackerapi.expensetracker.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> findAll(Integer userId, Integer categoryId);

    Transaction findById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;

    Integer create(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate) throws
            EtBadRequestException;

    void update(Integer userId, Integer categoryId, Integer transactionID, Transaction transaction) throws
            EtBadRequestException;

    void removeById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;

}
