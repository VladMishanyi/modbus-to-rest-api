package com.vm.modbus.device;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serotonin.modbus4j.code.RegisterRange;
import com.vm.modbus.lib.HysComparator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceModelUniversal implements DeviceModel  {

    private int address;
    private int register;
    private boolean write;
    private boolean archive;
    private String hysteresis;
    private String value;
    private String oldValue;
    private int registerRange;
    private int offset;
    private int dataType;

    @Override
    public boolean hysteresis(){
        boolean inner = false;
        if (dataType == 8) inner = HysComparator.compare(Float.parseFloat(oldValue), Float.parseFloat(value), Float.parseFloat(hysteresis));
        if (dataType == 2) inner = HysComparator.compare(Integer.parseInt(oldValue), Integer.parseInt(value), Integer.parseInt(hysteresis));
        if (inner) oldValue = value;
        return inner;
    }
}
