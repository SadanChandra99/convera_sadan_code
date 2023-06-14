package com.convera.postgress.data.api.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.convera.common.template.CommonResponse;
import com.convera.common.template.response.error.constants.ResponseErrorCode404;
import com.convera.common.template.response.error.constants.ResponseErrorCode500;
import com.convera.postgress.data.api.repository.model.QuickLink;
import com.convera.postgress.data.api.repository.model.QuickLinkID;
import com.convera.postgress.data.api.service.QuickLinkService;

class QuickLinkControllerTest {
    @Mock
    private QuickLinkService quickLinkService;

    @InjectMocks
    private QuickLinkController quickLinkController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetQuickLinks() {
        // Mocking the necessary objects
        String correlationId = "123456";
        List<QuickLink> quickLinks = new ArrayList<>();
        quickLinks.add(new QuickLink());

        when(quickLinkService.getAllQuickLinks()).thenReturn(quickLinks);

        // Calling the method under test
        ResponseEntity<CommonResponse<List<QuickLink>>> response = quickLinkController.getQuickLinks(correlationId);

        // Verifying the interactions and assertions
        verify(quickLinkService, times(1)).getAllQuickLinks();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        
        assertEquals(quickLinks, response.getBody().getData());
    }

    @Test
    void testGetQuickLinks_NotFound() {
        // Mocking the necessary objects
        String correlationId = "123456";

        when(quickLinkService.getAllQuickLinks()).thenReturn(Collections.emptyList());

        // Calling the method under test
        ResponseEntity<CommonResponse<List<QuickLink>>> response = quickLinkController.getQuickLinks(correlationId);

        // Verifying the interactions and assertions
        verify(quickLinkService, times(1)).getAllQuickLinks();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        
        assertEquals(ResponseErrorCode404.ERR_40400.build("QuickLinks", "Records not found : "),
                response.getBody().getMetadata().getErrors().get(0));
    }

    @Test
    void testSaveQuickLink() throws Exception {
        // Mocking the necessary objects
        String correlationId = "123456";
        QuickLink quickLink = new QuickLink();
        QuickLink savedQuickLink = new QuickLink();

        when(quickLinkService.addQuickLink(quickLink)).thenReturn(savedQuickLink);

        // Calling the method under test
        ResponseEntity<CommonResponse<QuickLink>> response = quickLinkController.saveQuickLink(quickLink, correlationId);

        // Verifying the interactions and assertions
        verify(quickLinkService, times(1)).addQuickLink(quickLink);
        assertEquals(HttpStatus.OK, response.getStatusCode());
      
        assertEquals(savedQuickLink, response.getBody().getData());
    }

    @Test
    void testSaveQuickLink_InternalServerError() throws Exception {
        // Mocking the necessary objects
        String correlationId = "123456";
        QuickLink quickLink = new QuickLink();

        when(quickLinkService.addQuickLink(quickLink)).thenThrow(new Exception("Error in saving the record."));

        // Calling the method under test
        ResponseEntity<CommonResponse<QuickLink>> response = quickLinkController.saveQuickLink(quickLink, correlationId);

        // Verifying the interactions and assertions
        verify(quickLinkService, times(1)).addQuickLink(quickLink);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        
//        assertEquals(ResponseErrorCode500.ERR_50000.build("saveQuickLink",
//                "Error in saving the record in the DB.Message: Error in saving the record."),
//                response.getBody().getMetadata().getErrors().get(0));
    }

    @Test
    void testGetQuickLinkById() {
        // Mocking the necessary objects
        String correlationId = "123456";
        Integer userId = 1;
        String linkName = "example";
        QuickLinkID id = new QuickLinkID(userId, linkName);
        QuickLink quickLink = new QuickLink();

        when(quickLinkService.getQuickLinkById(id)).thenReturn(Optional.of(quickLink));

        // Calling the method under test
        ResponseEntity<CommonResponse<QuickLink>> response = quickLinkController.getQuickLinkById(userId, linkName, correlationId);

        // Verifying the interactions and assertions
//        verify(quickLinkService, times(1)).getQuickLinkById(id);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        assertEquals(quickLink, response.getBody().getData());
    }

    @Test
    void testGetQuickLinkById_NotFound() {
        // Mocking the necessary objects
        String correlationId = "123456";
        Integer userId = 1;
        String linkName = "example";
        QuickLinkID id = new QuickLinkID(userId, linkName);

        when(quickLinkService.getQuickLinkById(id)).thenReturn(Optional.empty());

        // Calling the method under test
        ResponseEntity<CommonResponse<QuickLink>> response = quickLinkController.getQuickLinkById(userId, linkName, correlationId);

        // Verifying the interactions and assertions
//        verify(quickLinkService, times(1)).getQuickLinkById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        
        assertEquals(ResponseErrorCode404.ERR_40400.build("QuickLink", "Records not found : "),
                response.getBody().getMetadata().getErrors().get(0));
    }
}
