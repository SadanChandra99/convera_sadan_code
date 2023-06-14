package com.convera.postgress.data.api.helper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.convera.postgress.data.api.repository.model.QuickLink;

public final class QuickLinkHelperTest {
	
	public static List<QuickLink> getAllQuickLinks(){
		QuickLink q1 = new QuickLink(1, "Make a Payment",  LocalDateTime.now(), LocalDateTime.now());
		QuickLink q2 = new QuickLink(1, "View Transactions",  LocalDateTime.now(), LocalDateTime.now());
		QuickLink q3 = new QuickLink(1, "Add Payee",  LocalDateTime.now(), LocalDateTime.now());
		QuickLink q4 = new QuickLink(1, "Manage Payee",  LocalDateTime.now(), LocalDateTime.now());
		
		List<QuickLink> quickLinks = new ArrayList<QuickLink>();
		quickLinks.add(q1);
		quickLinks.add(q2);
		quickLinks.add(q3);
		quickLinks.add(q4);
		
		return quickLinks;
		
	}

}
