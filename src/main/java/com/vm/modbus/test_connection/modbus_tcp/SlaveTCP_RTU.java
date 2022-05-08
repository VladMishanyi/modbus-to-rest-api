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
        final BasicProcessImage processImage16 = new BasicProcessImage(16);//this device reserved addresses 16-23
        final BasicProcessImage processImage24 = new BasicProcessImage(24);//this device reserved addresses 24-31
        processImage16.setHoldingRegister(0, DataType.TWO_BYTE_INT_UNSIGNED, 3);
        processImage16.setHoldingRegister(1, DataType.FOUR_BYTE_FLOAT, 1.1F);
        processImage16.setHoldingRegister(2, DataType.TWO_BYTE_INT_UNSIGNED, 2);
        processImage16.setHoldingRegister(3, DataType.FOUR_BYTE_FLOAT, 1.2F);
        processImage16.setInputRegister(4, DataType.FOUR_BYTE_FLOAT, 1.3F);
        processImage16.setInputRegister(10, DataType.FOUR_BYTE_FLOAT, 1.4F);

        processImage24.setHoldingRegister(0, DataType.TWO_BYTE_INT_UNSIGNED, 4);
        processImage24.setHoldingRegister(1, DataType.FOUR_BYTE_FLOAT, 1.5F);
        processImage24.setHoldingRegister(2, DataType.TWO_BYTE_INT_UNSIGNED, 6);
        processImage24.setHoldingRegister(3, DataType.FOUR_BYTE_FLOAT, 1.6F);
        processImage24.setInputRegister(4, DataType.FOUR_BYTE_FLOAT, 1.7F);
        processImage24.setInputRegister(10, DataType.FOUR_BYTE_FLOAT, 1.8);


        Runnable thread = () -> {
            int i = 10;
            float f = 10.1F;
            while (!Thread.interrupted()){
                try {
                    i++;
                    f++;
//                    processImage16.setHoldingRegister(0, DataType.TWO_BYTE_INT_UNSIGNED, i);
//                    processImage16.setHoldingRegister(1, DataType.FOUR_BYTE_FLOAT, i*0.1);
//                    processImage16.setHoldingRegister(2, DataType.TWO_BYTE_INT_UNSIGNED, i+2);
//                    processImage16.setHoldingRegister(3, DataType.FOUR_BYTE_FLOAT, i*0.2F);
//                    processImage16.setInputRegister(4, DataType.FOUR_BYTE_FLOAT, i*0.3F);
                    processImage16.setInputRegister(10, DataType.FOUR_BYTE_FLOAT, i*0.4F);

//                    processImage24.setHoldingRegister(0, DataType.TWO_BYTE_INT_UNSIGNED, i);
//                    processImage24.setHoldingRegister(1, DataType.FOUR_BYTE_FLOAT, i*0.5F);
//                    processImage24.setHoldingRegister(2, DataType.TWO_BYTE_INT_UNSIGNED, i-2);
//                    processImage24.setHoldingRegister(3, DataType.FOUR_BYTE_FLOAT, i*0.6F);
                    processImage24.setInputRegister(4, DataType.FOUR_BYTE_FLOAT, i*0.7F);
//                    processImage24.setInputRegister(10, DataType.FOUR_BYTE_FLOAT, i*0.8);
                    System.out.println(i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(thread).start();


        try {
            slave.addProcessImage(processImage16);
            slave.addProcessImage(processImage24);
            slave.start();
        }
        catch (Exception e) {
            e.printStackTrace();
            slave.stop();
        }
    }
}
