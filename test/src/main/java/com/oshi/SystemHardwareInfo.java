//package com.oshi;
//
//import ch.qos.logback.core.util.SystemInfo;
//import cn.hutool.core.net.NetUtil;
//import cn.hutool.core.util.NumberUtil;
//import oshi.SystemInfo;
//import oshi.hardware.CentralProcessor;
//import oshi.hardware.CentralProcessor.TickType;
//import oshi.hardware.GlobalMemory;
//import oshi.hardware.HardwareAbstractionLayer;
//import oshi.software.os.FileSystem;
//import oshi.software.os.OSFileStore;
//import oshi.software.os.OperatingSystem;
//import oshi.util.Util;
//
//import java.io.Serializable;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Properties;
//
///**
// * @author rensong.pu
// * @date 2021/7/30 13:46 星期五
// **/
//public class SystemHardwareInfo implements Serializable {
//    private static final long serialVersionUID = 1L;
//
//    private static final int OSHI_WAIT_SECOND = 1000;
//
//    /**
//     * CPU相关信息
//     */
//    private Cpu cpu = new Cpu();
//
//    /**
//     * 內存相关信息
//     */
//    private Mem mem = new Mem();
//
//    /**
//     * JVM相关信息
//     */
//    private Jvm jvm = new Jvm();
//
//    /**
//     * 服务器相关信息
//     */
//    private Sys sys = new Sys();
//
//    /**
//     * 磁盘相关信息
//     */
//    private List<SysFile> sysFiles = new LinkedList<SysFile>();
//
//    public static void main(String[] args) throws Exception {
//        final SystemHardwareInfo systemHardwareInfo = new SystemHardwareInfo();
//        systemHardwareInfo.copyTo();
//        System.out.println(systemHardwareInfo.cpu);
//        System.out.println(systemHardwareInfo.mem);
//        System.out.println(systemHardwareInfo.jvm);
//        System.out.println(systemHardwareInfo.sys);
//        for (SysFile sysFile : systemHardwareInfo.sysFiles) {
//            System.out.println(sysFile);
//        }
//
//    }
//
//    public void copyTo() throws Exception {
//        SystemInfo si = new SystemInfo();
//        HardwareAbstractionLayer hal = si.getHardware();
//
//        setCpuInfo(hal.getProcessor());
//
//        setMemInfo(hal.getMemory());
//
//        setSysInfo();
//
//        setJvmInfo();
//
//        setSysFiles(si.getOperatingSystem());
//    }
//
//    /**
//     * 设置CPU信息
//     */
//    private void setCpuInfo(CentralProcessor processor) {
//        // CPU信息
//        long[] prevTicks = processor.getSystemCpuLoadTicks();
//        Util.sleep(OSHI_WAIT_SECOND);
//        long[] ticks = processor.getSystemCpuLoadTicks();
//        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
//        long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
//        long softirq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
//        long steal = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
//        long cSys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
//        long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
//        long iowait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
//        long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
//        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
//        cpu.setCpuNum(processor.getLogicalProcessorCount());
//        cpu.setCpuCoreNum(processor.getPhysicalProcessorCount());
//        cpu.setTotal(totalCpu);
//        cpu.setSys(cSys);
//        cpu.setUsed(user);
//        cpu.setWait(iowait);
//        cpu.setFree(idle);
//    }
//
//    /**
//     * 设置内存信息
//     */
//    private void setMemInfo(GlobalMemory memory) {
//        mem.setTotal(memory.getTotal());
//        mem.setUsed(memory.getTotal() - memory.getAvailable());
//        mem.setFree(memory.getAvailable());
//    }
//
//    /**
//     * 设置服务器信息
//     */
//    private void setSysInfo() {
//        Properties props = System.getProperties();
//        sys.setComputerName(NetUtil.getLocalHostName());
//        sys.setComputerIp(NetUtil.getLocalhostStr());
//        sys.setOsName(props.getProperty("os.name"));
//        sys.setOsArch(props.getProperty("os.arch"));
//        sys.setUserDir(props.getProperty("user.dir"));
//    }
//
//    /**
//     * 设置Java虚拟机
//     */
//    private void setJvmInfo() {
//        Properties props = System.getProperties();
//        jvm.setTotal(Runtime.getRuntime().totalMemory());
//        jvm.setMax(Runtime.getRuntime().maxMemory());
//        jvm.setFree(Runtime.getRuntime().freeMemory());
//        jvm.setVersion(props.getProperty("java.version"));
//        jvm.setHome(props.getProperty("java.home"));
//    }
//
//    /**
//     * 设置磁盘信息
//     */
//    private void setSysFiles(OperatingSystem os) {
//        FileSystem fileSystem = os.getFileSystem();
//        final List<OSFileStore> fileStores = fileSystem.getFileStores();
//        for (OSFileStore fs : fileStores) {
//            long free = fs.getUsableSpace();
//            long total = fs.getTotalSpace();
//            long used = total - free;
//            SysFile sysFile = new SysFile();
//            sysFile.setDirName(fs.getMount());
//            sysFile.setSysTypeName(fs.getType());
//            sysFile.setTypeName(fs.getName());
//            sysFile.setTotal(convertFileSize(total));
//            sysFile.setFree(convertFileSize(free));
//            sysFile.setUsed(convertFileSize(used));
//            sysFile.setUsage(NumberUtil.round(NumberUtil.mul(used, total, 4), 100).doubleValue());
//            sysFiles.add(sysFile);
//        }
//    }
//
//    /**
//     * 字节转换
//     *
//     * @param size 字节大小
//     * @return 转换后值
//     */
//    public String convertFileSize(long size) {
//        long kb = 1024;
//        long mb = kb * 1024;
//        long gb = mb * 1024;
//        if (size >= gb) {
//            return String.format("%.1f GB", (float) size / gb);
//        } else if (size >= mb) {
//            float f = (float) size / mb;
//            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
//        } else if (size >= kb) {
//            float f = (float) size / kb;
//            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
//        } else {
//            return String.format("%d B", size);
//        }
//    }
//}
