package com.example.cs4550su1acabeyprojectserverjava.models;

//@Entity
//@Table(name="widgets")
public class Widget {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name; // Optional name of the widget
    private String type; // Type of the widget, e.g., Heading, List, Paragraph, Image, YouTube, HTML, Link
    private Integer widgetOrder; //  Order with respect to widgets in the same list
    private String text; // Plain text useful for heading text, paragraph text, link text, etc
    private String url; // Absolute or relative URL referring to online resource
    private String size; // Useful to represent size of widget, e.g., heading size
    private Integer width, height; // Widget's horizontal & vertical size, e.g., Image's or YouTube's width & height
    private String cssClass; // CSS class implementing some CSS rule and transformations configured in some CSS rule
    private String style; // CSS transformations applied to the widget
    private String value; // Some arbitrary initial value interpreted by the widget
    private Integer topicId; // Unique ID of parent topic

    public Widget() {
    }

    public Widget(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Widget(Integer widgetOrder, String text, String url, String size, Integer width, Integer height, String cssClass, String style, String value) {
        this.widgetOrder = widgetOrder;
        this.text = text;
        this.url = url;
        this.size = size;
        this.width = width;
        this.height = height;
        this.cssClass = cssClass;
        this.style = style;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getWidgetOrder() {
        return widgetOrder;
    }

    public void setWidgetOrder(Integer widgetOrder) {
        this.widgetOrder = widgetOrder;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

}
