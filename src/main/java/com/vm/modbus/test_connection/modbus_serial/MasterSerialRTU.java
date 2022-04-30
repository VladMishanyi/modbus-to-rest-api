package com.vm.modbus.test_connection.modbus_serial;

import com.serotonin.modbus4j.BatchRead;
import com.vm.modbus.*;
import com.vm.modbus.device.DeviceModelMB110_1TD;
import com.vm.modbus.en.DigsFloat;
import com.vm.modbus.entity.ModbusMasterSerialModel;

import java.util.List;

/**
 * Created by User on 2017-05-15.
 */

public class MasterSerialRTU {

    public static void main(String[] args) throws Exception {
        long startTime;
        final ModbusMasterSerialModel modbusMasterSerialModel = new ModbusMasterSerialModel("/dev/ttyUSB0", 9600, 8, 1, 0, 200, 1);
//        final ModbusMasterSerialModel modbusMasterSerialModel = new ModbusMasterSerialModel("COM29", 9600, 8, 1, 0, 200, 1);

        final ModbusShort modbusShort = new ModbusShortImpl();
        modbusShort.setUseBorders(false);
        final ModbusInteger modbusInteger = new ModbusIntegerImpl();
        modbusInteger.setUseBorders(false);
        final ModbusFloat modbusFloat = new ModbusFloatImpl();
        modbusFloat.setUseBorders(false);
        final DeviceModelMB110_1TD deviceModelMB110_1TD = new DeviceModelMB110_1TD();

        int i = 0;
        while (true){
            System.out.println("----" + i);
            startTime = System.currentTimeMillis();
            BatchRead<Integer> batchRead = new BatchRead<>();

            List<Float> valFloat = modbusFloat.readDataFromModBusDigs(
                    DigsFloat.ONE_DIG,
                    modbusMasterSerialModel,
                    deviceModelMB110_1TD.getDeviceAddress(),
                    batchRead,
                    false,
                    deviceModelMB110_1TD.getModbusLocator0h(),
                    deviceModelMB110_1TD.getModbusLocator5h(),
                    deviceModelMB110_1TD.getModbusLocator6h(),
                    deviceModelMB110_1TD.getModbusLocator7h()
            );
            List<Integer> valShort = modbusInteger.readDataFromModBus(
                    modbusMasterSerialModel,
                    deviceModelMB110_1TD.getDeviceAddress(),
                    batchRead,
                    false,
                    deviceModelMB110_1TD.getModbusLocator3h(),
                    deviceModelMB110_1TD.getModbusLocator4h()
            );

//            if (i == 0){
//                modbusFloat.writeDataToModBus(modbusMasterSerialModel, deviceModelMB110_1TD.getDeviceAddress(),  0.0F, deviceModelMB110_1TD.getModbusLocator7h());
//                System.out.println("try to set weight item as zero");
//            }
//
//            if (i == 0){
//                modbusFloat.writeDataToModBus(modbusMasterSerialModel, deviceModelMB110_1TD.getDeviceAddress(),  100.0F, deviceModelMB110_1TD.getModbusLocator6h());
//                System.out.println("try to set maximum value for sensor");
//            }
//
//            if (i == 0){
//                modbusFloat.writeDataToModBus(modbusMasterSerialModel, deviceModelMB110_1TD.getDeviceAddress(),  0.0F, deviceModelMB110_1TD.getModbusLocator5h());
//                System.out.println("try to set minimum value for sensor");
//            }
//
//            if (i == 0){
//                modbusShort.writeDataToModBus(modbusMasterSerialModel, deviceModelMB110_1TD.getDeviceAddress(), (short) 1, deviceModelMB110_1TD.getModbusLocator4h());
//                System.out.println("try to set sensitivity sensor");
//            }
//
//            if (i == 0){
//                modbusShort.writeDataToModBus(modbusMasterSerialModel, deviceModelMB110_1TD.getDeviceAddress(), (short) 1, deviceModelMB110_1TD.getModbusLocator3h());
//                System.out.println("try to enable or disable use current weight as zero point");
//            }
//
//            if (i == 0){
//                modbusShort.writeDataToModBus(modbusMasterSerialModel, deviceModelMB110_1TD.getDeviceAddress(), (short) 0, deviceModelMB110_1TD.getModbusLocator1h());
//                System.out.println("try to set zero value");
//            }
//
//            if (i == 0){
//                modbusShort.writeDataToModBus(modbusMasterSerialModel, deviceModelMB110_1TD.getDeviceAddress(), (short) 0, deviceModelMB110_1TD.getModbusLocator2h());
//                System.out.println("try to commit a changes");
//            }
//
            System.out.println("Time elapsed: " + (System.currentTimeMillis() - startTime) + "ms");
            valFloat.forEach(System.out::println);
            valShort.forEach(System.out::println);
            System.out.println("----------------------------------------------------------------------------------------");
            Thread.sleep(1000);
            i++;
        }
    }
}
