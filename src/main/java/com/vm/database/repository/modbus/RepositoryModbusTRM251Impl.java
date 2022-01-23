package com.vm.database.repository.modbus;

import com.serotonin.modbus4j.BatchRead;
import com.vm.modbus.device.DeviceModelTRM251;
import com.vm.modbus.ModbusFloat;
import com.vm.modbus.ModbusShort;
import com.vm.modbus.entity.ModbusMasterSerialModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@ComponentScan(basePackages = {"com.vm.modbus"})
public class RepositoryModbusTRM251Impl implements RepositoryModbusTRM251 {

    private final ModbusMasterSerialModel modbusMasterSerialFirst;

    private final DeviceModelTRM251 deviceModel;

    private final BatchRead<Integer> batchRead;

    private final ModbusFloat modbusFloat;

    private final ModbusShort modbusShort;

    @Autowired
    public RepositoryModbusTRM251Impl(final ModbusMasterSerialModel modbusMasterSerialFirst,
                                      final DeviceModelTRM251 deviceModel,
                                      final BatchRead<Integer> batchRead,
                                      final ModbusFloat modbusFloat,
                                      final ModbusShort modbusShort) {
        this.modbusMasterSerialFirst = modbusMasterSerialFirst;
        this.deviceModel = deviceModel;
        this.batchRead = batchRead;
        this.modbusFloat = modbusFloat;
        this.modbusShort = modbusShort;
    }

    @Override
    public DeviceModelTRM251 readDataFromRegisterAll(final boolean useBorders,
                                                     final short borderMin,
                                                     final short borderMax,
                                                     final float digsFloat,
                                                     final boolean enableBatch) {
        if (Objects.nonNull(modbusFloat)){
            modbusFloat.setUseBorders(useBorders, borderMax, borderMin);
            final List<Float> listFloat =  modbusFloat.readDataFromModBusDigs(
                    digsFloat,
                    modbusMasterSerialFirst,
                    deviceModel.getDeviceAddress(),
                    batchRead,
                    enableBatch,
                    deviceModel.getModbusLocator0i(),
                    deviceModel.getModbusLocator1i()
                  );
            deviceModel.setInputRegister0(listFloat.get(0));
            deviceModel.setInputRegister1(listFloat.get(1));
        }
        return deviceModel;
    }

    @Override
    public DeviceModelTRM251 readDataFromRegister0(final boolean useBorders,
                                                   final short borderMin,
                                                   final short borderMax,
                                                   final float digsFloat,
                                                   final boolean enableBatch) {
        if (Objects.nonNull(modbusFloat)){
            modbusFloat.setUseBorders(useBorders, borderMax, borderMin);
            final List<Float> listFloat =  modbusFloat.readDataFromModBusDigs(
                    digsFloat,
                    modbusMasterSerialFirst,
                    deviceModel.getDeviceAddress(),
                    batchRead,
                    enableBatch,
                    deviceModel.getModbusLocator0i());
            deviceModel.setInputRegister0(listFloat.get(0));
        }
        return deviceModel;
    }

    @Override
    public DeviceModelTRM251 readDataFromRegister1(final boolean useBorders,
                                                   final short borderMin,
                                                   final short borderMax,
                                                   final float digsFloat,
                                                   final boolean enableBatch) {
        if (Objects.nonNull(modbusFloat)){
            modbusFloat.setUseBorders(useBorders, borderMax, borderMin);
            final List<Float> listFloat =  modbusFloat.readDataFromModBusDigs(
                    digsFloat,
                    modbusMasterSerialFirst,
                    deviceModel.getDeviceAddress(),
                    batchRead,
                    enableBatch,
                    deviceModel.getModbusLocator1i());
            deviceModel.setInputRegister1(listFloat.get(1));
        }
        return deviceModel;
    }
}
