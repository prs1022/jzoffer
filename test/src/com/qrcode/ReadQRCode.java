package com.qrcode;

import sun.java2d.pipe.SpanShapeRenderer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author rensong.pu
 * @date 2020/3/2 10:00 星期一
 **/
public class ReadQRCode {
    public static void main(String[] args){

        //        MultiFormatReader multiFormatReader=new MultiFormatReader();
//        HashMap hints=new HashMap();
//        hints.put(EncodeHintType.CHARACTER_SET,"GBK");
//        try{
//            BufferedImage source= ImageIO.read(new File("D:/qr2.jpg"));
//            BinaryBitmap binaryImg=new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(source)));
//            Result result=multiFormatReader.decode(binaryImg,hints);
//            System.out.println(result.getText());
//        }catch (Exception e){
//
//        }
        System.out.println(new SimpleDateFormat("yyyy/m/d").format(new Date()));

    }
}