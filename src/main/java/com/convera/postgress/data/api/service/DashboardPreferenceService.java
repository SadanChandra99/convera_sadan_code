package com.convera.postgress.data.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.convera.postgress.data.api.helper.WidgetQuickLinkHelper;
import com.convera.postgress.data.api.repository.DashboardPreferenceRepository;
import com.convera.postgress.data.api.repository.model.DashboardPreference;
import com.convera.postgress.data.api.repository.model.PositionedWidget;
import com.convera.postgress.data.api.repository.model.QuickLink;
import com.convera.postgress.data.api.repository.model.Widget;
import com.convera.postgress.data.api.repository.model.WidgetQuickLink;

@Service
public class DashboardPreferenceService {

	@Autowired
	DashboardPreferenceRepository dashboardPreferenceRepository;
	
	@Autowired
	WidgetsService widgetsService;
	
	@Autowired
	QuickLinkService quickLinkService;


	public List<DashboardPreference> saveAll(List<DashboardPreference> dashboards) {

		Integer enteredid = dashboards.get(0).getUserPreferenceId();
		List<DashboardPreference> p = dashboardPreferenceRepository.findByUserPreferenceId(enteredid);
		if (p.isEmpty()) {
			
			dashboards.get(0).setCreatedOn(LocalDateTime.now());
		
			dashboards.get(0).setUpdatedOn(LocalDateTime.now());
			return dashboards.stream().map(dashboardPreferenceRepository::save).collect(Collectors.toList());
		}

		else {
			
			p.get(0).setUpdatedOn(LocalDateTime.now());
			p.get(0).setWidgetQuickLink(dashboards.get(0).getWidgetQuickLink());
			
			dashboardPreferenceRepository.saveAll(p);
			return p;
		}

	}

	public Optional<DashboardPreference> getDashboardsByUserId(Integer userId) {

		if (dashboardPreferenceRepository.existsById(userId)) {
			Optional<DashboardPreference> dashboards = dashboardPreferenceRepository.findById(userId);

			List<Widget> widgetsList = new ArrayList<Widget>();

			widgetsList = dashboards.get().getWidgetQuickLink().getWidgetPreference();

			List<Widget> upadtedWidgets = new ArrayList<Widget>();

			for (Widget widget : widgetsList) {
				if (widget.isActive()) {
					upadtedWidgets.add(widget);
				}
			}

			dashboards.get().getWidgetQuickLink().setWidgetPreference(upadtedWidgets);
			return dashboards;
		} else {
			return dashboardPreferenceRepository.findById(userId);
		}

	}

	public Optional<DashboardPreference> getDefaultPreference() {
		
	
		
		List<Widget> widgets = widgetsService.getAllWidgets();
		List<QuickLink> quickLinks = quickLinkService.getAllQuickLinks();
		
		WidgetQuickLink widgetQuickLink = new WidgetQuickLink();
		widgetQuickLink.setWidgetPreference(widgets);
		widgetQuickLink.setQuickLinkPreference(quickLinks);
		
		DashboardPreference dp = dashboardPreferenceRepository.findById(0).get();
		DashboardPreference dashboard = new DashboardPreference(0, LocalDateTime.now(),
		LocalDateTime.now(), dp.getWidgetQuickLink());
		
//		DashboardPreference dashboard = new DashboardPreference(0, LocalDateTime.now(),
//				LocalDateTime.now(), widgetQuickLink);
//
		return Optional.of(dashboard);
	}
	
	public List<Integer> findAllUserPreferenceIdsByWidgetId(Long widgetId) {
		System.out.println(dashboardPreferenceRepository.findUserPreferenceIdsByWidgetId(widgetId));
        return dashboardPreferenceRepository.findUserPreferenceIdsByWidgetId(widgetId);
    }
	
	public List<DashboardPreference> getAllDashboards(){
		List<DashboardPreference> allDashboards = dashboardPreferenceRepository.findAll();
		System.out.println(allDashboards);
		return allDashboards;
	}
	

}
