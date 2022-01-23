package com.vm.modbus;

import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.BatchResults;
import com.serotonin.modbus4j.ModbusLocator;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.vm.modbus.entity.ModbusMasterSerialModel;
import com.vm.modbus.entity.ModbusMasterTcpModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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
    public void setUseBorders(final boolean useBorders) {
        this.setUseBorders(useBorders, borderMax, borderMin);
    }

    @Override
    public void setUseBorders(final boolean useBorders, final short bMax, final short bMin){
        this.useBorders = useBorders;
        this.borderMax = bMax;
        this.borderMin = bMin;
    }

    @Override
    public synchronized List<E> readDataFromModBus(ModbusMasterSerialModel modbusMasterSerialModel,
                                      final int adr,
                                      final BatchRead<Integer> batch,
                                      final boolean enableBatch,
                                      final ModbusLocator ... modbusLocator){
        ModbusMaster modbusMaster = modbusMasterSerialModel.getMaster();
        return readData(modbusMaster, adr, batch, enableBatch, modbusLocator);
    }

    @Override
    public synchronized List<E> readDataFromModBus(ModbusMasterTcpModel modbusMasterTcpModel,
                                                   final int adr,
                                                   final BatchRead<Integer> batch,
                                                   final boolean enableBatch,
                                                   final ModbusLocator ... modbusLocator){
        ModbusMaster modbusMaster = modbusMasterTcpModel.getMaster();
        return readData(modbusMaster, adr, batch, enableBatch, modbusLocator);
    }

    @SuppressWarnings("unchecked")
    private synchronized List<E> readData(ModbusMaster modbusMaster,
                                         final int adr,
                                         final BatchRead<Integer> batch,
                                         final boolean enableBatch,
                                         final ModbusLocator ... modbusLocator){
        List<E> list = new ArrayList<>();
        try {
            modbusMaster.init();
            boolean test = modbusMaster.testSlaveNode(adr);
            LOGGER.info("ModBus Listen slave address №"+ adr + "--"+test);
            System.out.println("ModBus Listen slave address №"+ adr + "--"+test);
        }
        catch (ModbusInitException e){
            String message = e.getMessage();
            LOGGER.error("ModBus Init problem, slave address №"+ adr+ "--"+message);
            System.out.println("ModBus Init problem, slave address №"+ adr+ "--"+message);
        }
        finally {
            try {
                if (enableBatch){
                    for (int i=0; i < modbusLocator.length; i++){
                        batch.addLocator(i,modbusLocator[i]);
                    }
                    BatchResults<Integer> batchResults = modbusMaster.send(batch);
                    for (int i=0; i < modbusLocator.length; i++){
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

            }catch (Exception e){
                String message = e.getMessage();
                LOGGER.error("ModBus Transport problem, slave address №"+ adr+ "--"+message);
                System.out.println("ModBus Transport problem, slave address №"+ adr+ "--"+message);
                setValuesDefault(list, modbusLocator.length);
            }
            finally {
                LOGGER.info("ModBus Close connection, slave address №"+ adr);
                System.out.println("ModBus Close connection, slave address №"+ adr);
                modbusMaster.destroy();
            }
            //-----------------------------------------------------------------------------
            String form = "___";
            for (int i=0; i < modbusLocator.length; i++){
                form = form + list.get(i) + "___";
            }
            System.out.println("Device №" + adr + "  "+ form);
            //-----------------------------------------------------------------------------
        }
        return list;
    }

    @Override
    public synchronized void writeDataToModBus(ModbusMasterSerialModel modbusMasterSerialModel,
                                  final int adr,
                                  final E values,
                                  final ModbusLocator modbusLocator){
        ModbusMaster modbusMaster = modbusMasterSerialModel.getMaster();
        writeData(modbusMaster, adr, values, modbusLocator);
    }

    @Override
    public synchronized void writeDataToModBus(ModbusMasterTcpModel modbusMasterTcpModel,
                                               final int adr,
                                               final E values,
                                               final ModbusLocator modbusLocator){
        ModbusMaster modbusMaster = modbusMasterTcpModel.getMaster();
        writeData(modbusMaster, adr, values, modbusLocator);
    }

    private synchronized void writeData(ModbusMaster modbusMaster, final int adr,
                                       final E values,
                                       final ModbusLocator modbusLocator){
        try {
            modbusMaster.init();
            boolean test = modbusMaster.testSlaveNode(adr);
            LOGGER.info("ModBus Listen slave address №"+ adr + "--"+test);
            System.out.println("ModBus Listen slave address №"+ adr + "--"+test);
        }
        catch (ModbusInitException e){
            String message = e.getMessage();
            LOGGER.error("ModBus Init problem, slave address №"+ adr+ "--"+message);
            System.out.println("ModBus Init problem, slave address №"+ adr+ "--"+message);
        }
        finally {
            try {
                modbusMaster.setValue(modbusLocator, values);
            }catch (Exception e){
                String message = e.getMessage();
                LOGGER.error("ModBus Transport problem, slave address №"+ adr+ "--"+message);
                System.out.println("ModBus Transport problem, slave address №"+ adr+ "--"+message);
            }
            finally {
                LOGGER.info("ModBus Close connection, slave address №"+ adr);
                System.out.println("ModBus Close connection, slave address №"+ adr);
                modbusMaster.destroy();
            }
        }
    }

    abstract void setValuesDefault(List<E> list, int length);

    abstract E borderValue(short bMin, short bMax, E val);
}
