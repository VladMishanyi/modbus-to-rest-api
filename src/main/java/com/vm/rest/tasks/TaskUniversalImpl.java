package com.vm.rest.tasks;

import com.vm.database.device_to_table.DeviceToTableUniversal;
import com.vm.database.service.ServiceDatabaseUniversal;
import com.vm.database.table.TableModelUniversal;
import com.vm.modbus.cache.MetadataGenerator;
import com.vm.modbus.device.DeviceModelUniversal;
import com.vm.modbus.service.ServiceModbusUniversal;
import com.vm.modbus.service.ServiceModbusUniversalImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = {"com.vm"})
public class TaskUniversalImpl implements TaskUniversal {
    private final ServiceDatabaseUniversal serviceDatabaseUniversal;
    private final ServiceModbusUniversal serviceModbusUniversal;
    private static int counter = 0;

    @Autowired
    public TaskUniversalImpl(final ServiceDatabaseUniversal serviceDatabaseUniversal) {
        this.serviceDatabaseUniversal = serviceDatabaseUniversal;
        this.serviceModbusUniversal = new ServiceModbusUniversalImpl();
    }
    @Override
    public ServiceDatabaseUniversal getServiceDatabaseUniversal() {
        return serviceDatabaseUniversal;
    }
    @Override
    public ServiceModbusUniversal getServiceModbusUniversal() {
        return serviceModbusUniversal;
    }


    @Override
    public void readModbusAndWriteToTable() {
        serviceModbusUniversal.readDataFromRegisterAll();
        counter++;
        if (counter >= 60){
            MetadataGenerator.getDeviceCache().getCache().stream().filter(DeviceModelUniversal::hysteresis).forEachOrdered(ele -> {
                TableModelUniversal tableModel = DeviceToTableUniversal.convert(ele, new TableModelUniversal());
                serviceDatabaseUniversal.addTableDevice(tableModel);
            });
            counter = 0;
        }
    }
}
