package com.vm.modbus.reposiptory;

import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.ModbusLocator;
import com.vm.modbus.ModbusFloat;
import com.vm.modbus.ModbusInteger;
import com.vm.modbus.ModbusShort;
import com.vm.modbus.device.DeviceCache;
import com.vm.modbus.entity.ModbusMasterSerialModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ComponentScan(basePackages = {"com.vm.modbus"})
public class RepositoryModbusUniversalImpl implements RepositoryModbusUniversal {

    private final ModbusMasterSerialModel modbusMasterSerialFirst;
//    private final DeviceModelTRM251 deviceModel;
    private final DeviceCache deviceCache;
    private final BatchRead<Integer> batchRead;
    private final ModbusFloat modbusFloat;
    private final ModbusShort modbusShort;
    private final ModbusInteger modbusInteger;

    @Autowired
    public RepositoryModbusUniversalImpl(final ModbusMasterSerialModel modbusMasterSerialFirst,
//                                         final DeviceModelTRM251 deviceModel,
                                         final DeviceCache deviceCache,
                                         final BatchRead<Integer> batchRead,
                                         final ModbusFloat modbusFloat,
                                         final ModbusShort modbusShort,
                                         final ModbusInteger modbusInteger) {
        this.modbusMasterSerialFirst = modbusMasterSerialFirst;
//        this.deviceModel = deviceModel;
        this.deviceCache = deviceCache;
        this.batchRead = batchRead;
        this.modbusFloat = modbusFloat;
        this.modbusShort = modbusShort;
        this.modbusInteger = modbusInteger;
    }

    @Override
    public DeviceCache readDataFromRegisterAll(final int address,
                                               final int register,
                                               final boolean useBorders,
                                               final short borderMin,
                                               final short borderMax,
                                               final float digsFloat,
                                               final boolean enableBatch) {
        if (modbusFloat != null && modbusInteger != null){
            modbusFloat.setUseBorders(useBorders, borderMax, borderMin);
            modbusInteger.setUseBorders(useBorders, borderMax, borderMin);
            deviceCache.getCache().forEach(fil -> {
                if (fil.getDataType() == 8) {
                    final List<Float> list =  modbusFloat.readDataFromModBusDigs(
                            digsFloat,
                            modbusMasterSerialFirst,
                            fil.getAddress(),
                            batchRead,
                            enableBatch,
                            new ModbusLocator(fil.getAddress(), fil.getRegisterRange(), fil.getOffset(), fil.getDataType()));
                    fil.setValue(list.get(0).toString());
                }
                if (fil.getDataType() == 2) {
                    final List<Integer> list =  modbusInteger.readDataFromModBus(
                            modbusMasterSerialFirst,
                            fil.getAddress(),
                            batchRead,
                            enableBatch,
                            new ModbusLocator(fil.getAddress(), fil.getRegisterRange(), fil.getOffset(), fil.getDataType()));
                    fil.setValue(list.get(0).toString());
                }

            });
        }
        return deviceCache;
    }

    @Override
    public DeviceCache readDataFromRegister(final int address,
                                            final int register,
                                            final boolean useBorders,
                                            final short borderMin,
                                            final short borderMax,
                                            final float digsFloat,
                                            final boolean enableBatch) {
        if (modbusFloat != null && modbusInteger != null){
            modbusFloat.setUseBorders(useBorders, borderMax, borderMin);
            modbusInteger.setUseBorders(useBorders, borderMax, borderMin);
            deviceCache.getCache().stream().filter( c -> (c.getAddress() == address) && (c.getRegister() == register)).forEachOrdered( fil -> {
                if (fil.getDataType() == 8) {
                    final List<Float> list =  modbusFloat.readDataFromModBusDigs(
                            digsFloat,
                            modbusMasterSerialFirst,
                            fil.getAddress(),
                            batchRead,
                            enableBatch,
                            new ModbusLocator(fil.getAddress(), fil.getRegisterRange(), fil.getOffset(), fil.getDataType()));
                    fil.setValue(list.get(0).toString());
                }
                if (fil.getDataType() == 2) {
                    final List<Integer> list =  modbusInteger.readDataFromModBus(
                            modbusMasterSerialFirst,
                            fil.getAddress(),
                            batchRead,
                            enableBatch,
                            new ModbusLocator(fil.getAddress(), fil.getRegisterRange(), fil.getOffset(), fil.getDataType()));
                    fil.setValue(list.get(0).toString());
                }

            });
        }
        return deviceCache;
    }

    @Override
    public DeviceCache writeDataToRegister (final int address,
                                            final int register,
                                            final String value) {
        if (modbusFloat != null && modbusShort != null) {
            deviceCache.getCache().stream().filter( c -> (c.getAddress() == address) && (c.getRegister() == register)).forEachOrdered( fil -> {
                if (fil.getDataType() == 8) {
                    modbusFloat.writeDataToModBus (
                            modbusMasterSerialFirst,
                            fil.getAddress(),
                            Float.valueOf(value),
                            new ModbusLocator(fil.getAddress(), fil.getRegisterRange(), fil.getOffset(), fil.getDataType()));
                    fil.setValue(value);
                }
                if (fil.getDataType() == 2) {
                    modbusShort.writeDataToModBus (
                            modbusMasterSerialFirst,
                            fil.getAddress(),
                            Short.valueOf(value),
                            new ModbusLocator(fil.getAddress(), fil.getRegisterRange(), fil.getOffset(), fil.getDataType()));
                    fil.setValue(value);
                }

            });
        }
        return deviceCache;
    }
}
