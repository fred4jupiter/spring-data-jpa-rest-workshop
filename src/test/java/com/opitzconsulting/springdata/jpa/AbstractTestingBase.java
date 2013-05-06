package com.opitzconsulting.springdata.jpa;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Base class for integration tests with Spring.
 *
 * @author michael
 */
@ContextConfiguration(classes = ApplicationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public abstract class AbstractTestingBase {

    @PersistenceContext
    protected EntityManager entityManager;

}
