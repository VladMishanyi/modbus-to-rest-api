package com.vm.rest.tasks;

import com.vm.database.service.ServiceDatabaseUniversal;
import com.vm.modbus.service.ServiceModbusUniversal;

public interface TaskUniversal {
    ServiceDatabaseUniversal getServiceDatabaseUniversal();
    ServiceModbusUniversal getServiceModbusUniversal();
    void readModbusAndWriteToTable();
}
