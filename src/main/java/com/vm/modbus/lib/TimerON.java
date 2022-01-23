package com.vm.modbus.lib;

public class TimerON extends Thread{
    private final long SEC = 1000;

    private boolean enable = false;
    private long time = 0;
    private boolean endTime = false;
    private long currentTime = 0;

    public TimerON(){
        this.start();
    }

    public TimerON(final boolean enable, final long time){
        this.enable = enable;
        this.time = time;
        this.start();
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isEndTime() {
        return endTime;
    }

    public void setEndTime(boolean endTime) {
        this.endTime = endTime;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public void run() {
        try {
            while (!this.isInterrupted()){
                Thread.sleep(SEC);
                if (enable && (time > 0)){
                    if (currentTime < time)  ++currentTime;
                    if (currentTime >= time)  endTime = true;
                }else {
                    currentTime = 0;
                    endTime = false;
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "TimerON{" +
                "enable=" + enable +
                ", time=" + time +
                ", endTime=" + endTime +
                ", currentTime=" + currentTime +
                '}';
    }
}
