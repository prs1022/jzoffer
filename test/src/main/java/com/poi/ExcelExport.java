//package com.poi;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.math.NumberUtils;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.xssf.streaming.SXSSFCell;
//import org.apache.poi.xssf.streaming.SXSSFRow;
//import org.apache.poi.xssf.streaming.SXSSFSheet;
//import org.apache.poi.xssf.streaming.SXSSFWorkbook;
//
//import java.io.*;
//import java.util.*;
//
//
///**
// * @author purensong
// * @date 2019/8/6 19:06
// */
//@Slf4j
//public class ExcelExport {
//
//    public static void main(String[] args) throws IOException {
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        int N = 5000;
//        for (int i = 0; i < N; i++) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("region_name", "ABCD");
//            map.put("point_amt", i + 1);
//            map.put("cost", 1234);
//            map.put("insert_time", "2019-09-09");
//            map.put("seller_id", "xxxx");
//            map.put("dim_shop_id", "yyyy");
//            map.put("region_id", "yyyy");
//            map.put("city_code", "上海");
//            list.add(map);
//        }
//        //
//        String[] parentCellTitle = new String[]{"", "B", "B", "A", "A", "A", "", ""};
//        String[] celltitle = new String[]{"region_name=区域名称", "point_amt=积分", "cost=成本", "insert_time=数据处理时间"
//                , "seller_id=业态", "dim_shop_id=门店", "region_id=大区", "city_code=城市"};
//        exportExcel(list, celltitle, parentCellTitle, "测试数据导出20190806");
////        exportExcelPicture("C:\\Users\\PC\\Pictures\\clojure.png", 0);
//    }
//
//    /**
//     * 导出图片excel
//     */
//    public static void exportExcelPicture(String pictureUrl, int rowIndex) {
//        try {
//            Workbook wb = new HSSFWorkbook();
//            Sheet sheet = wb.createSheet("图片");
//            Drawing patriarch = sheet.createDrawingPatriarch();
//            if (pictureUrl != null) {
//                //写入流
//                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                FileInputStream fileInputStream = new FileInputStream(new File(pictureUrl));
//                byte[] data = new byte[1024];
//                int len = 0;
//                while ((len = fileInputStream.read(data)) != -1) {
//                    outputStream.write(data, 0, len);
//                }
//                int x = 1;
//                int y = 1;
//                int width = 6;
//                int height = 10;
//                // anchor主要用于设置图片的属性
//                HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 0, (short) x, y, (short) (x + width), (y + height));
//                patriarch.createPicture(anchor, wb.addPicture(outputStream.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
//            }
//            OutputStream os = new FileOutputStream(new File("E:\\test.xlsx"));
//            wb.write(os);
//            os.flush();
//            os.close();
//            wb.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 导出表格数据excel
//     */
//    public static void exportExcel(List<Map<String, Object>> list, String[] cellTitleName, String[] parentCellTitle, String titleName) throws IOException {
//        // 创建excel
//        SXSSFWorkbook wb = new SXSSFWorkbook();
//        // 创建sheet
//        SXSSFSheet sheet = wb.createSheet(titleName);
//        // 自适应宽度
//        sheet.autoSizeColumn(1, true);
//        // 创建第一行（标题行）
//        int rowStart = 0;
//        SXSSFRow parentTitle = null;
//        if (parentCellTitle != null) {
//            //带有复合表头.先创建复合表头
//            parentTitle = sheet.createRow(rowStart);
//            parentTitle.setHeight((short) 400);
//            rowStart++;
//        }
//        SXSSFRow rowTitle = sheet.createRow(rowStart);
//        //  目的是想把行高设置成400px
//        rowTitle.setHeight((short) 400);
//
//        // 创建标题栏样式
//        CellStyle styleTitle = wb.createCellStyle();
//        // 水平居中
//        styleTitle.setAlignment(CellStyle.ALIGN_CENTER);
//        //垂直居中
//        styleTitle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//        // 设置背景色
//        styleTitle.setFillForegroundColor((short) 13);
//        styleTitle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        //  下边框
//        styleTitle.setBorderBottom(CellStyle.BORDER_THIN);
//        //  左边框
//        styleTitle.setBorderLeft(CellStyle.BORDER_THIN);
//        //  上边框
//        styleTitle.setBorderTop(CellStyle.BORDER_THIN);
//        //  右边框
//        styleTitle.setBorderRight(CellStyle.BORDER_THIN);
//        // 设置标题字体
//        Font fontTitle = wb.createFont();
//        // 宋体加粗
//        fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);
//        fontTitle.setFontName("宋体");
//        fontTitle.setFontHeight((short) 200);
//        //  设置字体大小
//        fontTitle.setFontHeightInPoints((short) 12);
//        styleTitle.setFont(fontTitle);
//
//        if (list != null && list.size() > 0) {
//            if (parentTitle != null) {
//                String[] parentCellTitleNew = parentCellTitle;
//                int cellIndex = 0;
//                for (int i = 0; i < parentCellTitleNew.length; i++) {
//                    SXSSFCell cell = parentTitle.createCell(cellIndex);
//                    String[] split = cellTitleName[i].split("=");
//                    cell.setCellValue(parentCellTitleNew[i].isEmpty() ? (split.length == 2 ? split[1] : split[0]) : parentCellTitleNew[i]);
//                    cell.setCellStyle(styleTitle);
//                    cellIndex++;
//                }
//            }
//
//            // 表头以自定义为准
//            String[] cellTitleNameNew = cellTitleName;
//
//            // 在第一行上创建标题列
//            SXSSFCell cellTitle = null;
//            int cellInde = 0;
//            for (String str : cellTitleNameNew) {
//                cellTitle = rowTitle.createCell(cellInde);
//                if (StringUtils.isEmpty(str)) {
//                    cellTitle.setCellValue("未定义");
//                } else if (str.split("=").length == 1) {
//                    cellTitle.setCellValue(str.split("=")[0]);
//                } else if (StringUtils.isEmpty(str.split("=")[1])) {
//                    cellTitle.setCellValue(str.split("=")[0]);
//                } else {
//                    cellTitle.setCellValue(str.split("=")[1]);
//                }
//                cellTitle.setCellStyle(styleTitle);
//                cellInde = cellInde + 1;
//            }
//
//            // 创建内容
//            Map<String, Object> map = null;
//            CellStyle styleCenter = wb.createCellStyle();
//            // 右对齐
//            styleCenter.setAlignment(CellStyle.ALIGN_RIGHT);
//            //  设置自动换行
//            styleCenter.setWrapText(true);
//            for (int i = 0; i < list.size(); i++) {
//                map = list.get(i);
//                SXSSFRow row = sheet.createRow(i + 1 + rowStart);
//                cellInde = 0;
//                SXSSFCell cell = null;
//                for (String str : cellTitleNameNew) {
//                    cell = row.createCell(cellInde);
//                    if (map.get(str.split("=")[0]) == null) {
//                        cell.setCellValue("");
//                    } else {
//                        String value = map.get(str.split("=")[0]).toString();
//
//                        if (NumberUtils.isNumber(value)) {
//                            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
//                            try {
//                                //丑陋的解决，后面改用BigDecimal解决
//                                if (Double.valueOf(value).toString().contains("E")) {
//                                    //如果已经展示为科学记数法，则用原来的字符串值
//                                    cell.setCellType(Cell.CELL_TYPE_STRING);
//                                    cell.setCellValue(value);
//                                } else {
//                                    cell.setCellValue(Double.valueOf(value));
//                                }
//                            } catch (NumberFormatException e) {
//                                cell.setCellValue(value);
//                            }
//                        } else {
//                            cell.setCellValue(value);
//                        }
//
//                    }
//                    cell.setCellStyle(styleCenter);
//                    cellInde = cellInde + 1;
//                }
//
//            }
//        } else {
//            // 在第一行上创建标题列
//            SXSSFCell cellTitle = rowTitle.createCell(0);
//            cellTitle.setCellValue("暂无数据");
//        }
//        //合并复合表头
//        if (parentCellTitle != null) {
//            //合并的单元格样式
//            for (int i = 0; i < parentCellTitle.length; i++) {
//                if (parentCellTitle[i].isEmpty()) {
//                    //合并列
//                    CellRangeAddress region = new CellRangeAddress(0, 1, i, i);
//                    sheet.addMergedRegion(region);
//                } else {
//                    //合并行
//                    int j = i + 1;
//                    for (; j < parentCellTitle.length; j++) {
//                        if (parentCellTitle[j] != parentCellTitle[i]) {
//                            break;
//                        } else {
//                            parentTitle.getCell(j).setCellValue("");
//                        }
//                    }
//                    CellRangeAddress region = new CellRangeAddress(0, 0, i, j - 1);
//                    sheet.addMergedRegion(region);
//                }
//            }
//        }
//        log.info("生成SXSSFWorkbook结束");
//        OutputStream os = new FileOutputStream(new File("E:\\test.xlsx"));
//        wb.write(os);
//    }
//}
