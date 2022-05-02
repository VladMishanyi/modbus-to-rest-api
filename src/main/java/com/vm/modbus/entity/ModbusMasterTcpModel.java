package com.vm.modbus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.ip.IpParameters;

import java.io.Serializable;

/**
 * Created by KIP-PC99 on 03.01.2019.
 */
public class ModbusMasterTcpModel implements Serializable {
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

    @JsonIgnore
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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }
}
