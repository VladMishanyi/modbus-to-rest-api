package com.vm.rest.tasks;

import com.vm.modbus.device.DeviceModelTRM251;
import com.vm.database.device_to_table.DeviceToTableTRM251;
import com.vm.database.service.ServiceTRM251;

public interface TaskTRM251 {

    public ServiceTRM251 getService();

    public DeviceModelTRM251 getDeviceModel();

    public DeviceToTableTRM251 getDeviceToTable();

    void readModbusAndWriteToTable();

}
