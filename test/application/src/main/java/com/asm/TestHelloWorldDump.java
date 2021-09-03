package com.asm;

import jdk.internal.org.objectweb.asm.*;

import java.lang.reflect.Method;

public class TestHelloWorldDump implements Opcodes {

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(52, ACC_PUBLIC + ACC_SUPER, "com/asm/HelloWorld", null, "java/lang/Object", null);

        cw.visitSource("TestHelloWorld.java", null);

        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(7, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(RETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "Lcom/asm/TestHelloWorld;", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(9, l0);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Hello World!!");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(10, l1);
            mv.visitInsn(RETURN);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLocalVariable("args", "[Ljava/lang/String;", null, l0, l2, 0);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }

    public static void main(String[] args) throws Exception {
        // 生成二进制字节码
        byte[] bytes = dump();
        // 使用自定义的ClassLoader
        MyClassLoader cl = new MyClassLoader();
        // 加载我们生成的 HelloWorld 类
        Class<?> clazz = cl.defineClass("com.asm.HelloWorld", bytes);
        // 反射获取 main 方法
        Method main = clazz.getMethod("main", String[].class);
        // 调用 main 方法
        main.invoke(null, new Object[]{new String[]{}});
    }


}
/**
 * 自定义ClassLoader以支持加载字节数组形式的字节码
 * @author dadiyang
 */
class MyClassLoader extends ClassLoader {
    public Class<?> defineClass(String name, byte[] b) {
        // ClassLoader是个抽象类，而ClassLoader.defineClass 方法是protected的
        // 所以我们需要定义一个子类将这个方法暴露出来
        return super.defineClass(name, b, 0, b.length);
    }
}