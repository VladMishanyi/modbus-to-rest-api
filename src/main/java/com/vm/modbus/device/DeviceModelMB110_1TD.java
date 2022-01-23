package com.vm.modbus.device;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.serotonin.modbus4j.ModbusLocator;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.code.RegisterRange;
import com.vm.modbus.lib.HysComparator;
import org.springframework.stereotype.Component;

/**
 * Created by KIP-PC99 on 20.09.2018.
 */
@Component
public class DeviceModelMB110_1TD implements DeviceModel {

    @JsonIgnore
    private transient final int deviceAddress = 16;//16-24 reserved for this device
    @JsonIgnore
    private transient final float hysteresisFloat = 1.0F;
    @JsonIgnore
    private transient final int hysteresisInt = 1;

    private float holdingRegister0 = 0F;
    @JsonIgnore
    private transient float oldHoldingRegister0 = 0F;
    /**
     * This modbusLocator0h uses for read real value of stretching.
     * Marked as Rd.fF
     * Registers 0x46-0x47
     * Read only.
     * Range values FLOAT.
     */
    @JsonIgnore
    private transient final ModbusLocator modbusLocator0h = new ModbusLocator(deviceAddress, RegisterRange.HOLDING_REGISTER, 70, DataType.FOUR_BYTE_FLOAT);

    private int holdingRegister1 = 0;
    @JsonIgnore
    private transient int oldHoldingRegister1 = 0;
    /**
     * This modbusLocator1h uses for write a current not correct value on sensor as zero point. Pay attention it will apply just after INIT command.
     * Marked as U.Wgh
     * Register 0x31
     * Write only.
     * Range values 0 only.
     */
    @JsonIgnore
    private transient final ModbusLocator modbusLocator1h = new ModbusLocator(deviceAddress, RegisterRange.HOLDING_REGISTER, 49, DataType.TWO_BYTE_INT_UNSIGNED);

    private int holdingRegister2 = 0;
    @JsonIgnore
    private transient int oldHoldingRegister2 = 0;
    /**
     * This modbusLocator2h uses for commit all changes. It is INIT command.
     * Marked as Init
     * Register 0x39
     * Write Only.
     * Range values 0 only.
     */
    @JsonIgnore
    private transient final ModbusLocator modbusLocator2h = new ModbusLocator(deviceAddress, RegisterRange.HOLDING_REGISTER, 57, DataType.TWO_BYTE_INT_UNSIGNED);

    private int holdingRegister3 = 0;
    @JsonIgnore
    private transient int oldHoldingRegister3 = 0;
    /**
     * This modbusLocator3h uses to apply enable or disable weight of item as zero point measure.
     * Marked as Cnt.P
     * Register 0xD
     * Write and Read.
     * Range values 0 - 1 only
     */
    @JsonIgnore
    private transient final ModbusLocator modbusLocator3h = new ModbusLocator(deviceAddress, RegisterRange.HOLDING_REGISTER, 13, DataType.TWO_BYTE_INT_UNSIGNED);

    private int holdingRegister4 = 0;
    @JsonIgnore
    private transient int oldHoldingRegister4 = 0;
    /**
     * This modbusLocator4h uses to set sensitivity sensor.
     * Marked as Sens
     * Register 0x11
     * Write and Read.
     * Range values 0 - 6 only
     */
    @JsonIgnore
    private transient final ModbusLocator modbusLocator4h = new ModbusLocator(deviceAddress, RegisterRange.HOLDING_REGISTER, 17, DataType.TWO_BYTE_INT_UNSIGNED);

    private float holdingRegister5 = 0F;
    @JsonIgnore
    private transient float oldHoldingRegister5 = 0F;
    /**
     * This modbusLocator5h uses to set min value for sensor.
     * Marked as v.Min
     * Register 0x15-0x16
     * Write and Read.
     * Range values FLOAT.
     */
    @JsonIgnore
    private transient final ModbusLocator modbusLocator5h = new ModbusLocator(deviceAddress, RegisterRange.HOLDING_REGISTER, 21, DataType.FOUR_BYTE_FLOAT);

    private float holdingRegister6 = 0F;
    @JsonIgnore
    private transient float oldHoldingRegister6 = 0F;
    /**
     * This modbusLocator6h uses to set max value for sensor.
     * Marked as v.Max
     * Register 0x1D-0x1E
     * Write and Read.
     * Range values FLOAT.
     */
    @JsonIgnore
    private transient final ModbusLocator modbusLocator6h = new ModbusLocator(deviceAddress, RegisterRange.HOLDING_REGISTER, 29, DataType.FOUR_BYTE_FLOAT);

    private float holdingRegister7 = 0F;
    @JsonIgnore
    private transient float oldHoldingRegister7 = 0F;
    /**
     * This modbusLocator7h uses to set weight of item.
     * Marked as P.Wgh
     * Register 0x25-0x26
     * Write and Read.
     * Range values FLOAT.
     */
    @JsonIgnore
    private transient final ModbusLocator modbusLocator7h = new ModbusLocator(deviceAddress, RegisterRange.HOLDING_REGISTER, 37, DataType.FOUR_BYTE_FLOAT);


