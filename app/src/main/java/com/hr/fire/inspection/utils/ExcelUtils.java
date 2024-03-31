package com.hr.fire.inspection.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.deepoove.poi.config.Configure;
import com.hr.fire.inspection.activity.CarbondioxideRecordAcitivty;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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
            //保存到年ExportData这个文件夹下
            File path = Environment.getExternalStorageDirectory();
            path = Environment.getExternalStoragePublicDirectory("ExportData");
            File file = new File(path + "/" + filename + ".xls");
            OutputStream stream = new FileOutputStream(file);//将Excel文件写入创建的file当中
            Configure configs=Configure.createDefault();


            workbook.write(stream);// 写入流
            stream.close();//关闭流
            stream.flush();
            Toast.makeText(mContext, "导出数据成功", Toast.LENGTH_SHORT).show();
            if (file.isFile()) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() + ".fileProvider", file);
                intent.setDataAndType(uri, "application/vnd.ms-excel");
                file.mkdirs(); // 通知系统文件夹刷新  获取最新文件
                MediaScannerConnection.scanFile(mContext, new String[] { file.getAbsolutePath() }, null, null); // 通知系统文件夹刷新  获取最新文件
                mContext.startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, "导出数据失败", Toast.LENGTH_SHORT).show();
        }
    }


    public Sheet genSheet(Context context, String sheetName, String[][] columnNames, String[] columnWidth, List<Map<String,Object>> rows, String title) {
        Sheet sheet = workbook.createSheet(sheetName);
        //画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
        HSSFPatriarch patriarch = (HSSFPatriarch) sheet.createDrawingPatriarch();
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
            int pictureIndex = -1;
            String imageUrl = "";
            String videoUrl = "";
            for (int j = 0; j <names.length; j++) {
                Cell dataCell = dataRow.createCell(j);
                dataCell.setCellStyle(dataStyle);
                obj = project.get(names[j]);
                Log.d("tzw","name: " +names[j]+" obj:"+obj);
                if(TextUtils.equals(names[j],"imageUrl") ){
                    Object image = project.get("imageUrl");
                    if(image != null){
                        imageUrl = (String) image;
                    }

                    Object video =  project.get("videoUrl");
                    if(video != null){
                        videoUrl = (String)video;
                    }
                    Log.d("tzw videoUrl",videoUrl);
                    pictureIndex = j;
                }

                if(!TextUtils.equals(names[j],"imageUrl")){
                    dataCell.setCellValue(obj==null ? "" :
                            obj instanceof java.util.Date ? sdf.format(obj) : obj.toString());
                }else {
                    if(!TextUtils.isEmpty(videoUrl)){
                        dataCell.setCellValue(videoUrl);
                    }else if(!TextUtils.isEmpty(imageUrl)){
//                        dataCell.setCellValue(imageUrl);
                    }

                }

            }

            if(pictureIndex >= 0){
                byte[] byteArrayOut = null;
                if(!TextUtils.isEmpty(videoUrl)){
                    MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                    retriever.setDataSource(videoUrl);
                    Bitmap  bitmap1= retriever.getFrameAtTime(1000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
                    int size = bitmap1.getWidth() * bitmap1.getHeight() * 4;
                    ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
                    bitmap1.compress(Bitmap.CompressFormat.PNG, 10, baos);
                    byteArrayOut = baos.toByteArray();
                }else if(!TextUtils.isEmpty(imageUrl)){
                    Uri uri = Uri.parse(imageUrl);
                    byteArrayOut = UriToByte(uri,context);
                }

                if(byteArrayOut != null){
                    dataRow.setHeight((short) (10 * 256));
                    HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,(short) (pictureIndex), (i+3),(short) (pictureIndex+1), (i+4));
                    //插入图片
                    int pictureId = workbook.addPicture(byteArrayOut, HSSFWorkbook.PICTURE_TYPE_PNG);
                    Log.d("tzw","pictureId: " +pictureId);
                    patriarch.createPicture(anchor, pictureId);
                }
            }
        }
        return sheet;
    }
    public byte[] UriToByte(Uri uri,Context context) {
        String uriType = getUriType(uri);
        Bitmap bitmap1 = null;
        if (uriType.equals("Content")) {
            try {
                bitmap1 = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
                int size = bitmap1.getWidth() * bitmap1.getHeight() * 4;
                ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
                bitmap1.compress(Bitmap.CompressFormat.PNG, 10, baos);
                byte[] imagedata1 = baos.toByteArray();
                return  imagedata1;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (uriType.equals("File")) {
            File file = new File(uri.toString());
            FileInputStream fis = null;
            ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

            try {
                fis = new FileInputStream(file);
                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                int len;
                while ((len = fis.read(buffer)) != -1) {
                    byteBuffer.write(buffer, 0, len);
                }
                return byteBuffer.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    private ByteArrayOutputStream convertToByteArrayOutputStream(String imagePath)  {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (FileInputStream fis = new FileInputStream(imagePath)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();

            // 现在baos包含图片的字节数据
            byte[] imageBytes = baos.toByteArray();

            // 处理imageBytes...

        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos;
    }

    /*
    判断图片来源
     */
    public static String getUriType(Uri uri) {
        String scheme = uri.getScheme();

        if (uri.toString().startsWith("/storage")) {
            return "File";
        }
        if (scheme != null) {
            if (scheme.equals("file")) {
                return "File";
            } else if (scheme.equals("content")) {
                return "Content";
            } else {
                return "Other";
            }
        }
        return "Unknown";
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
        cellStyle.setAlignment(HorizontalAlignment.LEFT);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直对齐
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        cellStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());//背景颜色

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
//        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());//背景颜色

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
