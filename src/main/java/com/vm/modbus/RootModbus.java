package com.vm.modbus;

import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.ModbusLocator;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.vm.modbus.entity.ModbusMasterSerialModel;
import com.vm.modbus.entity.ModbusMasterTcpModel;

import java.util.List;

/**
 * Created by KIP-PC99 on 20.06.2018.
 */
public interface RootModbus<E extends Number> {

    void setUseBorders(final boolean useBorders);

    void setUseBorders(final boolean useBorders, final short bMax, final short bMin);

    List<E> readDataFromModBus(final ModbusMasterSerialModel modbusMasterSerialModel,
                               final int adr, BatchRead<Integer> batch,
                               final boolean enableBatch,
                               final List<E> list,
                               final ModbusLocator... modbusLocator) throws ModbusInitException, ModbusTransportException;

    List<E> readDataFromModBus(final ModbusMasterTcpModel modbusMasterTcpModel,
                               final int adr, BatchRead<Integer> batch,
                               final boolean enableBatch,
                               final List<E> list,
                               final ModbusLocator... modbusLocator) throws ModbusInitException, ModbusTransportException;

    void writeDataToModBus(final ModbusMasterSerialModel modbusMasterSerialModel,
                           final int adr,
                           final E values,
                           final ModbusLocator modbusLocator) throws ModbusInitException, ModbusTransportException;

    void writeDataToModBus(final ModbusMasterTcpModel modbusMasterTcpModel,
                           final int adr,
                           final E values,
                           final ModbusLocator modbusLocator) throws ModbusInitException, ModbusTransportException;

    void setValuesDefault(final List<E> list, final int length);

    E borderValue(final short bMin, final short bMax, final E val);
}
