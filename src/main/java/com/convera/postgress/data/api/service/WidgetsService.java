package com.convera.postgress.data.api.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.convera.common.template.response.error.constants.ResponseErrorCode404;
import com.convera.common.template.response.util.CommonResponseUtil;
import com.convera.postgress.data.api.exception.WidgetNotFoundException;
import com.convera.postgress.data.api.helper.WidgetsHelper;
import com.convera.postgress.data.api.repository.DashboardPreferenceRepository;
import com.convera.postgress.data.api.repository.WidgetRepository;
import com.convera.postgress.data.api.repository.model.DashboardPreference;
import com.convera.postgress.data.api.repository.model.Widget;
import com.convera.postgress.data.api.repository.model.WidgetQuickLink;
import com.convera.postgress.data.api.repository.model.WidgetsID;


@Service
public class WidgetsService {

	@Autowired
	WidgetRepository widgetRepository;
	
	@Autowired
	DashboardPreferenceRepository dashboardPreferenceRepository;
	
	@Autowired
	DashboardPreferenceService dashboardPreferenceService;
	
	 public List<Widget> getAllWidgets() {
		 List<Widget> widgets = widgetRepository.findAll();
		 if(widgets.isEmpty()) {
			 return WidgetsHelper.getDefaultWidgets();
		 }
		 else {
			 return widgets;
		 }
		 
	    }
	 
	 public Widget addWidget(Widget widget) {
 
		 List<DashboardPreference> listDP = new ArrayList<DashboardPreference>();
		 DashboardPreference dp = dashboardPreferenceService.getDefaultPreference().get();
		 WidgetQuickLink wq = dp.getWidgetQuickLink();
		 List<Widget> widgets = wq.getWidgetPreference();
		 
		 for (Widget widget2 : widgets) {
			if(widget2.getWidgetId().equals(widget.getWidgetId())){
				widget2.setActive(true);
				widget2.setCreatedOn(LocalDateTime.now());
				widget2.setImageUrl(widget.getImageUrl());
				widget2.setNumberOfRecords(widget.getNumberOfRecords());
				widget2.setPosition(widget.getPosition());
				widget2.setTemplate(widget.getTemplate());
				widget2.setUpdatedOn(LocalDateTime.now());
				widget2.setWidgetName(widget.getWidgetName());
				widgetRepository.save(widget2);
			}
		}
		 wq.setWidgetPreference(widgets);
		 dp.setWidgetQuickLink(wq);
		 listDP.add(dp);
		 dashboardPreferenceService.saveAll(listDP);
		 
	// ADD WIDGETS TO SPECIFIC USERS WHICH HAVE WIDGETS BY WIDGETID
		 
		List<DashboardPreference> allDashboards = dashboardPreferenceService.getAllDashboards();
		for (DashboardPreference dashboardPreference : allDashboards) {
			List<Widget> widgetsofuser = dashboardPreference.getWidgetQuickLink().getWidgetPreference();
			for (Widget w : widgetsofuser) {
				if(widget.getWidgetId().equals(w.getWidgetId())) {
					w.setActive(true);
					w.setImageUrl(widget.getImageUrl());
					w.setNumberOfRecords(widget.getNumberOfRecords());
					w.setPosition(widget.getPosition());
					w.setTemplate(widget.getTemplate());
					w.setUpdatedOn(LocalDateTime.now());
					
				}
			}
		}
		dashboardPreferenceService.saveAll(allDashboards);
		 
	//
		 
		 Optional<Widget> w1 = widgetRepository.findByWidgetId(widget.getWidgetId());
		 if(w1.isPresent()) {
			 Widget w2 = w1.get();
			 w2.setActive(true);
			 w2.setImageUrl(widget.getImageUrl());
			 w2.setNumberOfRecords(widget.getNumberOfRecords());
			 w2.setPosition(widget.getPosition());
			 w2.setTemplate(widget.getTemplate());
			 w2.setUpdatedOn(LocalDateTime.now());
			 w2.setCreatedOn(LocalDateTime.now());
			 return widgetRepository.save(w2);
		 }
		 else {
			 widget.setCreatedOn(LocalDateTime.now());
			 widget.setUpdatedOn(LocalDateTime.now());
			 widget.setActive(true);
			 return widgetRepository.save(widget);
		 }


	 }
	 
	 public Optional<Widget> getWidgetByUserId(WidgetsID id){
		 
		 	return widgetRepository.findById(id);
	 
	 }
	 
	 public Optional<Widget> getWidgetByWidgetId(Long id){
		 return widgetRepository.findByWidgetId(id);
	 }
	 
	 
}
