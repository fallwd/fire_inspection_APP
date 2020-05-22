package com.hr.fire.inspection.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    private HSSFWorkbook workbook;

    public HSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(HSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public ExcelUtils() {
        this.workbook = new HSSFWorkbook();
    }


    public void exportExcel(Context mContext , String filename) {
        try {
            //创建一个文件
            String path = Environment.getExternalStorageDirectory().getPath();
            File file = new File(path + "/" + filename + ".xls");
            OutputStream stream = new FileOutputStream(file);//将Excel文件写入创建的file当中
            workbook.write(stream);// 写入流
            stream.close();//关闭流
            Toast.makeText(mContext, "报告生成成功", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Sheet genSheet(String sheetName,String[][] columnNames, String[] columnWidth, List<Map<String,Object>> rows, String title){
        Sheet sheet = workbook.createSheet(sheetName);
        CellStyle titleStyle = createTitleCellStyle(workbook);
        CellStyle headerStyle = createHeaderCellStyle(workbook);
        CellStyle dataStyle = createDataCellStyle(workbook);

        // 表格标题行
        Row row0 = sheet.createRow(0);
        row0.setHeight((short)(3 * 256));
        Cell cell0_0 = row0.createCell(0); // 创建单元格，参数说明的是第几个单元格
        cell0_0.setCellStyle(titleStyle);
        cell0_0.setCellValue(title); // 设置单元格 和里面的内容

        if(columnWidth.length>0){
            Integer clWidth;
            for(int i =0;i<columnWidth.length;i++){
                if(columnWidth[i]!=null &&!"".equals(columnWidth[i])){
                    clWidth = Integer.valueOf(columnWidth[i]);
                    sheet.setColumnWidth(i, clWidth*256);
                }
            }
        }



        Row row = null;
        Cell cell = null;
        for(int i = 1 ; i<=columnNames.length ; i++){
            row = sheet.createRow(i);
            row.setHeight((short)(2 * 256));
            for(int j = 0 ;j < columnNames[i-1].length;j++){
                cell = row.createCell(j);
                cell.setCellValue(columnNames[i-1][j]);
                cell.setCellStyle(headerStyle);

            }
        }

        sheet.getRow(columnNames.length).setZeroHeight(true);
        // 合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnNames[0].length-1)); // 合并大标题行
        String[] names = columnNames[columnNames.length-1];

        // 数据填充,标题占一行，columnNames占columnNames.length行，之后才到数据行
        Object obj = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < rows.size(); i++) {
            Row dataRow = sheet.createRow(columnNames.length+1+ i);
            Map<String,Object> project = rows.get(i);
            for (int j = 0; j <names.length; j++) {
                Cell dataCell = dataRow.createCell(j);
                dataCell.setCellStyle(dataStyle);
                obj = project.get(names[j]);
                dataCell.setCellValue(obj==null ? "" :
                                        obj instanceof java.util.Date ? sdf.format(obj) : obj.toString());
            }
        }
        return sheet;
    }


    /**
     * 给合并后的单元格加边框
     * @param border
     * @param region
     * @param sheet
     */
    private void setBorderStyle(BorderStyle border, CellRangeAddress region, Sheet sheet){
        RegionUtil.setBorderBottom(border, region, sheet);//下边框
        RegionUtil.setBorderLeft(border, region, sheet);     //左边框
        RegionUtil.setBorderRight(border, region, sheet);    //右边框
        RegionUtil.setBorderTop(border, region, sheet);      //上边框
    }

    /**
     * 创建标题样式
     * @param wb
     * @return
     */
    private CellStyle createTitleCellStyle(HSSFWorkbook wb) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直对齐
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());//背景颜色

        cellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN); //左边框
        cellStyle.setBorderRight(BorderStyle.THIN); //右边框
        cellStyle.setBorderTop(BorderStyle.THIN); //上边框

        Font headerFont1 = wb.createFont(); // 创建字体样式
        headerFont1.setBold(true); //字体加粗
        headerFont1.setFontName("宋体"); // 设置字体类型
        headerFont1.setFontHeightInPoints((short) 15); // 设置字体大小
        cellStyle.setFont(headerFont1); // 为标题样式设置字体样式

        return cellStyle;
    }

    /**
     * 创建表头样式
     * @param wb
     * @return
     */
    private CellStyle createHeaderCellStyle(HSSFWorkbook wb) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直对齐
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());//背景颜色

        cellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN); //左边框
        cellStyle.setBorderRight(BorderStyle.THIN); //右边框
        cellStyle.setBorderTop(BorderStyle.THIN); //上边框

        Font headerFont1 = wb.createFont(); // 创建字体样式
        headerFont1.setFontName("宋体"); // 设置字体类型
        headerFont1.setFontHeightInPoints((short) 12); // 设置字体大小
        cellStyle.setFont(headerFont1); // 为标题样式设置字体样式

        return cellStyle;
    }

    /**
     * 创建内容样式
     * @param
     * @return
     */
    private static CellStyle createDataCellStyle(HSSFWorkbook wb) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);// 水平居中
        cellStyle.setWrapText(true);// 设置自动换行
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN); //左边框
        cellStyle.setBorderRight(BorderStyle.THIN); //右边框
        cellStyle.setBorderTop(BorderStyle.THIN); //上边框

        // 生成10号字体
        Font font = wb.createFont();
        font.setColor((short)8);
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);

        return cellStyle;
    }



}
