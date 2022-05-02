//package com.vm.rest.configuration;
//
//import com.serotonin.modbus4j.BatchRead;
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//
//@Configuration
//@ComponentScan(basePackages = {"com.vm"})
//public class ConfigurationRoot {
//
//    @Bean
//    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public BatchRead<Integer> batchRead(){
//        return new BatchRead<>();
//    }
//
////    @Bean
////    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
////    public SimpleDateFormat simpleDateFormat(){
////        return new SimpleDateFormat("yyyy-MM-dd HH:mm");
////    }
////
////    @Bean
////    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
////    public Date getDate(){
////        return new Date();
////    }
//}
