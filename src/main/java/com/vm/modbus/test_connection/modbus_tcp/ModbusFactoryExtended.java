package com.vm.modbus.test_connection.modbus_tcp;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusSlaveSet;
import com.serotonin.modbus4j.ip.tcp.TcpSlave;

public class ModbusFactoryExtended extends ModbusFactory {

    public ModbusSlaveSet createTcpPortSlave(int port, boolean encapsulated) {
        return new TcpSlave(port, encapsulated);
    }
}
