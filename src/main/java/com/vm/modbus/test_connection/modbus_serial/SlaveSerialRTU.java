package com.vm.modbus.test_connection.modbus_serial;

import com.serotonin.io.serial.SerialParameters;
import com.serotonin.modbus4j.BasicProcessImage;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusSlaveSet;
import com.serotonin.modbus4j.code.DataType;

/**
 * Created by vlad on 19.05.17.
 */
public class SlaveSerialRTU {
    public static void main(String[] args) {

        final SerialParameters params = new SerialParameters();
//        params.setCommPortId("COM3");
        params.setCommPortId("/dev/ttyUSB0");
//        params.setPortOwnerName("ubuntu");
        params.setBaudRate(9600);
        params.setDataBits(8);
        params.setStopBits(1);
        params.setParity(0);

        final ModbusFactory factory = new ModbusFactory();

        final ModbusSlaveSet slave = factory.createRtuSlave(params);

        final BasicProcessImage processImage = new BasicProcessImage(1);//slave addres
        processImage.setHoldingRegister(0, DataType.TWO_BYTE_INT_UNSIGNED, 220);

        try {
            slave.addProcessImage(processImage);
            slave.start();
        }
        catch (Exception e) {
            e.printStackTrace();
            slave.stop();
        }
    }
}
