package com.vm.database.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "modbus")
public class TableModelUniversal extends TableModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "address")
    private int address;

    @Column(name = "register")
    private int register;

    @Column(name = "write")
    private boolean write;

    @Column(name = "archive")
    private boolean archive;

    @Column(name = "hysteresis")
    private String hysteresis;

    @Column(name = "value")
    private String value;

    @Column(name = "registerRange")
    private int registerRange;

    @Column(name = "offset")
    private int offset;

    @Column(name = "dataType")
    private int dataType;

    public TableModelUniversal(){}

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

    public boolean isWrite() {
        return write;
    }

    public void setWrite(boolean write) {
        this.write = write;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public String getHysteresis() {
        return hysteresis;
    }

    public void setHysteresis(String hysteresis) {
        this.hysteresis = hysteresis;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getRegisterRange() {
        return registerRange;
    }

    public void setRegisterRange(int registerRange) {
        this.registerRange = registerRange;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = super.equals(object);
        if (result){
            TableModelUniversal that = (TableModelUniversal) object;
            result = that.address == address &&
                    that.register == register &&
                    that.write == write &&
                    that.archive == archive &&
                    that.hysteresis.equals(hysteresis) &&
                    that.value.equals(value) &&
                    that.registerRange == registerRange &&
                    that.offset == offset &&
                    that.dataType == dataType;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address, register, write, archive, hysteresis, value, registerRange, offset, dataType);
    }

    @Override
    public String toString() {
        return "TableModelUniversal{" +
                super.toString() +
                "address=" + address +
                ", register=" + register +
                ", write=" + write +
                ", archive=" + archive +
                ", hysteresis='" + hysteresis + '\'' +
                ", value='" + value + '\'' +
                ", registerRange=" + registerRange +
                ", offset=" + offset +
                ", dataType=" + dataType +
                '}';
    }

    @Override
    public TableModelUniversal clone() {
        return (TableModelUniversal) super.clone();
    }
}
