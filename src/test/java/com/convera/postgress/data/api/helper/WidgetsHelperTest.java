package com.convera.postgress.data.api.helper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.convera.postgress.data.api.repository.model.Widget;

public final class WidgetsHelperTest {

	public static List<Widget> getDefaultWidgets(){
		
		Widget w1 = new Widget(1L, "Payment Outcomes Widget", "Payment Template", "http:convera/image/1", true,  LocalDateTime.now(), LocalDateTime.now(), 2);
		Widget w2 = new Widget(2L, "Market News Widget", "Market News Template", "http:convera/market-news/1", true, LocalDateTime.now(), LocalDateTime.now(), 3);
		Widget w3 = new Widget(3L, "Quick Quotes Widget", "Quotes Template", "http:convera/quotes/1", true,  LocalDateTime.now(), LocalDateTime.now(), 4);
		Widget w4 = new Widget(4L, "Recent PTCs Widget", "Recent PTCs Template", "http:convera/ptc/", true,  LocalDateTime.now(), LocalDateTime.now(), 5);
		Widget w5 = new Widget(5L, "Total Payments KPI Widget", "Total Payments Template", "http:convera/totalPayments/", true,  LocalDateTime.now(), LocalDateTime.now(), 6);
		Widget w6 = new Widget(6L, "Frequently Paid payees Widget", "Frequently Paid payees Template", "http:convera/frequentPayees/", true,  LocalDateTime.now(), LocalDateTime.now(), 7);
		Widget w7 = new Widget(7L, "Recent Payments Widget", "Recent Payments Template", "http:convera/recent/", true,  LocalDateTime.now(), LocalDateTime.now(), 9);

		List<Widget> widgetsList = new ArrayList<Widget>();
		widgetsList.add(w1);
		widgetsList.add(w2);
		widgetsList.add(w3);
		widgetsList.add(w4);
		widgetsList.add(w5);
		widgetsList.add(w6);
		widgetsList.add(w7);
		
		return widgetsList;
	}
}
