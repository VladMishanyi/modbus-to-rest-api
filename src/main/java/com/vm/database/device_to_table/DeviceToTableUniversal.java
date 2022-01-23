package com.vm.database.device_to_table;

import com.vm.database.table.TableModelTRM251;
import com.vm.database.table.TableModelUniversal;
import com.vm.modbus.device.DeviceModelTRM251;
import com.vm.modbus.device.DeviceModelUniversal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
@ComponentScan(basePackages = {"com.vm"})
public class DeviceToTableUniversal {

    private final DeviceModelUniversal deviceModel;

    @Autowired
    public DeviceToTableUniversal(final DeviceModelUniversal deviceModel){
        this.deviceModel = deviceModel;
    }

    public TableModelUniversal convert(){
        final TableModelUniversal tableModel = new TableModelUniversal();
        if (Objects.nonNull(deviceModel)){
            tableModel.setDate(LocalDateTime.now());
            tableModel.setAddress(deviceModel.getAddress());
            tableModel.setRegister(deviceModel.getRegister());
            tableModel.setWrite(deviceModel.isWrite());
            tableModel.setArchive(deviceModel.isArchive());
            tableModel.setHysteresis(deviceModel.getHysteresis());
            tableModel.setValue(deviceModel.getValue());
            tableModel.setRegisterRange(deviceModel.getRegisterRange());
            tableModel.setOffset(deviceModel.getOffset());
            tableModel.setDataType(deviceModel.getDataType());
        }
        return tableModel;
    }
}
