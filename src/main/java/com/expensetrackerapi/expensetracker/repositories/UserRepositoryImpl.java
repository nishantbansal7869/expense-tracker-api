package com.expensetrackerapi.expensetracker.repositories;

import com.expensetrackerapi.expensetracker.domain.User;
import com.expensetrackerapi.expensetracker.exceptions.EtAuthException;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
    @Override
    public Integer create(String firstName, String lastName, String email, String password) throws EtAuthException {
        return null;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws EtAuthException {
        return null;
    }

    @Override
    public Integer getCountByEmail(String email) {
        return null;
    }

    @Override
    public User findByUserId(Integer userId) {
        return null;
    }
}
