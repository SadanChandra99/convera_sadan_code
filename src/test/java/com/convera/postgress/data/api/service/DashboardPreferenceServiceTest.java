package com.convera.postgress.data.api.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.convera.postgress.data.api.repository.DashboardPreferenceRepository;
import com.convera.postgress.data.api.repository.model.DashboardPreference;
import com.convera.postgress.data.api.repository.model.QuickLink;
import com.convera.postgress.data.api.repository.model.Widget;
import com.convera.postgress.data.api.repository.model.WidgetQuickLink;

@SpringBootTest
public class DashboardPreferenceServiceTest {

    @Mock
    private DashboardPreferenceRepository dashboardPreferenceRepository;
    
    @Mock
    private WidgetsService widgetsService;

    @Mock
    private QuickLinkService quickLinkService;

    @InjectMocks
    private DashboardPreferenceService dashboardPreferenceService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    void testSaveAll_NewDashboards_Success() {
        // Arrange
        List<DashboardPreference> dashboards = new ArrayList<>();
        DashboardPreference dashboard = new DashboardPreference();
        dashboard.setUserPreferenceId(1);
        dashboard.setWidgetQuickLink(new WidgetQuickLink());
        dashboards.add(dashboard);

        when(dashboardPreferenceRepository.findByUserPreferenceId(1)).thenReturn(new ArrayList<>());
        when(dashboardPreferenceRepository.save(any(DashboardPreference.class))).thenReturn(dashboard);

        // Act
        List<DashboardPreference> result = dashboardPreferenceService.saveAll(dashboards);

        // Assert
        assertEquals(dashboards, result);
        verify(dashboardPreferenceRepository, times(1)).save(any(DashboardPreference.class));
    }

    @Test
    void testSaveAll_ExistingDashboards_Success() {
        // Arrange
        List<DashboardPreference> dashboards = new ArrayList<>();
        DashboardPreference dashboard = new DashboardPreference();
        dashboard.setUserPreferenceId(1);
        dashboard.setWidgetQuickLink(new WidgetQuickLink());
        dashboards.add(dashboard);

        List<DashboardPreference> existingDashboards = new ArrayList<>();
        DashboardPreference existingDashboard = new DashboardPreference();
        existingDashboard.setUserPreferenceId(1);
        existingDashboard.setWidgetQuickLink(new WidgetQuickLink());
        existingDashboards.add(existingDashboard);

        when(dashboardPreferenceRepository.findByUserPreferenceId(1)).thenReturn(existingDashboards);
        when(dashboardPreferenceRepository.saveAll(any(List.class))).thenReturn(existingDashboards);

        // Act
        List<DashboardPreference> result = dashboardPreferenceService.saveAll(dashboards);

        // Assert
        assertEquals(existingDashboards, result);
        verify(dashboardPreferenceRepository, times(1)).saveAll(any(List.class));
    }

    @Test
    void saveAll_NewDashboard_ReturnsSavedDashboards() {
        // Arrange
        List<DashboardPreference> dashboards = new ArrayList<>();
        DashboardPreference dashboard = new DashboardPreference();
        dashboards.add(dashboard);

        when(dashboardPreferenceRepository.findByUserPreferenceId(anyInt())).thenReturn(new ArrayList<>());
        when(dashboardPreferenceRepository.save(any(DashboardPreference.class))).thenReturn(dashboard);

        // Act
        List<DashboardPreference> savedDashboards = dashboardPreferenceService.saveAll(dashboards);

        // Assert
        Assertions.assertEquals(1, savedDashboards.size());
        Assertions.assertEquals(dashboard, savedDashboards.get(0));
        verify(dashboardPreferenceRepository, times(1)).save(dashboard);
    }

    @Test
    public void testGetDashboardsByUserIdExists() {
        // Prepare data
        Integer userId = 1;
        DashboardPreference dashboardPreference = new DashboardPreference();
        WidgetQuickLink widgetQuickLink = new WidgetQuickLink();
        List<Widget> widgetsList = new ArrayList<>();
        Widget widget1 = new Widget();
        widget1.setActive(true);
        widgetsList.add(widget1);
        Widget widget2 = new Widget();
        widget2.setActive(false);
        widgetsList.add(widget2);
        widgetQuickLink.setWidgetPreference(widgetsList);
        dashboardPreference.setWidgetQuickLink(widgetQuickLink);

        // Mock the repository methods
        when(dashboardPreferenceRepository.existsById(userId)).thenReturn(true);
        when(dashboardPreferenceRepository.findById(userId)).thenReturn(Optional.of(dashboardPreference));

        // Perform the getDashboardsByUserId operation
        Optional<DashboardPreference> result = dashboardPreferenceService.getDashboardsByUserId(userId);

        // Assertions
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getWidgetQuickLink().getWidgetPreference().size());
        assertTrue(result.get().getWidgetQuickLink().getWidgetPreference().get(0).isActive());
    }

    @Test
    public void testGetDashboardsByUserIdNotExists() {
        // Prepare data
        Integer userId = 1;
        DashboardPreference dashboardPreference = new DashboardPreference();

        // Mock the repository methods
        when(dashboardPreferenceRepository.existsById(userId)).thenReturn(false);
        when(dashboardPreferenceRepository.findById(userId)).thenReturn(Optional.of(dashboardPreference));

        // Perform the getDashboardsByUserId operation
        Optional<DashboardPreference> result = dashboardPreferenceService.getDashboardsByUserId(userId);

        // Assertions
        assertTrue(result.isPresent());
        // Additional assertions based on the expected behavior
    }

    
    @Test
    void getDefaultPreference_ReturnsDashboardPreferenceWithDefaultValues() {
        // Arrange
        int userId = 0;
        List<Widget> widgets = new ArrayList<>();
        List<QuickLink> quickLinks = new ArrayList<>();

        when(widgetsService.getAllWidgets()).thenReturn(widgets);
        when(quickLinkService.getAllQuickLinks()).thenReturn(quickLinks);

        // Act
        Optional<DashboardPreference> result = dashboardPreferenceService.getDefaultPreference();

        // Assert
        Assertions.assertTrue(result.isPresent());
        DashboardPreference dashboard = result.get();
        Assertions.assertEquals(0, dashboard.getUserPreferenceId());

        Assertions.assertEquals(LocalDateTime.now(), dashboard.getUpdatedOn());
        Assertions.assertEquals(widgets, dashboard.getWidgetQuickLink().getWidgetPreference());
        Assertions.assertEquals(quickLinks, dashboard.getWidgetQuickLink().getQuickLinkPreference());
    }
    
    @Test
    void saveAll_ExistingDashboard_ReturnsUpdatedDashboards() {
        // Arrange
        List<DashboardPreference> dashboards = new ArrayList<>();
        DashboardPreference existingDashboard = new DashboardPreference();
        existingDashboard.setUserPreferenceId(1);

        DashboardPreference updatedDashboard = new DashboardPreference();
        updatedDashboard.setUserPreferenceId(1);

        dashboards.add(updatedDashboard);

        List<DashboardPreference> existingDashboards = new ArrayList<>();
        existingDashboards.add(existingDashboard);

        when(dashboardPreferenceRepository.findByUserPreferenceId(anyInt())).thenReturn(existingDashboards);
        when(dashboardPreferenceRepository.saveAll(anyList())).thenReturn(existingDashboards);

        // Act
        List<DashboardPreference> updatedDashboards = dashboardPreferenceService.saveAll(dashboards);

        // Assert
        Assertions.assertEquals(1, updatedDashboards.size());

        verify(dashboardPreferenceRepository, times(1)).saveAll(existingDashboards);
    }

}


