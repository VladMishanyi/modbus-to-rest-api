package com.vm.rest.chain;

import com.vm.modbus.cache.MetadataGenerator;
import com.vm.modbus.entity.ModbusBodyQuery;
import com.vm.rest.tasks.TaskUniversal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
@ComponentScan(basePackages = {"com.vm.rest.tasks"})
public class ChainModbus extends Thread{

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public static Queue<ModbusBodyQuery> modbusBodyQueryQueue = new LinkedBlockingQueue<>();

    private final TaskUniversal task;

    private boolean triggerInit = true;

    @Autowired
    public ChainModbus(final TaskUniversal task){
        this.task = task;
        this.start();
    }

    private void initConfig() {
        try {
            MetadataGenerator.readFromJsonFileModbusMasterSerialCache();
            MetadataGenerator.readFromJsonFileModbusMasterTcpCache();
            MetadataGenerator.readFromJsonFileDeviceCache();
            triggerInit = false;
        } catch (Exception e) {
            LOGGER.error("Can't init configuration from json: " + e.getMessage());
        }
    }

    @Override
    public void run(){
        while (!this.isInterrupted()){
            try {
                if (triggerInit) {
                    this.initConfig();
                }
                task.readModbusAndWriteToTable();
                checkQueryQueue();
                this.sleep(1000);
            }catch (InterruptedException e){
                LOGGER.error("Interrupted:" + this.getClass() + "thread --" + e.getMessage());
            }
        }
    }
    private void checkQueryQueue() {
        if (!modbusBodyQueryQueue.isEmpty()) {
            while (!modbusBodyQueryQueue.isEmpty()) {
                ModbusBodyQuery body = modbusBodyQueryQueue.poll();
                if (body.isWrite()) task.getServiceModbusUniversal().writeDataToRegister(body.getAddress(), body.getRegister(), body.getValue());
                if (!body.isWrite()) task.getServiceModbusUniversal().readDataFromRegister(body.getAddress(), body.getRegister());
                if (body.getAddress() == 0 && body.getRegister() == 0 && body.getValue() == null && !body.isWrite()) task.getServiceModbusUniversal().readDataFromRegisterAll();
            }
        }
    }
}
