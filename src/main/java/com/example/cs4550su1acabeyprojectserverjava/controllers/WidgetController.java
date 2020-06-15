package com.example.cs4550su1acabeyprojectserverjava.controllers;

import com.example.cs4550su1acabeyprojectserverjava.models.Widget;
import com.example.cs4550su1acabeyprojectserverjava.services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class WidgetController {

    @Autowired
    WidgetService widgetService;

    /**
     * parses Widget JSON object from HTTP body encoded as JSON string. Uses WidgetService to create a new Widget
     * instance and add it to the existing collection of widgets for a topic whose ID is tid. Returns the new widget with a unique identifier
     * @param topicId
     * @param widget
     * @return newly created widget
     */
    @PostMapping("/api/topics/{topicId}/widgets")
    public Widget createWidget(@PathVariable String topicId, @RequestBody Widget widget) {
        return widgetService.createWidget(Integer.valueOf(topicId), widget);
    }

    /**
     * uses WidgetService to retrieve collection of all widgets and returns a string encoded as a JSON array for a
     * topic whose ID is tid
     * @param topicId
     * @return
     */
    @GetMapping("/api//topics/{topicId}/widgets")
    public List<Widget> findWidgetsForTopic(
            @PathVariable String topicId) {
        return widgetService.findWidgetsForTopic(Integer.valueOf(topicId));
    }

    /**
     * parses Widget JSON object from HTTP body encoded as JSON string. Uses WidgetService to find widget instance
     * whose id is equal to wid and update the fields to be the new values in widget parameter.
     * Returns 1 if successful, 0 otherwise.
     * @param widgetId
     * @param updatedWidget
     * @return
     */
    @PutMapping("/api/widgets/{widgetId}")
    public Integer updateWidget(
            @PathVariable Integer widgetId,
            @RequestBody Widget updatedWidget) {
        return widgetService.updateWidget(widgetId, updatedWidget);
    }

    /**
     * uses WidgetService to remove widget whose id is wid. Returns 1 if successful, 0 otherwise
     * @param widgetId
     * @return
     */
    @DeleteMapping("/api/widgets/{widgetId}")
    public Integer deleteWidget(
            @PathVariable Integer widgetId) {
        return widgetService.deleteWidget(widgetId);
    }

    /**
     * uses WidgetService to retrieve collection of all widgets and returns a string encoded as a JSON array.
     * @return
     */
    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets() {
        return widgetService.findAllWidgets();
    }

    /**
     * ses WidgetService to retrieve a single widget instance whose id is equal to wid and returns a string encoded as
     * a JSON object.
     * @param widgetId
     * @return
     */
    @GetMapping("/api/widgets/{widgetId}")
    public Widget findWidgetById(
            @PathVariable String widgetId) {
        return widgetService.findWidgetById(Integer.valueOf(widgetId));
    }


}
