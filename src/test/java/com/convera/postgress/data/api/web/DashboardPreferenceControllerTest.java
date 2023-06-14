package com.convera.postgress.data.api.web;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.convera.common.template.CommonResponse;
import com.convera.postgress.data.api.repository.model.DashboardPreference;
import com.convera.postgress.data.api.service.DashboardPreferenceService;

@SpringBootTest
public class DashboardPreferenceControllerTest {

    @Mock
    private DashboardPreferenceService dashboardPreferenceService;

    @InjectMocks
    private DashboardPreferenceController dashboardPreferenceController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetDashboardsExists() {
        // Prepare data
        Integer userId = 1;
        DashboardPreference dashboardPreference = new DashboardPreference();
        // Set up the necessary data for dashboardPreference

        // Mock the service methods
        when(dashboardPreferenceService.getDashboardsByUserId(userId)).thenReturn(Optional.of(dashboardPreference));

        // Perform the getDashboards operation
//        ResponseEntity<CommonResponse<DashboardPreference>> response = dashboardPreferenceController.getDashboards(userId, null);
//
//        // Assertions
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
        // Additional assertions based on the expected behavior
    }

    @Test
    public void testGetDashboardsNotExists() {
        // Prepare data
        Integer userId = 1;

        // Mock the service methods
        when(dashboardPreferenceService.getDashboardsByUserId(userId)).thenReturn(Optional.empty());
        when(dashboardPreferenceService.getDefaultPreference()).thenReturn(Optional.of(new DashboardPreference()));

        // Perform the getDashboards operation
        ResponseEntity<CommonResponse<DashboardPreference>> response = dashboardPreferenceController.getDashboards(userId, null);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        // Additional assertions based on the expected behavior
    }

    @Test
    public void testSaveDashboards() {
        // Prepare data
        List<DashboardPreference> request = new ArrayList<>();
        // Add some dashboard preferences to the request

        // Mock the service method
        when(dashboardPreferenceService.saveAll(anyList())).thenReturn(request);

        // Perform the saveDashboards operation
        ResponseEntity<CommonResponse<List<DashboardPreference>>> response = dashboardPreferenceController.saveDashboards(request, null);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        // Additional assertions based on the expected behavior
    }

    @Test
    public void testSaveDashboardsException() {
        // Prepare data
        List<DashboardPreference> request = new ArrayList<>();
        // Add some dashboard preferences to the request

        // Mock the service method to throw an exception
        when(dashboardPreferenceService.saveAll(anyList())).thenThrow(new RuntimeException());

        // Perform the saveDashboards operation
        ResponseEntity<CommonResponse<List<DashboardPreference>>> response = dashboardPreferenceController.saveDashboards(request, null);

        // Assertions
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        // Additional assertions based on the expected behavior
    }

}
