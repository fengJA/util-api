package com.fj.redis.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.reflect.AccessibleObject;
import java.math.BigInteger;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

@Data
public class CanDataDto {
    @ApiModelProperty(value = "农机类型ID")
    private String id;
    @ApiModelProperty(value = "农机名称")
    private String machineName;
    @ApiModelProperty(value = "农机车牌")
    private String plateNumber;
    public static void main(String[] args) {
        new Thread(() -> {
            ReentrantLock lock = new ReentrantLock();
            lock.lock();
            System.out.println("==========");
            lock.unlock();
        },"A").start();
        CanDataDto build = new DataBuilder("789").machineName("678").build();
    }

    public CanDataDto(String id) {
        this.id = id;
    }
    public CanDataDto(String id, String machineName) {
        this(id);
    }

    public CanDataDto(DataBuilder dto) {
        this.id = dto.id;
        this.machineName = dto.machineName;
        this.plateNumber = dto.plateNumber;
    }
//    public static CanDataDto te(String id) {
//
//        return new ExtendsCan();
//    }
    public static class DataBuilder{
        private String id;
        private String machineName;
        private String plateNumber;

        public DataBuilder(String id) {
            this.id = id;
        }
        public DataBuilder machineName(String val){
            machineName = val;
            return this;
        }
        public DataBuilder plateNumber(String val){
            plateNumber = val;
            return this;
        }
        public CanDataDto build(){
            return new CanDataDto(this);
        }
    }

    public enum anEnum{HAM, DAR};

    private void re(){
        EnumSet<anEnum> set = EnumSet.noneOf(anEnum.class);
        AccessibleObject.setAccessible(new AccessibleObject[5], false);
    }


}
