package com.vm.modbus.test_connection.modbus_serial;

import com.vm.modbus.cache.MetadataGenerator;
import com.vm.modbus.service.ServiceModbusUniversal;
import com.vm.modbus.service.ServiceModbusSerialUniversalImpl;

/**
 * Created by User on 2017-05-15.
 */

public class MasterSerialRTU {
    public static void main(String[] args) throws Exception {
        MetadataGenerator.readFromJsonFileModbusMasterSerialCache();
        MetadataGenerator.readFromJsonFileDeviceCache();
        long startTime;
        ServiceModbusUniversal service = new ServiceModbusSerialUniversalImpl();

        int i = 0;
        while (true){
            System.out.println("count: " + i);
            startTime = System.currentTimeMillis();
            service.readDataFromRegister(16, 7);
            service.writeDataToRegister(16, 1, "1.3");
            MetadataGenerator.getDeviceCache().getCache().forEach(System.out::println);
            System.out.println("Time elapsed: " + (System.currentTimeMillis() - startTime) + "ms");
            System.out.println("----------------------------------------------------------------------------------------");
            Thread.sleep(1000);
            i++;
        }
    }
}
