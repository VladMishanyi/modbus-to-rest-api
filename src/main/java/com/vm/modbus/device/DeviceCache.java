package com.vm.modbus.device;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

@Component
@Data
@NoArgsConstructor
public class DeviceCache implements Serializable {
    private List<DeviceModelUniversal> cache = new ArrayList<>();

    public DeviceCache(DeviceModelUniversal deviceModelUniversal) {
        this.cache.add(deviceModelUniversal);
    }
}
