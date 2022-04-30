//package com.vm.database.device_to_table;
//
//import com.vm.database.table.TableModelTRM251;
//import com.vm.modbus.device.DeviceModelTRM251;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.Objects;
//
//@Component
//@ComponentScan(basePackages = {"com.vm"})
//public class DeviceToTableTRM251 {
//
//    private final DeviceModelTRM251 deviceModel;
//
//    @Autowired
//    public DeviceToTableTRM251(final DeviceModelTRM251 deviceModel){
//        this.deviceModel = deviceModel;
//    }
//
//    public TableModelTRM251 convert(){
//        final TableModelTRM251 tableModelTRM251 = new TableModelTRM251();
//        if (Objects.nonNull(deviceModel)){
//            tableModelTRM251.setDate(LocalDateTime.now());
//            tableModelTRM251.setInputRegister0(deviceModel.getInputRegister0());
//            tableModelTRM251.setInputRegister1(deviceModel.getInputRegister1());
//        }
//        return tableModelTRM251;
//    }
//}
