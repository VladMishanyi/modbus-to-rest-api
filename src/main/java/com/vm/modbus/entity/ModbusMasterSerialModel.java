package com.vm.modbus.entity;

import com.serotonin.io.serial.SerialParameters;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;


/**
 * Created by User on 2018-02-22.
 */
public class ModbusMasterSerialModel {
    private String port;
    private int baduRate;
    private int dataBits;
    private int stopBits;
    private int parity;
    private int timeout;
    private int retries;
    public ModbusMasterSerialModel(){}
    public ModbusMasterSerialModel(final String port,
                                   final int baduRate,
                                   final int dataBits,
                                   final int stopBits,
                                   final int parity,
                                   final int timeout,
                                   final int retries){
        this.port = port;
        this.baduRate = baduRate;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.parity = parity;
        this.timeout = timeout;
        this.retries = retries;
    }

    public ModbusMaster getMaster() {
        ModbusFactory factory = new ModbusFactory();
        SerialParameters params = new SerialParameters();
        params.setCommPortId(port);
        params.setBaudRate(baduRate);
        params.setDataBits(dataBits);
        params.setStopBits(stopBits);
        params.setParity(parity);

        ModbusMaster master = factory.createRtuMaster(params);
        master.setTimeout(timeout);
        master.setRetries(retries);
        return master;
    }
}
