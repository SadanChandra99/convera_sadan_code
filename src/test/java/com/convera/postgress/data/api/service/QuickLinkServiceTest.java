package com.convera.postgress.data.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.convera.postgress.data.api.helper.QuickLinkHelper;
import com.convera.postgress.data.api.repository.QuickLinkRepository;
import com.convera.postgress.data.api.repository.model.QuickLink;
import com.convera.postgress.data.api.repository.model.QuickLinkID;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.convera.postgress.data.api.helper.QuickLinkHelper;
import com.convera.postgress.data.api.repository.QuickLinkRepository;
import com.convera.postgress.data.api.repository.model.QuickLink;
import com.convera.postgress.data.api.repository.model.QuickLinkID;


@SpringBootTest
public class QuickLinkServiceTest {
	
	@InjectMocks
	private QuickLinkService quickLinkService;
	
	@MockBean
	private QuickLinkRepository quickLinkRepository;
	

	
	@Test
	void getAllQuickLinks() {
		assertNotNull(QuickLinkHelper.getAllQuickLinks());
	}
	
	@Test
	void getQuickLinkById() {
		QuickLinkID id = new QuickLinkID(1, "testing");
		QuickLinkID id2 = new QuickLinkID();
		id2.builder().linkId(1)
		.linkName("testing")
		.build();
		QuickLink q = new QuickLink(100, "Testing",  LocalDateTime.now(), LocalDateTime.now());
		
		assertNotNull(q);
		assertNotNull(id2);

		
	}
	
	@Test
	void addQuickLink() throws Exception {
		QuickLink q = new QuickLink(100, "Payment Link",  LocalDateTime.now(), LocalDateTime.now());
		QuickLink q2 = new QuickLink();
		q2.builder()
		.linkId(100)
		.linkName("payment link")
		.updatedBy(LocalDateTime.now())
		.updatedOn(LocalDateTime.now())
		.build();
		assertNotNull((q2));
	}
	
	
	
	
	 @Test
	    public void testGetAllQuickLinks_WhenQuickLinksExist_ShouldReturnAllQuickLinks() {
	        // Arrange
	        List<QuickLink> quickLinks = createSampleQuickLinkList();
	        when(quickLinkRepository.findAll()).thenReturn(quickLinks);

	        // Act
	        List<QuickLink> result = quickLinkService.getAllQuickLinks();

	        // Assert
	        assertEquals(quickLinks, result);
	    }

	    @Test
	    public void testGetAllQuickLinks_WhenNoQuickLinksExist_ShouldReturnDefaultQuickLinks() {
	        // Arrange
	        when(quickLinkRepository.findAll()).thenReturn(new ArrayList<>());

	        // Act
	        List<QuickLink> result = quickLinkService.getAllQuickLinks();

	        // Assert
	        List<QuickLink> defaultQuickLinks = QuickLinkHelper.getAllQuickLinks();
	        assertEquals(defaultQuickLinks, result);
	    }

	    @Test
	    public void testAddQuickLink_ShouldSaveAndReturnQuickLink() throws Exception {
	        // Arrange
	        QuickLink quickLink = createSampleQuickLink();
	        when(this.quickLinkRepository.save(quickLink)).thenReturn(quickLink);

	        // Act
	        QuickLink result = this.quickLinkService.addQuickLink(quickLink);

	        // Assert
	        assertEquals(quickLink, result);
	        assertTrue(result.getUpdatedBy() instanceof LocalDateTime);
	        assertTrue(result.getUpdatedOn() instanceof LocalDateTime);
	    }

	    @Test
	    public void testGetQuickLinkById_WhenQuickLinkExists_ShouldReturnQuickLink() {
	    	
	    	QuickLinkRepository quickLinkRepository;
	    	Integer userId = 12;
	        // Arrange
	        QuickLinkID id = new QuickLinkID(userId, "linkId");
	        QuickLink quickLink = createSampleQuickLink();
	        when(this.quickLinkRepository.findById(id)).thenReturn(Optional.of(quickLink));

	        // Act
	        Optional<QuickLink> result = this.quickLinkService.getQuickLinkById(id);

	        // Assert
	        assertTrue(result.isPresent());
	        assertEquals(quickLink, result.get());
	    }

	    @Test
	    public void testGetQuickLinkById_WhenQuickLinkDoesNotExist_ShouldReturnEmptyOptional() {
	     	Integer userId = 12;
	        // Arrange
	        QuickLinkID id = new QuickLinkID(userId, "linkId");
	        when(quickLinkRepository.findById(id)).thenReturn(Optional.empty());

	        // Act
	        Optional<QuickLink> result = quickLinkService.getQuickLinkById(id);

	        // Assert
	        assertFalse(result.isPresent());
	    }

	    // Helper methods to create sample data for testing
	    private List<QuickLink> createSampleQuickLinkList() {
	        List<QuickLink> quickLinks = new ArrayList<>();
	        quickLinks.add(createSampleQuickLink());
	        quickLinks.add(createSampleQuickLink());
	        return quickLinks;
	    }

	    private QuickLink createSampleQuickLink() {
	        QuickLink quickLink = new QuickLink(1, "link1", LocalDateTime.now(), LocalDateTime.now());
	        return quickLink;
	    }
	
	

	   

	



}
