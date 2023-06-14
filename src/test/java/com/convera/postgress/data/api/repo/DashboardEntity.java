package com.convera.postgress.data.api.repo;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.convera.postgress.data.api.helper.DashboardHelper;
import com.convera.postgress.data.api.repository.DashboardPreferenceRepository;
import com.convera.postgress.data.api.repository.model.DashboardPreference;

@DataJpaTest
public class DashboardEntity {

	@Autowired
    private TestEntityManager em;

    @Autowired
    private DashboardPreferenceRepository repository;
    
    @Test
    void contextLoads() {
        Assertions.assertNotNull(em);
    }
    
    @Test
    void verifyDashboard_save() {

        em.persist(DashboardHelper.getValidDashboard(1));

        Optional<DashboardPreference> result = repository.findById(1);
        Assertions.assertEquals(Boolean.TRUE, result.isPresent());
//        Assertions.assertEquals(19L, result.get().getUserId());
//        Assertions.assertEquals("w1", result.get().getWidgetName());
    }
}
