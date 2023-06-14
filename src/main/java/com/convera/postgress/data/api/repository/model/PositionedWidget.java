package com.convera.postgress.data.api.repository.model;

public class PositionedWidget {
	
    private Widget widget;
    private int position;

    public PositionedWidget(Widget widget, int position) {
        this.widget = widget;
        this.position = position;
    }

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
