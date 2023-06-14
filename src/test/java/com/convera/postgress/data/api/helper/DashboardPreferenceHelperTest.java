package com.convera.postgress.data.api.helper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.convera.postgress.data.api.repository.model.DashboardPreference;
import com.convera.postgress.data.api.repository.model.QuickLink;
import com.convera.postgress.data.api.repository.model.Widget;
import com.convera.postgress.data.api.repository.model.WidgetQuickLink;

public class DashboardPreferenceHelperTest {


    public static DashboardPreference getValidDashboard(Integer userId) {
    	Widget w1 = new Widget(1L, "payment widget", "template-1", "localhost:8080", false,  LocalDateTime.now(), LocalDateTime.now(), 2);
    	Widget w2 = new Widget(1L, "market news widget", "template-2", "localhost:8984", true, LocalDateTime.now(), LocalDateTime.now(), 4);
    	Widget w3 = new Widget(1L, "quotes widget", "template-3", "localhost:4820", true,  LocalDateTime.now(), LocalDateTime.now(), 6);

    	List<Widget> widgetPreference = new ArrayList<Widget>();
    	widgetPreference.add(w1);
    	widgetPreference.add(w2);
    	widgetPreference.add(w3);
    	
    	QuickLink q1 = new QuickLink(1, "quotes link",  LocalDateTime.now(), LocalDateTime.now());
    	QuickLink q2 = new QuickLink(1, "payment link",  LocalDateTime.now(), LocalDateTime.now());
    	
    	List<QuickLink> quickLinkPreference = new ArrayList<QuickLink>();
    	quickLinkPreference.add(q1);
    	quickLinkPreference.add(q2);
    	
    	WidgetQuickLink wq = new WidgetQuickLink(widgetPreference, quickLinkPreference);
    	return DashboardPreference.builder()
                .userPreferenceId(userId)
          
                .createdOn(LocalDateTime.now())
             
                .updatedOn(LocalDateTime.now())
                .widgetQuickLink(wq)
                .build();
    }
}
