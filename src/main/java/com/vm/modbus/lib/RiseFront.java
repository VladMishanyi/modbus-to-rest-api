package com.vm.modbus.lib;

public class RiseFront {

    private boolean inputFront = false;
    private boolean outputResult = false;
    private boolean firstCheck = false;

    public RiseFront() {
    }

    public RiseFront(boolean inputFront) {
        this.inputFront = inputFront;
    }

    public void setInputFront(boolean inputFront) {
        this.inputFront = inputFront;
        if (!this.inputFront){
            outputResult = false;
            firstCheck = false;
        }
        if (this.inputFront && !firstCheck){
            outputResult = true;
            firstCheck = true;
        }
    }

    public boolean isOutputResult() {
        if (outputResult){
            outputResult = false;
            return true;
        }
        return outputResult;
    }

    @Override
    public String toString() {
        return "RiseFront{" +
                "inputFront=" + inputFront +
                ", outputResult=" + outputResult +
                ", firstCheck=" + firstCheck +
                '}';
    }
}
