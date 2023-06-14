package com.convera.postgress.data.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.convera.postgress.data.api.helper.QuickLinkHelper;
import com.convera.postgress.data.api.repository.QuickLinkRepository;
import com.convera.postgress.data.api.repository.model.DashboardPreference;
import com.convera.postgress.data.api.repository.model.QuickLink;
import com.convera.postgress.data.api.repository.model.QuickLinkID;
import com.convera.postgress.data.api.repository.model.WidgetQuickLink;

@Service
public class QuickLinkService {
	
	@Autowired
	QuickLinkRepository quickLinkRepository;
	
	@Autowired
	DashboardPreferenceService dashboardPreferenceService;
	
	public List<QuickLink> getAllQuickLinks() {
		
		List<QuickLink> quickLinks = quickLinkRepository.findAll();
		if(quickLinks.isEmpty()) {
			return (QuickLinkHelper.getAllQuickLinks());
		}
		else {
			return quickLinks;
		}
         
    }
	
	public QuickLink addQuickLink(QuickLink quickLink) throws Exception {

		Optional<QuickLink> q1 = quickLinkRepository.findByLinkId(quickLink.getLinkId());
		if(q1.isPresent()) {
			QuickLink q2 = q1.get();
			q2.setLinkName(quickLink.getLinkName());
			QuickLink q3 = quickLinkRepository.save(q2);
			return q3;
		}
		else {
			QuickLink q3 = quickLinkRepository.save(quickLink);
			return q3;
		}
	}
	
	public Optional<QuickLink> getQuickLinkById(QuickLinkID id) {
		return quickLinkRepository.findById(id);
		 
	}
	

}
