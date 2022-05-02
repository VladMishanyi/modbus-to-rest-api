package com.vm.modbus.entity;

/**
 * Created by KIP-PC99 on 26.11.2018.
 */
public class ModbusBodyQuery {
    private int address;
    private int register;
    private String value;

    public ModbusBodyQuery(){}

    public ModbusBodyQuery(final int address, final int register, final String value) {
        this.address = address;
        this.register = register;
        this.value = value;
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
}
