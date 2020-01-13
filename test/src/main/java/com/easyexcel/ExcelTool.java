package com.easyexcel;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author rensong.pu
 * @date 2019/11/15 11:28 星期五
 **/

@SuppressWarnings("unchecked")
public class ExcelTool {

    public static void main(String[] args) throws IOException {
        final InputStream resourceAsStream = ExcelTool.class.getClassLoader().getResourceAsStream("data.json");
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
        String line;
        StringBuilder sb = new StringBuilder();
        while((line=bufferedReader.readLine())!=null){
            sb.append(line);
        }
        List<Map> data = new Gson().fromJson(sb.toString(), new TypeToken<List<Map>>() {
        }.getType());
        for(Map<String,Object> m:data){
            final Object predictData = m.get("predictData");
            final Object o1 = ((Map) m.get("predictData")).get("preCheckResult");
            final Object o = ((Map) m.get("predictData")).get("creditBalance");
            System.out.println(m.get("status")+"\t"+o1+"\t"+o);
        }
    }

    private ExcelTool() {
    }

    private static List<String> columns;//要解析excel中的列名
    private static int sheetNum = 0;//要解析的sheet下标
    private static boolean dynamicColumn = false;//是否
    private static Map<String, String> dynamicMapConfig = new ListOrderedMap();//動態列配置文件
    private static String pattern;// 日期格式

    /**
     * poi读取excle
     *
     * @return
     */
    public static String readExcel(File file) throws Exception {
        StringBuilder retJson = new StringBuilder();
        InputStream inStream = null;
        try {
            inStream = new FileInputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook(inStream);
            HSSFSheet sheet = workbook.getSheetAt(sheetNum);//获得表
            int lastRowNum = sheet.getLastRowNum();//最后一行
            retJson.append("[");
            for (int i = 0; i < lastRowNum; i++) {
                HSSFRow row = sheet.getRow(i + 1);//获得行
                String rowJson = readExcelRow(row);
                retJson.append(rowJson);
                if (i < lastRowNum - 1) {
                    retJson.append(",");
                }
            }
            retJson.append("]");
        } catch (Exception e) {
            try {
                inStream = new FileInputStream(file);
                XSSFWorkbook workbook = new XSSFWorkbook(inStream);
                XSSFSheet sheet = workbook.getSheetAt(sheetNum);
                int lastRowNum = sheet.getLastRowNum();//最后一行
                retJson.append("[");
                for (int i = 0; i < lastRowNum; i++) {
                    XSSFRow row = sheet.getRow(i + 1);//获得行
                    String rowJson = readExcelRow(row);
                    retJson.append(rowJson);
                    if (i < lastRowNum - 1) {
                        retJson.append(",");
                    }
                }
                retJson.append("]");
            } catch (IOException e1) {
                e1.printStackTrace();
                throw e1;
            }
        } finally {
            close(null, inStream);
        }
        return retJson.toString();
    }

