package com.oshi;

/**
 * @author rensong.pu
 * @date 2021/7/30 13:47 星期五
 **/
import lombok.Data;
import java.io.Serializable;

@Data
public class Sys implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务器名称
     */
    private String computerName;

    /**
     * 服务器Ip
     */
    private String computerIp;

    /**
     * 项目路径
     */
    private String userDir;

    /**
     * 操作系统
     */
    private String osName;

    /**
     * 系统架构
     */
    private String osArch;
}