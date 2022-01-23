package com.vm.rest.controllers;

import com.vm.rest.chain.ChainModbus;
import com.vm.rest.tasks.TaskTRM251;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableScheduling
@ComponentScan(basePackages = {"com.vm.rest.chain"})
@RequestMapping(value = "/chain")
public class ControllerChain {

    private ChainModbus chainModbus;

    private TaskTRM251 taskTRM251;

    @Autowired
    public ControllerChain(final ChainModbus chainModbus,
                           final TaskTRM251 taskTRM251){
        this.chainModbus = chainModbus;
        this.taskTRM251 = taskTRM251;
    }

    @RequestMapping(value = "/modbus", method = RequestMethod.GET)
    public boolean checkStatusModbusChain(){
        return chainModbus.isAlive();
    }

    @Scheduled(fixedRate = 1000*60)
    private void loopModbus(){
        if (!chainModbus.isAlive()){
            chainModbus = new ChainModbus(taskTRM251);
        }
    }
}