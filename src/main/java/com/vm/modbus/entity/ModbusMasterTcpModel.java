package com.vm.modbus.entity;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.ip.IpParameters;

/**
 * Created by KIP-PC99 on 03.01.2019.
 */
public class ModbusMasterTcpModel {
    private String host;
    private int port;
    private int timeout;
    private int retries;
    public ModbusMasterTcpModel(){}
    public ModbusMasterTcpModel(String host,
                                int port,
                                int timeout,
                                int retries){
        this.host = host;
        this.port = port;
        this.timeout = timeout;
        this.retries = retries;
    }

    public ModbusMaster getMaster(){
        ModbusFactory modbusFactory = new ModbusFactory();
        IpParameters ipParameters = new IpParameters();
        ipParameters.setHost(host);
        ipParameters.setPort(port);

        ModbusMaster modbusMaster = modbusFactory.createTcpMaster(ipParameters, true);
        modbusMaster.setTimeout(timeout);
        modbusMaster.setRetries(retries);
        return modbusMaster;
    }
}