    public DeviceModelMB110_1TD(){}

    public int getDeviceAddress() {
        return deviceAddress;
    }

    public float getHysteresisFloat() {
        return hysteresisFloat;
    }

    public int getHysteresisInt() {
        return hysteresisInt;
    }

    public float getHoldingRegister0() {
        return holdingRegister0;
    }

    public void setHoldingRegister0(float holdingRegister0) {
        this.holdingRegister0 = holdingRegister0;
    }

    public float getOldHoldingRegister0() {
        return oldHoldingRegister0;
    }

    public void setOldHoldingRegister0(float oldHoldingRegister0) {
        this.oldHoldingRegister0 = oldHoldingRegister0;
    }

    public ModbusLocator getModbusLocator0h() {
        return modbusLocator0h;
    }

    public int getHoldingRegister1() {
        return holdingRegister1;
    }

    public void setHoldingRegister1(int holdingRegister1) {
        this.holdingRegister1 = holdingRegister1;
    }

    public int getOldHoldingRegister1() {
        return oldHoldingRegister1;
    }

    public void setOldHoldingRegister1(int oldHoldingRegister1) {
        this.oldHoldingRegister1 = oldHoldingRegister1;
    }

    public ModbusLocator getModbusLocator1h() {
        return modbusLocator1h;
    }

    public int getHoldingRegister2() {
        return holdingRegister2;
    }

    public void setHoldingRegister2(int holdingRegister2) {
        this.holdingRegister2 = holdingRegister2;
    }

    public int getOldHoldingRegister2() {
        return oldHoldingRegister2;
    }

    public void setOldHoldingRegister2(int oldHoldingRegister2) {
        this.oldHoldingRegister2 = oldHoldingRegister2;
    }

    public ModbusLocator getModbusLocator2h() {
        return modbusLocator2h;
    }

    public int getHoldingRegister3() {
        return holdingRegister3;
    }

    public void setHoldingRegister3(int holdingRegister3) {
        this.holdingRegister3 = holdingRegister3;
    }

    public int getOldHoldingRegister3() {
        return oldHoldingRegister3;
    }

    public void setOldHoldingRegister3(int oldHoldingRegister3) {
        this.oldHoldingRegister3 = oldHoldingRegister3;
    }

    public ModbusLocator getModbusLocator3h() {
        return modbusLocator3h;
    }

    public int getHoldingRegister4() {
        return holdingRegister4;
    }

    public void setHoldingRegister4(int holdingRegister4) {
        this.holdingRegister4 = holdingRegister4;
    }

    public int getOldHoldingRegister4() {
        return oldHoldingRegister4;
    }

    public void setOldHoldingRegister4(int oldHoldingRegister4) {
        this.oldHoldingRegister4 = oldHoldingRegister4;
    }

    public ModbusLocator getModbusLocator4h() {
        return modbusLocator4h;
    }

    public float getHoldingRegister5() {
        return holdingRegister5;
    }

    public void setHoldingRegister5(float holdingRegister5) {
        this.holdingRegister5 = holdingRegister5;
    }

    public float getOldHoldingRegister5() {
        return oldHoldingRegister5;
    }

    public void setOldHoldingRegister5(float oldHoldingRegister5) {
        this.oldHoldingRegister5 = oldHoldingRegister5;
    }

    public ModbusLocator getModbusLocator5h() {
        return modbusLocator5h;
    }

    public float getHoldingRegister6() {
        return holdingRegister6;
    }

    public void setHoldingRegister6(float holdingRegister6) {
        this.holdingRegister6 = holdingRegister6;
    }

    public float getOldHoldingRegister6() {
        return oldHoldingRegister6;
    }

    public void setOldHoldingRegister6(float oldHoldingRegister6) {
        this.oldHoldingRegister6 = oldHoldingRegister6;
    }

    public ModbusLocator getModbusLocator6h() {
        return modbusLocator6h;
    }

    public float getHoldingRegister7() {
        return holdingRegister7;
    }

    public void setHoldingRegister7(float holdingRegister7) {
        this.holdingRegister7 = holdingRegister7;
    }

    public float getOldHoldingRegister7() {
        return oldHoldingRegister7;
    }

    public void setOldHoldingRegister7(float oldHoldingRegister7) {
        this.oldHoldingRegister7 = oldHoldingRegister7;
    }

    public ModbusLocator getModbusLocator7h() {
        return modbusLocator7h;
    }

    @Override
    public boolean hysteresis(){
        boolean inner = HysComparator.compare(oldHoldingRegister0,
                holdingRegister0,
                hysteresisFloat);
        if (inner){
            oldHoldingRegister0 = holdingRegister0;
        }
        return inner;
    }
}
