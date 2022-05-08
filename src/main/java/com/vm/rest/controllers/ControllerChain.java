package com.vm.rest.controllers;

import com.vm.rest.chain.ChainModbus;
import com.vm.rest.tasks.TaskUniversal;
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
    private TaskUniversal task;
    @Autowired
    public ControllerChain(final ChainModbus chainModbus,
                           final TaskUniversal task){
        this.chainModbus = chainModbus;
        this.task = task;
    }

    @RequestMapping(value = "/modbus", method = RequestMethod.GET)
    public boolean checkStatusModbusChain(){
        return chainModbus.isInterrupted();
    }

    @Scheduled(fixedRate = 1000*60)
    private void loopModbus(){
        if (chainModbus.isInterrupted()){
            chainModbus = new ChainModbus(task);
        }
    }
}