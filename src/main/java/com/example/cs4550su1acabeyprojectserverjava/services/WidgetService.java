package com.example.cs4550su1acabeyprojectserverjava.services;

import com.example.cs4550su1acabeyprojectserverjava.models.Widget;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class WidgetService {

    private List<Widget> widgetList = new ArrayList<Widget>();
    private Random rand = new Random();

    public Widget createWidget(Integer tid, Widget widget) {
        widget.setId(rand.nextInt());
        this.widgetList.add(widget);
        return widget;
    }

    public List<Widget> findAllWidgets() {
        return widgetList;
    }

    public Widget findWidgetById(Integer widgetId) {
        Widget foundWidget = null;
        for (Widget w : widgetList)
            if (w.getId() == widgetId)
                foundWidget = w;

        return foundWidget;
    }

    public List<Widget> findWidgetsForTopic(Integer topicId) {
        List<Widget> topicWidgets = new ArrayList<>();
        for (Widget w : widgetList) {
            if (w.getTopicId() == topicId)
                topicWidgets.add(w);
        }
        return topicWidgets;
    }

    public Integer deleteWidget(Integer widgetId) {
        List<Widget> updatedList = new ArrayList<>();
        Integer didDelete = 0;
        for (Widget w : widgetList) {
            if (w.getId() != widgetId)
                updatedList.add(w);
        }
        didDelete = updatedList.size() == widgetList.size() ? 1 : 0;
        widgetList = updatedList;
        return didDelete;
    }

    public Integer updateWidget(Integer widgetId, Widget updatedWidget) {
        Integer didUpdate = 0;
        List<Widget> updatedList = new ArrayList<>();
        for (Widget w : widgetList) {
            if (w.getId() != widgetId)
                updatedList.add(w);
            else {
                updatedList.add(updatedWidget);
                didUpdate = 1;
            }
        }
        widgetList = updatedList;
        return didUpdate;
    }

}
