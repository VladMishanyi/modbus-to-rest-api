package com.vm.database.device_to_table;

import com.vm.database.table.TableModelUniversal;
import com.vm.modbus.device.DeviceModelUniversal;

import java.time.LocalDateTime;
import java.util.Objects;
public class DeviceToTableUniversal {
    public static TableModelUniversal convert(final DeviceModelUniversal deviceModel,
                                              final TableModelUniversal tableModel){
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
