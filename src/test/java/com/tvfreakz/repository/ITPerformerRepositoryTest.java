/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.repository;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.tvfreakz.model.entity.Performer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/config/testMVCContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("testDataset.xml")
public class ITPerformerRepositoryTest {
    
    @Autowired
    private PerformerRepository repository;
    
    @Test
    public void testFindByPerformerIdWhenNoPerformerFound() {
        Performer performer = repository.findByPerformerId(23L);
        assertNull(performer);
    }
    
    @Test
    public void testFindByPerformerIdWhenPerformerIsFound() {
        Performer performer = repository.findByPerformerId(1L);
        assertEquals("Sigourney Weaver", performer.getPerformerName());
    }

}