    /**
     * poi读取excle 生成实体集合
     *
     * @param <E>
     * @return
     */
    public static <E> List<E> readExcel(File file, Class<E> clazz) throws Exception {
        if (columns == null) {
            setColumns(clazz);
        }
        InputStream inStream = null;
        List<E> eList = new ArrayList<E>();
        try {
            inStream = new FileInputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook(inStream);
            HSSFSheet sheet = workbook.getSheetAt(sheetNum);//获得表
            int lastRowNum = sheet.getLastRowNum();//最后一行
            if (dynamicColumn) {
                //动态列處理
                HSSFRow header = sheet.getRow(0);//表頭
                List<String> rList = readRow(header);
                setDynamicColumn(rList);
            }
            for (int i = 0; i < lastRowNum; i++) {
                HSSFRow row = sheet.getRow(i + 1);//获得行
                String rowJson = readExcelRow(row);
                E _e = JSON.parseObject(rowJson, clazz);
                eList.add(_e);
            }
        } catch (Exception e) {
            try {
                inStream = new FileInputStream(file);
                XSSFWorkbook workbook = new XSSFWorkbook(inStream);
                XSSFSheet sheet = workbook.getSheetAt(sheetNum);
                int lastRowNum = sheet.getLastRowNum();//最后一行
                if (dynamicColumn) {
                    //动态列處理
                    XSSFRow header = sheet.getRow(0);
                    List<String> rList = readRow(header);
                    setDynamicColumn(rList);
                }
                for (int i = 0; i < lastRowNum; i++) {
                    XSSFRow row = sheet.getRow(i + 1);//获得行
                    String rowJson = readExcelRow(row);
                    E _e = JSON.parseObject(rowJson, clazz);
                    eList.add(_e);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                throw e1;
            }
        } finally {
            close(null, inStream);
        }
        return eList;
    }

    /**
     * 动态列配置
     */
    private static void setDynamicColumn(List<String> headlist) throws Exception {
        List<String> tempcolumns = new ArrayList<String>();
        for (Iterator<String> iterator = headlist.iterator(); iterator.hasNext(); ) {
            String value = iterator.next();
            value = value.replace("（", "(");
            value = value.replace("）", ")");
            value = value.trim();
            String key = findKey(value);
            if (key == null) {
                throw new RuntimeException("请上传合法excle！");
            }
            tempcolumns.add(findKey(value));
        }
        setColumns(tempcolumns);//重新设置column
    }

    /**
     * 根据value动态找到key值
     *
     * @return
     */
    private static String findKey(String value) {
        for (Map.Entry<String, String> entryconfig : dynamicMapConfig.entrySet()) {
            String keyc = entryconfig.getKey();
            String valc = entryconfig.getValue();
            if (valc.equals(value)) {
                return keyc;
            }
        }
        return null;
    }

    /**
     * poi读取excle 生成实体集合
     *
     * @param file
     * @param clazz
     * @param exceptscolumns
     * @return
     */
    public static <E> List<E> readExcel(File file, Class<E> clazz, String[] exceptscolumns) throws Exception {
        setColumns(clazz, exceptscolumns);
        return readExcel(file, clazz);
    }

    /**
     * 读取excle多个sheet到多个对象（对象的顺序固定）
     *
     * @param file
     * @param clazz
     * @return
     */
    public static <E> List<List<E>> readExcel(File file, Class<E>[] clazz) throws Exception {
        List<List<E>> eliLists = new ArrayList<List<E>>();//[clazz.length];
        int i = 0;
        for (Class<E> cls : clazz) {
            setColumns(null, null);
            setSheetNum(i++);
            List<E> eList = readExcel(file, cls);
            eliLists.add(eList);
        }
        return eliLists;
    }

    /**
     * 读取行值
     *
     * @return
     */
    private static String readExcelRow(HSSFRow row) {
        StringBuilder rowJson = new StringBuilder();
        int lastCellNum = ExcelTool.columns.size();//最后一个单元格
        rowJson.append("{");
        for (int i = 0; i < lastCellNum; i++) {
            HSSFCell cell = row.getCell(i);
            String cellVal = getCellValueToString(cell);
            rowJson.append(toJsonItem(columns.get(i), cellVal));
            if (i < lastCellNum - 1) {
                rowJson.append(",");
            }
        }
        rowJson.append("}");
        return rowJson.toString();
    }

    /**
     * 读取行值
     *
     * @return
     */
    private static String readExcelRow(XSSFRow row) {
        StringBuilder rowJson = new StringBuilder();
        int lastCellNum = ExcelTool.columns.size();//最后一个单元格
        rowJson.append("{");
        for (int i = 0; i < lastCellNum; i++) {
            XSSFCell cell = row.getCell(i);
            String cellVal = getCellValueToString(cell);
            rowJson.append(toJsonItem(columns.get(i), cellVal));
            if (i < lastCellNum - 1) {
                rowJson.append(",");
            }
        }
        rowJson.append("}");
        return rowJson.toString();
    }

    /**
     * 读取行生称list
     *
     * @return
     */
    private static List<String> readRow(XSSFRow row) {
        List<String> ret = new ArrayList<String>();
        int cellcount = row.getLastCellNum();
        for (int i = 0; i < cellcount; i++) {
            XSSFCell cell = row.getCell(i);
            String cellval = getCellValueToString(cell);
            if (cellval.trim().length() > 0) {
                ret.add(cellval);
            }
        }
        return ret;
    }

    /**
     * 读取行生称list
     *
     * @return
     */
    private static List<String> readRow(HSSFRow row) {
        List<String> ret = new ArrayList<String>();
        int cellcount = row.getLastCellNum();
        for (int i = 0; i < cellcount; i++) {
            HSSFCell cell = row.getCell(i);
            String cellval = getCellValueToString(cell);
            if (cellval.trim().length() > 0) {
                ret.add(cellval);
            }
        }
        return ret;
    }

    /**
     * 读取单元格的值
     *
     * @return
     */
    @SuppressWarnings("static-access")
    private static String getCellValueToString(Cell cell) {
        String strCell = "";
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    if (pattern != null) {
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        strCell = sdf.format(date);
                    } else {
                        strCell = date.toString();
                    }
                    break;
                }
                // 不是日期格式，则防止当数字过长时以科学计数法显示
                //cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                strCell = cell.toString();
                break;
            case STRING:
                strCell = cell.getStringCellValue();
                break;
            default:
                strCell = "";
                break;
        }
        return strCell;
    }


    /**
     * 转换为json对
     *
     * @return
     */
    private static String toJsonItem(String name, String val) {
        return "\"" + name + "\":\"" + val + "\"";
    }

    /**
     * 关闭io流
     */
    private static void close(OutputStream out, InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                System.out.println("InputStream关闭失败");
                e.printStackTrace();
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                System.out.println("OutputStream关闭失败");
                e.printStackTrace();
            }
        }
    }

    /**
     * 填出数据到excel book中
     *
     * @param book
     * @param data
     * @param sheetname
     * @param titles
     * @param columns
     */
    public static void data2Book(Workbook book, List<? extends Object> data, String sheetname, String[] titles, String[] columns) throws Exception {
        Sheet sheet = book.createSheet(sheetname);
        Row th = sheet.createRow((short) 0);//标题行
        for (int i = 0; i < titles.length; i++) {
            Cell cell = th.createCell(i);
            cell.setCellValue(titles[i]);
        }
        Object _d = data.get(0);
        if (_d instanceof Map) {
            //Map集合
            for (int j = 0; j < data.size(); j++) {
                Map _dm = (Map) data.get(j);
                Row tr = sheet.createRow((short) (j + 1));//内容行
                for (int k = 0; k < columns.length; k++) {
                    Cell cell = tr.createCell(k);
                    Object val = _dm.get(columns[k]);
                    String value = val == null ? "" : val.toString();
                    cell.setCellValue(value);
                }

            }
        } else {
            //Bean集合
            for (int j = 0; j < data.size(); j++) {
                Object _do = data.get(j);
                Row tr = sheet.createRow((short) (j + 1));//内容行
                for (int k = 0; k < columns.length; k++) {
                    String column = columns[k];
                    Method method = getTargetGetMethod(_do, column);//获取目标方法
                    try {
                        Cell cell = tr.createCell(k);
                        Object val = method.invoke(_do);
                        String value = val == null ? "" : val.toString();
                        value = value.replace("00:00:00.0", "");//处理时间中非法字符
                        cell.setCellValue(value);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw e;
                    }
                }

            }
        }
    }

    /**
     * 获取bean的指定getter方法
     *
     * @param o
     * @param name
     * @return
     */
    private static Method getTargetGetMethod(Object o, String name) throws Exception {
        try {
            String mname = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
            return o.getClass().getMethod(mname);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 将bean所有属性放入map中
     */
    private static <E> void beanProp2List(Class<E> clazz, List<String> excepts) {
        Field[] fields = clazz.getDeclaredFields();
        columns = new ArrayList<String>();//顺序固定可重复
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            if (excepts != null && excepts.contains(fieldName)) {
                continue;
            }
            columns.add(fieldName);
        }
    }

    public static List<String> getColumns() {
        return ExcelTool.columns;
    }

    public static void setColumns(List<String> columns) {
        ExcelTool.columns = columns;
    }

    public static void setColumns(Class<?> clazz) {
        beanProp2List(clazz, null);
    }

    /**
     * 设置列，不包括excepts指定的字段
     *
     * @param clazz
     * @param excepts
     */
    public static void setColumns(Class<?> clazz, String[] excepts) {
        beanProp2List(clazz, Arrays.asList(excepts));
    }

    public static int getSheetNum() {
        return sheetNum;
    }

    public static void setSheetNum(int sheetNum) {
        ExcelTool.sheetNum = sheetNum;
    }

    public static boolean isDynamicColumn() {
        return dynamicColumn;
    }

    public static void setDynamicColumn(boolean dynamicColumn) {
        ExcelTool.dynamicColumn = dynamicColumn;
    }

    public static Map<String, String> getDynamicMapConfig() {
        return dynamicMapConfig;
    }

    public static void setDynamicMapConfig(Map<String, String> dynamicMapConfig) {
        ExcelTool.dynamicMapConfig = dynamicMapConfig;
    }

    public void setPattern(String pattern) {
        pattern = pattern;
    }
}
