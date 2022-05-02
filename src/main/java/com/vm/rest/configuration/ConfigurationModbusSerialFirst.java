//package com.vm.rest.configuration;
//
//import com.vm.modbus.entity.ModbusMasterSerialModel;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//@Configuration
//@ComponentScan(basePackages = {"com.vm.modbus"})
//@PropertySource(value = "classpath:application.properties")
//public class ConfigurationModbusSerialFirst {
//
//    @Value("${modbusFirst.commPortId}")
//    private String commPortId;
//
//    @Value("${modbusFirst.baduRate}")
//    private int baduRate;
//
//    @Value("${modbusFirst.dataBits}")
//    private int dataBits;
//
//    @Value("${modbusFirst.stopBits}")
//    private int stopBits;
//
//    @Value("${modbusFirst.parity}")
//    private int parity;
//
//    @Value("${modbusFirst.timeout}")
//    private int timeout;
//
//    @Value("${modbusFirst.retries}")
//    private int retries;
//
//    @Bean(name = "modbusMasterSerialFirst")
//    public ModbusMasterSerialModel modbusMasterSerialFirst(){
//        return new ModbusMasterSerialModel(commPortId, baduRate, dataBits, stopBits, parity, timeout, retries);
//    }
//}
