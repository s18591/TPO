package com.company.tests1;

import com.company.dtos.DTOBase;
import com.company.repositories.IRepository;

import org.junit.After;
import org.junit.Before;


public abstract class RepositoryTestBase<TDTO extends DTOBase, TRepository extends IRepository<TDTO>> {

    protected TRepository _repository;

    @Before
    public void before() {
        _repository = Create();
        if (_repository != null) {
            _repository.beginTransaction();
        }
    }

    @After
    public void after() {
        if (_repository != null) {
            _repository.rollbackTransaction();
        }
    }

    protected abstract TRepository Create();
}