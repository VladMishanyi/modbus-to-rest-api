package com.vm.modbus.device;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MetadataGenerator {

    public static void main(String[] args) throws IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
//        DeviceModelUniversal universal = new DeviceModelUniversal(16, 0, false, true, "1", "8","2", 4, 4,8);
//        DeviceCache deviceCache = new DeviceCache(universal);
//        jsonMapper.writeValue(new File("./modbus-configuration.json"), deviceCache);
        DeviceCache result = jsonMapper.readValue(new File("./modbus-configuration.json"), DeviceCache.class);
        System.out.println(result.toString());
    }
}
