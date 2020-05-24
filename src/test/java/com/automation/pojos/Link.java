package com.automation.pojos;

public class Link {

    /*
     {
                    "rel": "self",
                    "href": "http://3.85.41.58:1000/ords/hr/employees/100"
                }
     */

    // This is a collection classs _--> when we use one custom class in another class
    // without inheriting that class HAS-A relationship
    //any map we can represent as an object

    private String rel;
    private String href;

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "Link{" +
                "rel='" + rel + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
