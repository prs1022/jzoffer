package com.google;

/**
 * 相较于我们java开发常用的json 序列化，protobuf平时开发中用到的少之又少。
 * 就很像我们从spring学过来的使用properties，而 不太习惯 yaml的写法一样
 * <p>
 * protobuf本身和语言和平台无关， 是一种可扩展的用于序列化和结构化数据的方法，常用于用于通信协议，数据存储等。
 * <p>
 * 凡事具有两面性，protobuf比json体积小，速度快，但可读性差很多，是二进制的，而json文本我们人类都能看得懂
 * 所以需要自定义一个schema来定义数据结构的描述
 */

import com.jet.protobuf.PersonTestProtos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author rensong.pu
 * @date 2021/10/11 13:55 星期一
 **/
public class ProtoBufTest {
    public static void main(String[] args) {
        /** Step1：生成 personTest 对象 */
        // personTest 构造器
        PersonTestProtos.PersonTest.Builder personBuilder = PersonTestProtos.PersonTest.newBuilder();
        // personTest 赋值
        personBuilder.setName("Jet Chen");
        personBuilder.setEmail("ckk505214992@gmail.com");
        personBuilder.setSex(PersonTestProtos.PersonTest.Sex.MALE);

        // 内部的 PhoneNumber 构造器
        PersonTestProtos.PersonTest.PhoneNumber.Builder phoneNumberBuilder = PersonTestProtos.PersonTest.PhoneNumber.newBuilder();
        // PhoneNumber 赋值
        phoneNumberBuilder.setType(PersonTestProtos.PersonTest.PhoneNumber.PhoneType.MOBILE);
        phoneNumberBuilder.setNumber("17717037257");

        // personTest 设置 PhoneNumber
        personBuilder.addPhone(phoneNumberBuilder);

        // 生成 personTest 对象
        PersonTestProtos.PersonTest personTest = personBuilder.build();

        /** Step2：序列化和反序列化 */
        // 方式一 byte[]：
        // 序列化
//            byte[] bytes = personTest.toByteArray();
        // 反序列化
//            PersonTestProtos.PersonTest personTestResult = PersonTestProtos.PersonTest.parseFrom(bytes);
//            System.out.println(String.format("反序列化得到的信息，姓名：%s，性别：%d，手机号：%s", personTestResult.getName(), personTest.getSexValue(), personTest.getPhone(0).getNumber()));


        // 方式二 ByteString：
        // 序列化
//            ByteString byteString = personTest.toByteString();
//            System.out.println(byteString.toString());
        // 反序列化
//            PersonTestProtos.PersonTest personTestResult = PersonTestProtos.PersonTest.parseFrom(byteString);
//            System.out.println(String.format("反序列化得到的信息，姓名：%s，性别：%d，手机号：%s", personTestResult.getName(), personTest.getSexValue(), personTest.getPhone(0).getNumber()));


        // 方式三 InputStream
        // 粘包,将一个或者多个protobuf 对象字节写入 stream
        // 序列化
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            personTest.writeDelimitedTo(byteArrayOutputStream);
            // 反序列化，从 steam 中读取一个或者多个 protobuf 字节对象
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            PersonTestProtos.PersonTest personTestResult = PersonTestProtos.PersonTest.parseDelimitedFrom(byteArrayInputStream);
            System.out.println(String.format("反序列化得到的信息，姓名：%s，性别：%d，手机号：%s", personTestResult.getName(), personTest.getSexValue(), personTest.getPhone(0).getNumber()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
