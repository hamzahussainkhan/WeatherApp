
package com.example.weatherapp;

import java.util.LinkedHashMap;
import java.util.Map;



public class Sys {

    private String pod;
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
