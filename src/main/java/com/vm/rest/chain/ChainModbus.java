package com.vm.rest.chain;

import com.vm.modbus.entity.ModbusBodyQuery;
import com.vm.rest.tasks.TaskTRM251;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
@ComponentScan(basePackages = {"com.vm.rest.tasks"})
public class ChainModbus extends Thread{

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public static Queue<ModbusBodyQuery> modbusBodyQueryQueue = new LinkedList<>();

    private final TaskTRM251 taskTRM251;

    @Autowired
    public ChainModbus(final TaskTRM251 taskTRM251){
        this.taskTRM251 = taskTRM251;
        this.start();
    }

    @Override
    public void run(){
        while (!this.isInterrupted()){
            try {

                taskTRM251.readModbusAndWriteToTable();

                this.sleep(1000);
            }catch (InterruptedException e){
                String message = e.getMessage();
                LOGGER.error("Interrupted"+this.getClass()+"thread --"+message);
                System.out.println("Interrupted"+this.getClass()+"thread --"+message);
            }
        }
    }
}
