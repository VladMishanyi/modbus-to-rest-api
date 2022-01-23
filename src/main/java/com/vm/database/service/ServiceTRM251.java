package com.vm.database.service;

import com.vm.database.table.TableModelTRM251;
import com.vm.modbus.device.DeviceModelTRM251;

import java.time.LocalDateTime;
import java.util.List;

public interface ServiceTRM251 {
    List<TableModelTRM251> findByDateBetween (final LocalDateTime start, final LocalDateTime end);
    TableModelTRM251 findLastValueByDate ();
    void addTableDevice(final TableModelTRM251 tableModel);
    void addAllTableDevice(final List<TableModelTRM251> tableModelTRM138List);

    DeviceModelTRM251 readDataFromRegisterAll();
    DeviceModelTRM251 readDataFromRegister0();
    DeviceModelTRM251 readDataFromRegister1();
}
