//package com.easyexcel;
//
//import com.alibaba.excel.ExcelWriter;
//import com.alibaba.excel.metadata.Sheet;
//import com.alibaba.excel.support.ExcelTypeEnum;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//
///**
// * @author purensong
// * @date 2019/8/7 14:54
// */
//@Slf4j
//public class EasyExcelDemo {
//    public static void writeExcel(File file) throws FileNotFoundException {
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        ExcelWriter excelWriter = new ExcelWriter(fileOutputStream, ExcelTypeEnum.XLSX);
//        List<List<String>> data = new ArrayList<>();
//        data.add(Arrays.asList(new String[]{"aa", "bb", "cc", "dd"}));
//        data.add(Arrays.asList(new String[]{"aa1", "bb1", "cc1", "dd1"}));
//        Sheet sheet = new Sheet(1);
//        sheet.setSheetName("测试easyExcel导出");
////        ExcelWriter writer = excelWriter.write(data, sheet);
//        ExcelWriter writer = excelWriter.write0(data, sheet);
//        writer.finish();
//    }
//
//    public static void main(String[] args) throws IOException {
//        File file = new File("E://easyExcelTest.xlsx");
//        if(!file.exists()){
//            file.createNewFile();
//        }
//        writeExcel(file);
//    }
//
//}
