package com.oshi;

/**
 * @author rensong.pu
 * @date 2021/7/30 13:48 星期五
 **/

import cn.hutool.core.util.NumberUtil;
import lombok.Data;

import java.io.Serializable;

@Data
public class Cpu implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 虚拟核心数
     */
    private int cpuNum;

    /**
     * 物理核数
     */
    private int cpuCoreNum;

    /**
     * CPU总的使用率
     */
    private double total;

    /**
     * CPU系统使用率
     */
    private double sys;

    /**
     * CPU用户使用率
     */
    private double used;

    /**
     * CPU当前等待率
     */
    private double wait;

    /**
     * CPU当前空闲率
     */
    private double free;


    public double getTotal() {
        return NumberUtil.round(NumberUtil.mul(total, 100), 2).doubleValue();
    }

    public double getSys() {
        return NumberUtil.round(NumberUtil.mul(sys / total, 100), 2).doubleValue();
    }

    public double getUsed() {
        return NumberUtil.round(NumberUtil.mul(used / total, 100), 2).doubleValue();
    }

    public double getWait() {
        return NumberUtil.round(NumberUtil.mul(wait / total, 100), 2).doubleValue();
    }

    public double getFree() {
        return NumberUtil.round(NumberUtil.mul(free / total, 100), 2).doubleValue();
    }
}