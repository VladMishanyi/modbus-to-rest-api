package com.vm.modbus;

import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.BatchResults;
import com.serotonin.modbus4j.ModbusLocator;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.vm.modbus.entity.ModbusMasterSerialModel;
import com.vm.modbus.entity.ModbusMasterTcpModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by KIP-PC99 on 15.06.2018.
 */

public abstract class RootModbusImpl<E extends Number> implements RootModbus<E> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private boolean useBorders = false;

    private short borderMax = 10000;

    private short borderMin = -10000;

    public RootModbusImpl(){}

    @Override
    public synchronized void setUseBorders(final boolean useBorders) {
        this.setUseBorders(useBorders, borderMax, borderMin);
    }

    @Override
    public synchronized void setUseBorders(final boolean useBorders, final short bMax, final short bMin) {
        this.useBorders = useBorders;
        this.borderMax = bMax;
        this.borderMin = bMin;
    }

    @Override
    public synchronized List<E> readDataFromModBus(final ModbusMasterSerialModel modbusMasterSerialModel,
                                                   final int adr,
                                                   final BatchRead<Integer> batch,
                                                   final boolean enableBatch,
                                                   final List<E> list,
                                                   final ModbusLocator ... modbusLocator) throws ModbusInitException, ModbusTransportException {
        return readData(modbusMasterSerialModel.getMaster(), adr, batch, enableBatch, list, modbusLocator);
    }

    @Override
    public synchronized List<E> readDataFromModBus(final ModbusMasterTcpModel modbusMasterTcpModel,
                                                   final int adr,
                                                   final BatchRead<Integer> batch,
                                                   final boolean enableBatch,
                                                   final List<E> list,
                                                   final ModbusLocator ... modbusLocator) throws ModbusInitException, ModbusTransportException {
        return readData(modbusMasterTcpModel.getMaster(), adr, batch, enableBatch, list, modbusLocator);
    }

    @SuppressWarnings("unchecked")
    private List<E> readData(final ModbusMaster modbusMaster,
                             final int adr,
                             final BatchRead<Integer> batch,
                             final boolean enableBatch,
                             final List<E> list,
                             final ModbusLocator ... modbusLocator) throws ModbusInitException, ModbusTransportException {
        initMaster(modbusMaster, adr);
        try {
            if (enableBatch){
                for (int i=0; i < modbusLocator.length; i++) {
                    batch.addLocator(i,modbusLocator[i]);
                }
                BatchResults<Integer> batchResults = modbusMaster.send(batch);
                for (int i=0; i < modbusLocator.length; i++) {
                    E val = (E) batchResults.getValue(i);

                    if (useBorders){
                        list.add(i, borderValue(borderMin, borderMax, val));
                    }else {
                        list.add(i, val);
                    }
                }
            }else {
                for (int i=0; i < modbusLocator.length; i++){
                    E val = (E) modbusMaster.getValue(modbusLocator[i]);

                    if (useBorders){
                        list.add(i, borderValue(borderMin, borderMax, val));
                    }else {
                        list.add(i, val);
                    }
                }
            }
        }catch (ModbusTransportException | ErrorResponseException | RuntimeException e) {
            modbusMaster.destroy();
            throw new ModbusTransportException(e);
        }
        LOGGER.info("ModBus, slave address №" + adr + " - " + list);
        return list;
    }

    @Override
    public synchronized void writeDataToModBus(final ModbusMasterSerialModel modbusMasterSerialModel,
                                               final int adr,
                                               final E values,
                                               final ModbusLocator modbusLocator) throws ModbusInitException, ModbusTransportException {
        writeData(modbusMasterSerialModel.getMaster(), adr, values, modbusLocator);
    }

    @Override
    public synchronized void writeDataToModBus(final ModbusMasterTcpModel modbusMasterTcpModel,
                                               final int adr,
                                               final E values,
                                               final ModbusLocator modbusLocator) throws ModbusInitException, ModbusTransportException {
        writeData(modbusMasterTcpModel.getMaster(), adr, values, modbusLocator);
    }

    private void writeData(final ModbusMaster modbusMaster,
                                        final int adr,
                                        final E values,
                                        final ModbusLocator modbusLocator) throws ModbusInitException, ModbusTransportException {

        initMaster(modbusMaster, adr);
        try {
            modbusMaster.setValue(modbusLocator, values);
        }catch (ModbusTransportException | ErrorResponseException | RuntimeException e) {
            modbusMaster.destroy();
            throw new ModbusTransportException(e);
        }
    }

    private void initMaster(final ModbusMaster modbusMaster, final int adr) throws ModbusInitException {
        try {
            modbusMaster.init();
            LOGGER.info("ModBus Listen, slave address №" + adr);
        }
        catch (ModbusInitException e){
            modbusMaster.destroy();
            throw new ModbusInitException(e);
        }
    }
}
