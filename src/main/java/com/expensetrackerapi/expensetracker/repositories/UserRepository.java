package com.expensetrackerapi.expensetracker.repositories;

import com.expensetrackerapi.expensetracker.domain.User;
import com.expensetrackerapi.expensetracker.exceptions.EtAuthException;

public interface UserRepository {

    Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;
    User findByEmailAndPassword(String email, String password) throws EtAuthException;
    Integer getCountByEmail(String email);
    User findByUserId(Integer userId);

}
