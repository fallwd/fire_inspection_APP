package com.hr.fire.inspection.utils;

import android.util.Log;
import com.apkfuns.logutils.LogUtils;
import com.hr.fire.inspection.entity.CheckType;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: Excel 工具类
 * @author: ODM
 * @date: 2020/4/11
 */
public class ImportExcel {

    /**
     * 读取Excel文件
     * @param file
     * @throws FileNotFoundException
     */
    public static List<List<Map<String,Object>>> readExcel(File file, List<CheckType> checkTypes) throws FileNotFoundException {
        if(file == null) {
            Log.e("NullFile","读取Excel出错，文件为空文件");
            return null;
        }

        List<List<Map<String,Object>>> result = new ArrayList<>();

        InputStream stream = new FileInputStream(file);
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(stream);
            for (int i=0;i<checkTypes.size() ;i++) {
                CheckType checkType = checkTypes.get(i);

                HSSFSheet sheet = workbook.getSheet(checkType.getName()); //获取对应的sheet
                int rowsCount = sheet.getPhysicalNumberOfRows();
                FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

                List<Map<String,Object>> list = new ArrayList<>();
                //获取数据
                String[] titles = null;
                for (int r = 2; r<rowsCount; r++) {
                    Row row = sheet.getRow(r);
                    int cellsCount = row.getPhysicalNumberOfCells();

                    if (r == 2) { //获取表头
                        titles = new String[cellsCount];
                        for (int c = 0; c < cellsCount;c++) {
                            titles[c] = getCellAsString(row, c, formulaEvaluator);
                        }
                        continue;
                    }


                    //获取数据
                    Map<String,Object> map = new HashMap<>();
                    for (int c = 0;c < cellsCount ;c++) {
                        map.put(titles[c],getCellAsString(row, c, formulaEvaluator));
                    }
                    list.add(map);
                }

                result.add(list);

            }

        } catch (Exception e) {
            LogUtils.e(e.toString());
        }


        return result;

    }

    /**
     * 读取excel文件中每一行的内容
     * @param row
     * @param c
     * @param formulaEvaluator
     * @return
     */
    private static String getCellAsString(Row row, int c, FormulaEvaluator formulaEvaluator) {
        String value = "";
        try {
            Cell cell = row.getCell(c);
            CellValue cellValue = formulaEvaluator.evaluate(cell);
            switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    value = ""+cellValue.getBooleanValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    double numericValue = cellValue.getNumberValue();
                    if(HSSFDateUtil.isCellDateFormatted(cell)) {
                        double date = cellValue.getNumberValue();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                        value = formatter.format(HSSFDateUtil.getJavaDate(date));
                    } else {
                        value = ""+numericValue;
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    value = ""+cellValue.getStringValue();
                    break;
                default:
                    break;
            }
        } catch (NullPointerException e) {
            /* proper error handling should be here */
            LogUtils.e(e.toString());
        }
        return value;
    }

    /**
     * 根据类型后缀名简单判断是否Excel文件
     * @param file 文件
     * @return 是否Excel文件
     */
    public static boolean checkIfExcelFile(File file){
        if(file == null) {
            return false;
        }
        String name = file.getName();
        //”.“ 需要转义字符
        String[] list = name.split("\\.");
        //划分后的小于2个元素说明不可获取类型名
        if(list.length < 2) {
            return false;
        }
        String  typeName = list[list.length - 1];
        //满足xls或者xlsx才可以
        return "xls".equals(typeName) || "xlsx".equals(typeName);
    }
}