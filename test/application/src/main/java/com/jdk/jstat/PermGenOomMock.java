package com.jdk.jstat;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * 永久代溢出测试
 * @author rensong.pu
 * @date 2019/11/21 10:08 星期四
 **/
public class PermGenOomMock {
    public static void main(String[] args) {
        URL url = null;
        ArrayList<ClassLoader> classLoaders = new ArrayList<>();
        try {
            url = new File("/tmp").toURI().toURL();
            URL[] urls = {url};
            while(true){
                URLClassLoader urlClassLoader = new URLClassLoader(urls);
                classLoaders.add(urlClassLoader);
                urlClassLoader.loadClass("com.jdk.jstat.PermGenOomMock");
            }
        } catch (MalformedURLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
