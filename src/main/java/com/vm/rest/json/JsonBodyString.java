package com.vm.rest.json;

import org.springframework.stereotype.Component;

/**
 * Created by KIP-PC99 on 15.01.2019.
 */
@Component
public class JsonBodyString {

    private String value;

    public JsonBodyString() {
    }

    public JsonBodyString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
