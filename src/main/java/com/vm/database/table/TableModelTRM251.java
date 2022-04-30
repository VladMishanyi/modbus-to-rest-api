//package com.vm.database.table;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import java.util.Objects;
//
//@Entity
//@Table(name = "trm251")
//public class TableModelTRM251 extends TableModel {
//
//    private static final long serialVersionUID = 1L;
//
//    @Column(name = "inputRegister0")
//    private float inputRegister0 = 0F;
//
//    @Column(name = "inputRegister1")
//    private float inputRegister1 = 0F;
//
//    public TableModelTRM251(){}
//
//    public float getInputRegister0() {
//        return inputRegister0;
//    }
//
//    public void setInputRegister0(float inputRegister0) {
//        this.inputRegister0 = inputRegister0;
//    }
//
//    public float getInputRegister1() {
//        return inputRegister1;
//    }
//
//    public void setInputRegister1(float inputRegister1) {
//        this.inputRegister1 = inputRegister1;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        boolean result = super.equals(object);
//        if (result){
//            TableModelTRM251 that = (TableModelTRM251) object;
//            result = Float.compare(that.inputRegister0, inputRegister0) == 0 &&
//                    Float.compare(that.inputRegister1, inputRegister1) == 0;
//        }
//        return result;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(this.getId(), this.getDate(), inputRegister0, inputRegister1);
//    }
//
//    @Override
//    public String toString() {
//        return "TableModelTRM138{" +
//                super.toString()+
//                ", inputRegister0=" + inputRegister0 +
//                ", inputRegister1=" + inputRegister1 +
//                '}';
//    }
//
//    @Override
//    public TableModelTRM251 clone() {
//        return (TableModelTRM251) super.clone();
//    }
//}
