package com.vm.database.service;

import com.vm.modbus.device.DeviceModelTRM251;
import com.vm.modbus.en.DigsFloat;
import com.vm.database.repository.database.RepositoryDatabaseTRM251;
import com.vm.database.repository.modbus.RepositoryModbusTRM251;
import com.vm.database.table.TableModelTRM251;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@ComponentScan(basePackages = {"com.vm"})
public class ServiceTRM251Impl implements ServiceTRM251 {


    private final RepositoryDatabaseTRM251 repositoryDatabase;

    private final RepositoryModbusTRM251 repositoryModbus;

    @Autowired
    public ServiceTRM251Impl(RepositoryDatabaseTRM251 repositoryDatabase, RepositoryModbusTRM251 repositoryModbus) {
        this.repositoryDatabase = repositoryDatabase;
        this.repositoryModbus = repositoryModbus;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TableModelTRM251> findByDateBetween (final LocalDateTime start, final LocalDateTime end){
        return repositoryDatabase.findByDateBetween(start, end);
    }
    @Transactional(readOnly = true)
    @Override
    public TableModelTRM251 findLastValueByDate (){
        return repositoryDatabase.findLastValueByDate();
    }
    @Transactional
    @Override
    public void addTableDevice(final TableModelTRM251 tableModel){
        repositoryDatabase.saveAndFlush(tableModel);
    }
    @Transactional
    @Override
    public void addAllTableDevice(final List<TableModelTRM251> tableModelList){
        repositoryDatabase.saveAll(tableModelList);
    }

    @Override
    public DeviceModelTRM251 readDataFromRegisterAll(){
        return repositoryModbus.readDataFromRegisterAll(true, (short) 0, (short) 500, DigsFloat.ONE_DIG, false);

    }
    @Override
    public DeviceModelTRM251 readDataFromRegister0(){
        return repositoryModbus.readDataFromRegister0(true, (short) 0, (short) 500, DigsFloat.ONE_DIG, false);
    }
    @Override
    public DeviceModelTRM251 readDataFromRegister1(){
        return repositoryModbus.readDataFromRegister1(true, (short) 0, (short) 500, DigsFloat.ONE_DIG, false);
    }
}
