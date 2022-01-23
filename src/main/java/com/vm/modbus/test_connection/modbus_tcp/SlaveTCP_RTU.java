package com.vm.modbus.test_connection.modbus_tcp;

import com.serotonin.modbus4j.BasicProcessImage;
import com.serotonin.modbus4j.ModbusSlaveSet;
import com.serotonin.modbus4j.code.DataType;

/**
 * Created by vlad on 19.05.17.
 */
public class SlaveTCP_RTU {

    public static void main(String[] args){

        final ModbusFactoryExtended factory = new ModbusFactoryExtended();

        final ModbusSlaveSet slave = factory.createTcpPortSlave(2222, false);

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
