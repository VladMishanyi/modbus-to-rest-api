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
            service.readDataFromRegister(16, 0);
            service.readDataFromRegister(16, 1);
            service.readDataFromRegister(16, 2);
            service.readDataFromRegister(16, 3);
            service.readDataFromRegister(16, 4);
            service.readDataFromRegister(16, 10);
            service.readDataFromRegister(24, 0);
            service.readDataFromRegister(24, 1);
            service.readDataFromRegister(24, 2);
            service.readDataFromRegister(24, 3);
            service.readDataFromRegister(24, 4);
            service.readDataFromRegister(24, 10);

//            service.writeDataToRegister(16,0, "70");
//            service.writeDataToRegister(24,10, "70.0");
            MetadataGenerator.getDeviceCache().getCache().forEach(System.out::println);
            System.out.println("Time elapsed: " + (System.currentTimeMillis() - startTime) + "ms");
            System.out.println("----------------------------------------------------------------------------------------");
            Thread.sleep(1000);
            i++;
        }
    }
}
