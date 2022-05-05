package com.vm.modbus.entity;

/**
 * Created by KIP-PC99 on 26.11.2018.
 */
public class ModbusBodyQuery {
    private int address;
    private int register;
    private String value;
    private boolean write;

    public ModbusBodyQuery(){}

    public ModbusBodyQuery(final int address, final int register, final boolean write) {
        this(address, register, "0", write);
    }

    public ModbusBodyQuery(final int address, final int register, final String value, final boolean write) {
        this.address = address;
        this.register = register;
        this.value = value;
        this.write = write;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getRegister() {
        return register;
    }

    public void setRegister(int register) {
        this.register = register;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isWrite() {
        return write;
    }

    public void setWrite(boolean write) {
        this.write = write;
    }
}
