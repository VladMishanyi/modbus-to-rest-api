package com.vm.rest.chain;

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

    @Autowired
    public ChainModbus(final TaskUniversal task){
        this.task = task;
        this.start();
    }

    @Override
    public void run(){
        while (!this.isInterrupted()){
            try {
                task.readModbusAndWriteToTable();
                checkQueryQueue();
                this.sleep(1000);
            }catch (InterruptedException e){
                LOGGER.error("Interrupted:" + this.getClass() + "thread --" + e.getMessage());
            }
        }
    }
    private void checkQueryQueue() {
        if (modbusBodyQueryQueue.size() > 0){
            while (!modbusBodyQueryQueue.isEmpty()){
                ModbusBodyQuery body = modbusBodyQueryQueue.poll();
                task.getServiceModbusUniversal().writeDataToRegister(body.getAddress(), body.getRegister(), body.getValue());
            }
        }
    }
}
