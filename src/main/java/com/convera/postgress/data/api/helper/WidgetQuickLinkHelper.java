package com.convera.postgress.data.api.helper;

import java.util.List;

import com.convera.postgress.data.api.repository.model.QuickLink;
import com.convera.postgress.data.api.repository.model.Widget;
import com.convera.postgress.data.api.repository.model.WidgetQuickLink;

public final class WidgetQuickLinkHelper {
	
	
	public static WidgetQuickLink getWidgetQuickLink() {
		
		List<Widget> widgets = WidgetsHelper.getDefaultWidgets();
		List<QuickLink> quickLinks = QuickLinkHelper.getAllQuickLinks();
		
		WidgetQuickLink wq = new WidgetQuickLink(widgets, quickLinks);

		return wq;
	}

}
