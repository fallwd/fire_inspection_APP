package com.hr.fire.inspection.adapter;

        import android.content.Context;
        import android.os.Build;
        import android.os.Environment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.RequiresApi;
        import com.hr.fire.inspection.R;
        import com.hr.fire.inspection.activity.CheckReport;
        import com.hr.fire.inspection.entity.InspectionResult;
        import com.hr.fire.inspection.service.ServiceFactory;

        import org.apache.poi.hssf.usermodel.HSSFCell;
        import org.apache.poi.hssf.usermodel.HSSFCellStyle;
        import org.apache.poi.hssf.usermodel.HSSFRow;
        import org.apache.poi.hssf.usermodel.HSSFSheet;
        import org.apache.poi.hssf.usermodel.HSSFWorkbook;
        import org.apache.poi.ss.usermodel.Sheet;
        import org.apache.poi.ss.usermodel.Workbook;

        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.HashMap;
        import java.util.List;

public class CheckReporItemAdapter extends BaseAdapter {

    private static Context mContext;
    private List<String> stringArrayList;
    private List<Long> companyInfoId;
    private List<Date> checkDate;
    private String set_company_name;
    private String set_oil_name;
    private String set_Platform_name;
    private List<String> chairpersons;
    private List<HashMap> goalless;
    private List<Long> systemId;

    // ————————————————————————————————————————————————————————
    public CheckReporItemAdapter(CheckReport mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return stringArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_fire_report_item, parent, false);
            holder = new ViewHolder();
            holder.viewBtn = convertView.findViewById(R.id.output_word);
            holder.sayTextView = convertView.findViewById(R.id.dong_context);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.sayTextView.setText(stringArrayList.get(position));
        // 点击导出按钮 生成报告
        holder.viewBtn.setOnClickListener(v -> {
            Toast.makeText(mContext, "正在生成报告，请稍后...", Toast.LENGTH_SHORT).show();
            // 获取后台参数
            goalless = ServiceFactory.getInspectionService().getOutputItemData(companyInfoId.get(position), systemId.get(position), chairpersons.get(position), checkDate.get(position));
            Log.d("getallmessage", goalless +"");

            // 获得系统id号并进行区分
            // 传入模板的数据
            List<InspectionResult> inspectionResult = (List) goalless.get(0).get("data");
            Log.d("inspectionResult", inspectionResult +"");
            String sheena = (String) goalless.get(0).get("systemName");
//            Log.d("systemName", sheena);
            try {
                writeExcelTwo(stringArrayList.get(position), inspectionResult,sheena);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // 点击搜索实现模糊查询
        return convertView;
    }

    public void setData(List<HashMap> mapList) {
        stringArrayList = new ArrayList<>();
        companyInfoId = new ArrayList<>();
        checkDate = new ArrayList<>();
        systemId = new ArrayList<>();
        chairpersons = new ArrayList<>();
        for (int i = 0; i < mapList.size(); i++) {
            HashMap hashMap = mapList.get(i);
            String ret = (String) hashMap.get("ret");
            Long infoId = (Long) hashMap.get("companyInfoId");
            Date check_date = (Date) hashMap.get("checkDate");
            Long sysId = (Long) hashMap.get("systemId");
            String chairperson = (String) hashMap.get("checkPerson");
            companyInfoId.add(infoId);
            stringArrayList.add(ret);
            checkDate.add(check_date);
            systemId.add(sysId);
            chairpersons.add(chairperson);
        }
    }

    public void get_company_name(String company_name) {
        set_company_name = company_name;
    }

    public void get_oil_name(String oil_name) {
        set_oil_name = oil_name;
    }

    public void get_Platform_name(String platform_name) {
        set_Platform_name = platform_name;
    }

    public void refresh(List arr) {
        this.stringArrayList = arr;
        Log.d("更新报告列表", this.stringArrayList+ "");
    }

    static class ViewHolder {
        Button viewBtn;
        TextView sayTextView;
    }
    
        //poi实现Excel文件写入 itemCon 文件名称
        public void writeExcelTwo(String filename, List<InspectionResult> excleList, String SheetName) throws IOException{
                System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
                System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
                System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");

                InputStream filePath = mContext.getAssets().open("check_template.xls");
                HSSFWorkbook workbook = new HSSFWorkbook(filePath);    //读取excel模板
                //获取工作表
                HSSFSheet sheet = workbook.getSheet(SheetName);
                removeModelSheet(workbook,sheet);
                //设置单元格样式
                HSSFCellStyle cellStyle = workbook.createCellStyle();
                //设置边框:
                cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
                cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
                cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
                cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
                //设置居中:
                cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
                //从索引3开始  因为1,2上是表头名称
                Log.d("生成表数据的行数", excleList.size()+"");
                checkSystem(sheet, excleList, SheetName,cellStyle);
                try {
                    //创建一个文件
                        String path = Environment.getExternalStorageDirectory().getPath();
                        File file = new File(path + "/" + filename + ".xls");
                        OutputStream stream = new FileOutputStream(file);//将Excel文件写入创建的file当中
                        workbook.write(stream);// 写入流
                        stream.close();//关闭流
                    Toast.makeText(mContext, "报告生成成功", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, "报告生成失败", Toast.LENGTH_SHORT).show();
                }
            }
        // 选择系统
        private void checkSystem(HSSFSheet sheet, List<InspectionResult> excelList, String sheetName, HSSFCellStyle cellStyle) {
        Log.d("sheet表名", sheetName);
            switch (sheetName){
                case "灭火器":
                    getMHQData(sheet,excelList,cellStyle);
                    break;
                case "气体灭火器系统":
                    getMHSystem(sheet,excelList,cellStyle);
                    break;
                case "防风火闸":
                    getFFHZData(sheet,excelList,cellStyle);
                    break;
                case "雨淋阀":
                    getYLFData(sheet,excelList,cellStyle);
                    break;
                case "消防软管站":
                    getXFRGZData(sheet,excelList,cellStyle);
                    break;
                case "消防水龙带":
                    getXFSLDData(sheet,excelList,cellStyle);
                    break;
                case "火气探头及火灾盘":
                    getTDHZPDData(sheet,excelList,cellStyle);
                    break;
                case "厨房湿粉灭火系统":
                    getCFSFDData(sheet,excelList,cellStyle);
                    break;
                case "泡沫灭火系统":
                    getPMMHData(sheet,excelList,cellStyle);
                    break;
                case "消防泵":
                    getXFBData(sheet,excelList,cellStyle);
                    break;
                case "消防员装备箱":
                    getXFZZXData(sheet,excelList,cellStyle);
                    break;
                case "消防水炮":
                    getXXSPData(sheet,excelList,cellStyle);
                    break;
            }
        }
    //消防水炮
    private void getXXSPData(HSSFSheet sheet, List<InspectionResult> excelList, HSSFCellStyle cellStyle) {
        for(int i=2, j= 0;j<excelList.size();i++,j++){
            //创建新的下一行  i为行数
            HSSFRow nextRow = sheet.createRow(i);
            InspectionResult inspectionResult = excelList.get(j);
            //创建下一行的单元格对象 索引是一行中的第几个单元格
            HSSFCell cell2 = nextRow.createCell(0);
            cell2.setCellValue(j+1);// 序号
            cell2.setCellStyle(cellStyle);
            cell2 = nextRow.createCell(1);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam1());
            cell2 = nextRow.createCell(2);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam2());
            cell2 = nextRow.createCell(3);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam3());
            cell2 = nextRow.createCell(4);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam4());
            cell2 = nextRow.createCell(5);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam5());
            cell2 = nextRow.createCell(6);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam6());
            cell2 = nextRow.createCell(7);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getDescription());
            cell2 = nextRow.createCell(8);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getImgPath());
        }
    }

    //消防员装备箱
    private void getXFZZXData(HSSFSheet sheet, List<InspectionResult> excelList, HSSFCellStyle cellStyle) {
        for(int i=2, j= 0;j<excelList.size();i++,j++){
            //创建新的下一行  i为行数
            HSSFRow nextRow = sheet.createRow(i);
            InspectionResult inspectionResult = excelList.get(j);
            //创建下一行的单元格对象 索引是一行中的第几个单元格
            HSSFCell cell2 = nextRow.createCell(0);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(j+1);// 序号
            cell2 = nextRow.createCell(1);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam1());
            cell2 = nextRow.createCell(2);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam2());
            cell2 = nextRow.createCell(3);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam3());
            cell2 = nextRow.createCell(4);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam4());
            cell2 = nextRow.createCell(5);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam5());
            cell2 = nextRow.createCell(6);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam6());
            cell2 = nextRow.createCell(7);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam7());
            cell2 = nextRow.createCell(8);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam8());
            cell2 = nextRow.createCell(9);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam9());
            cell2 = nextRow.createCell(10);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam10());
            cell2 = nextRow.createCell(11);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam11());
            cell2 = nextRow.createCell(12);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam12());
            cell2 = nextRow.createCell(13);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam13());
            cell2 = nextRow.createCell(14);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam14());
            cell2 = nextRow.createCell(15);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam15());
            cell2 = nextRow.createCell(16);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam16());
            cell2 = nextRow.createCell(17);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam17());
            cell2 = nextRow.createCell(18);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam18());
            cell2 = nextRow.createCell(19);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam19());
            cell2 = nextRow.createCell(20);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam20());
            cell2 = nextRow.createCell(21);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam21());
            cell2 = nextRow.createCell(22);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam22());
            cell2 = nextRow.createCell(23);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam23());
            cell2 = nextRow.createCell(24);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam24());
            cell2 = nextRow.createCell(25);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam25());
            cell2 = nextRow.createCell(26);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam26());
            cell2 = nextRow.createCell(27);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getDescription());
            cell2 = nextRow.createCell(28);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getImgPath());

        }
    }

    // 消防泵
    private void getXFBData(HSSFSheet sheet, List<InspectionResult> excelList, HSSFCellStyle cellStyle) {
        for(int i=2, j= 0;j<excelList.size();i++,j++){
            //创建新的下一行  i为行数
            HSSFRow nextRow = sheet.createRow(i);
            InspectionResult inspectionResult = excelList.get(j);
            //创建下一行的单元格对象 索引是一行中的第几个单元格
            HSSFCell cell2 = nextRow.createCell(0);
            cell2.setCellValue(j+1);// 序号
            cell2.setCellStyle(cellStyle);
            cell2 = nextRow.createCell(1);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam1());
            cell2 = nextRow.createCell(2);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam2());
            cell2 = nextRow.createCell(3);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam3());
            cell2 = nextRow.createCell(4);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam4());
            cell2 = nextRow.createCell(5);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam5());
            cell2 = nextRow.createCell(6);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam6());
            cell2 = nextRow.createCell(7);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam7());
            cell2 = nextRow.createCell(8);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam8());
            cell2 = nextRow.createCell(9);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam9());
            cell2 = nextRow.createCell(10);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam10());
            cell2 = nextRow.createCell(11);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam11());
            cell2 = nextRow.createCell(12);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam12());
            cell2 = nextRow.createCell(13);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam13());
            cell2 = nextRow.createCell(14);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam14());
            cell2 = nextRow.createCell(15);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam15());
            cell2 = nextRow.createCell(16);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getDescription());
            cell2 = nextRow.createCell(17);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getImgPath());
        }
    }

    //泡沫灭火系统
    private void getPMMHData(HSSFSheet sheet, List<InspectionResult> excelList, HSSFCellStyle cellStyle) {
        for(int i=2, j= 0;j<excelList.size();i++,j++){
            //创建新的下一行  i为行数
            HSSFRow nextRow = sheet.createRow(i);
            InspectionResult inspectionResult = excelList.get(j);
            //创建下一行的单元格对象 索引是一行中的第几个单元格
            HSSFCell cell2 = nextRow.createCell(0);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(j+1);// 序号
            cell2 = nextRow.createCell(1);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam1());
            cell2 = nextRow.createCell(2);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam2());
            cell2 = nextRow.createCell(3);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam3());
            cell2 = nextRow.createCell(4);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam4());
            cell2 = nextRow.createCell(5);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam5());
            cell2 = nextRow.createCell(6);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam6());
            cell2 = nextRow.createCell(7);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam7());
            cell2 = nextRow.createCell(8);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam8());
            cell2 = nextRow.createCell(9);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam9());
            cell2 = nextRow.createCell(10);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam10());
            cell2 = nextRow.createCell(11);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam11());
            cell2 = nextRow.createCell(12);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam12());
            cell2 = nextRow.createCell(13);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam13());
            cell2 = nextRow.createCell(14);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam14());
            cell2 = nextRow.createCell(15);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam15());
            cell2 = nextRow.createCell(16);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam16());
            cell2 = nextRow.createCell(17);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam17());
            cell2 = nextRow.createCell(18);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam18());
            cell2 = nextRow.createCell(19);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam19());
            cell2 = nextRow.createCell(20);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam20());
            cell2 = nextRow.createCell(21);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam21());
            cell2 = nextRow.createCell(22);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam22());
            cell2 = nextRow.createCell(23);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam23());
            cell2 = nextRow.createCell(24);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getDescription());
            cell2 = nextRow.createCell(25);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getImgPath());
        }
    }

    //厨房湿粉灭火系统
    private void getCFSFDData(HSSFSheet sheet, List<InspectionResult> excelList, HSSFCellStyle cellStyle) {
        for(int i=2, j= 0;j<excelList.size();i++,j++){
            //创建新的下一行  i为行数
            HSSFRow nextRow = sheet.createRow(i);
            InspectionResult inspectionResult = excelList.get(j);
            //创建下一行的单元格对象 索引是一行中的第几个单元格
            HSSFCell cell2 = nextRow.createCell(0);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(j+1);// 序号
            cell2 = nextRow.createCell(1);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam1());
            cell2 = nextRow.createCell(2);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam2());
            cell2 = nextRow.createCell(3);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam3());
            cell2 = nextRow.createCell(4);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam4());
            cell2 = nextRow.createCell(5);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam5());
            cell2 = nextRow.createCell(6);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam6());
            cell2 = nextRow.createCell(7);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam7());
            cell2 = nextRow.createCell(8);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam8());
            cell2 = nextRow.createCell(9);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam9());
            cell2 = nextRow.createCell(10);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam10());
            cell2 = nextRow.createCell(11);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getDescription());
            cell2 = nextRow.createCell(12);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getImgPath());
        }
    }

    //火气探头及火灾盘
    private void getTDHZPDData(HSSFSheet sheet, List<InspectionResult> excelList, HSSFCellStyle cellStyle) {
        // 火灾探头检查表
        for(int i=2, j= 0;j<excelList.size();i++,j++){
            //创建新的下一行  i为行数
            HSSFRow nextRow = sheet.createRow(i);
            InspectionResult inspectionResult = excelList.get(j);
            //创建下一行的单元格对象 索引是一行中的第几个单元格
            HSSFCell cell2 = nextRow.createCell(0);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(j+1);// 序号
            cell2 = nextRow.createCell(1);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam1());
            cell2 = nextRow.createCell(2);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam2());
            cell2 = nextRow.createCell(3);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam3());
            cell2 = nextRow.createCell(4);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam4());
            cell2 = nextRow.createCell(5);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam5());
            cell2 = nextRow.createCell(6);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam6());
            cell2 = nextRow.createCell(7);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam7());
            cell2 = nextRow.createCell(8);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam8());
            cell2 = nextRow.createCell(9);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam9());
            cell2 = nextRow.createCell(10);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getDescription());
            cell2 = nextRow.createCell(11);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getImgPath());
        }
       //  火气监控系统检查表
        for(int i=20, j = 0; j<excelList.size(); i++,j++){
            //创建新的下一行  i为行数
            HSSFRow nextRow = sheet.createRow(i);
            InspectionResult inspectionResult = excelList.get(j);
            //创建下一行的单元格对象 索引是一行中的第几个单元格
            HSSFCell cell2 = nextRow.createCell(0);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(j+1);// 序号
            cell2 = nextRow.createCell(1);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam1());
            cell2 = nextRow.createCell(2);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam2());
            cell2 = nextRow.createCell(3);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam3());
            cell2 = nextRow.createCell(4);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam4());
            cell2 = nextRow.createCell(5);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam5());
            cell2 = nextRow.createCell(6);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam6());
            cell2 = nextRow.createCell(7);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getDescription());
            cell2 = nextRow.createCell(8);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getImgPath());
        }
    }

    // 消防水龙带
    private void getXFSLDData(HSSFSheet sheet, List<InspectionResult> excelList, HSSFCellStyle cellStyle) {
        for(int i=2, j= 0;j<excelList.size();i++,j++){
            //创建新的下一行  i为行数
            HSSFRow nextRow = sheet.createRow(i);
            InspectionResult inspectionResult = excelList.get(j);
            //创建下一行的单元格对象 索引是一行中的第几个单元格
            HSSFCell cell2 = nextRow.createCell(0);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(j+1);// 序号
            cell2 = nextRow.createCell(1);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam1());
            cell2 = nextRow.createCell(2);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam2());
            cell2 = nextRow.createCell(3);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam3());
            cell2 = nextRow.createCell(4);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam4());
            cell2 = nextRow.createCell(5);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam5());
            cell2 = nextRow.createCell(6);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam6());
            cell2 = nextRow.createCell(7);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam7());
            cell2 = nextRow.createCell(8);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam8());
            cell2 = nextRow.createCell(9);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam9());
            cell2 = nextRow.createCell(10);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getDescription());
            cell2 = nextRow.createCell(11);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getImgPath());
        }
    }

    //消防软管站
    private void getXFRGZData(HSSFSheet sheet, List<InspectionResult> excelList, HSSFCellStyle cellStyle) {
        for(int i=2, j= 0;j<excelList.size();i++,j++){
            //创建新的下一行  i为行数
            HSSFRow nextRow = sheet.createRow(i);
            InspectionResult inspectionResult = excelList.get(j);
            //创建下一行的单元格对象 索引是一行中的第几个单元格
            HSSFCell cell2 = nextRow.createCell(0);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(j+1);// 序号
            cell2 = nextRow.createCell(1);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam1());
            cell2 = nextRow.createCell(2);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam2());
            cell2 = nextRow.createCell(3);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam3());
            cell2 = nextRow.createCell(4);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam4());
            cell2 = nextRow.createCell(5);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam5());
            cell2 = nextRow.createCell(6);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam6());
            cell2 = nextRow.createCell(7);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam7());
            cell2 = nextRow.createCell(8);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam8());
            cell2 = nextRow.createCell(9);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam9());
            cell2 = nextRow.createCell(10);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam10());
            cell2 = nextRow.createCell(11);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam11());
            cell2 = nextRow.createCell(12);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam12());
            cell2 = nextRow.createCell(13);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam13());
            cell2 = nextRow.createCell(14);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getDescription());
            cell2 = nextRow.createCell(15);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getImgPath());
        }
    }

    // 雨淋阀
    private static void getYLFData(HSSFSheet sheet, List<InspectionResult> excelList, HSSFCellStyle cellStyle) {
        for(int i=2, j= 0;j<excelList.size();i++,j++){
            //创建新的下一行  i为行数
            HSSFRow nextRow = sheet.createRow(i);
            InspectionResult inspectionResult = excelList.get(j);
            //创建下一行的单元格对象 索引是一行中的第几个单元格
            HSSFCell cell2 = nextRow.createCell(0);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(j+1);// 序号
            cell2 = nextRow.createCell(1);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam1());
            cell2 = nextRow.createCell(2);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam2());
            cell2 = nextRow.createCell(3);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam3());
            cell2 = nextRow.createCell(4);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam4());
            cell2 = nextRow.createCell(5);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam5());
            cell2 = nextRow.createCell(6);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam6());
            cell2 = nextRow.createCell(7);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam7());
            cell2 = nextRow.createCell(8);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam8());
            cell2 = nextRow.createCell(9);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam9());
            cell2 = nextRow.createCell(10);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam10());
            cell2 = nextRow.createCell(11);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam11());
            cell2 = nextRow.createCell(12);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam12());
            cell2 = nextRow.createCell(13);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam13());
            cell2 = nextRow.createCell(14);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam14());
            cell2 = nextRow.createCell(15);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getDescription());
            cell2 = nextRow.createCell(16);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getImgPath());
        }
    }

    // 防风火闸
    private void getFFHZData(HSSFSheet sheet, List<InspectionResult> excelList, HSSFCellStyle cellStyle) {
        for(int i=2, j= 0;j<excelList.size();i++,j++){
            //创建新的下一行  i为行数
            HSSFRow nextRow = sheet.createRow(i);
            InspectionResult inspectionResult = excelList.get(j);
            //创建下一行的单元格对象 索引是一行中的第几个单元格
            HSSFCell cell2 = nextRow.createCell(0);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(j+1);// 序号
            cell2 = nextRow.createCell(1);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam1());
            cell2 = nextRow.createCell(2);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam2());
            cell2 = nextRow.createCell(3);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam3());
            cell2 = nextRow.createCell(4);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam4());
            cell2 = nextRow.createCell(5);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam5());
            cell2 = nextRow.createCell(6);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam6());
            cell2 = nextRow.createCell(7);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam7());
            cell2 = nextRow.createCell(8);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getDescription());
            cell2 = nextRow.createCell(9);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getImgPath());
        }
    }

    // 气体灭火器系统
    private void getMHSystem(HSSFSheet sheet, List<InspectionResult> excelList, HSSFCellStyle cellStyle) {
        for(int i=2, j= 0;j<excelList.size();i++,j++){
            //创建新的下一行  i为行数
            HSSFRow nextRow = sheet.createRow(i);
            InspectionResult inspectionResult = excelList.get(j);
            //创建下一行的单元格对象 索引是一行中的第几个单元格
            HSSFCell cell2 = nextRow.createCell(0);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(j+1);// 序号
            cell2 = nextRow.createCell(1);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam1());
            cell2 = nextRow.createCell(2);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam2());
            cell2 = nextRow.createCell(3);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam3());
            cell2 = nextRow.createCell(4);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam4());
            cell2 = nextRow.createCell(5);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam5());
            cell2 = nextRow.createCell(6);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam6());
            cell2 = nextRow.createCell(7);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam7());
            cell2 = nextRow.createCell(8);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam8());
            cell2 = nextRow.createCell(9);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam9());
            cell2 = nextRow.createCell(10);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam10());
            cell2 = nextRow.createCell(11);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam11());
            cell2 = nextRow.createCell(12);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam12());
            cell2 = nextRow.createCell(13);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam13());
            cell2 = nextRow.createCell(14);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam14());
            cell2 = nextRow.createCell(15);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam15());
            cell2 = nextRow.createCell(16);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam16());
            cell2 = nextRow.createCell(17);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam17());
            cell2 = nextRow.createCell(18);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam18());
            cell2 = nextRow.createCell(19);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam19());
            cell2 = nextRow.createCell(20);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam20());
            cell2 = nextRow.createCell(21);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam21());
            cell2 = nextRow.createCell(22);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam22());
            cell2 = nextRow.createCell(23);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam23());
            cell2 = nextRow.createCell(24);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getParam24());
            cell2 = nextRow.createCell(25);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getDescription());
            cell2 = nextRow.createCell(26);cell2.setCellStyle(cellStyle);
            cell2.setCellValue(inspectionResult.getImgPath());
        }
    }

    // 灭火器
    private void getMHQData(HSSFSheet sheet, List<InspectionResult> excelList, HSSFCellStyle cellStyle) {
            for(int i=2, j= 0;j<excelList.size();i++,j++){
                //创建新的下一行  i为行数
                HSSFRow nextRow = sheet.createRow(i);
                InspectionResult inspectionResult = excelList.get(j);
                //创建下一行的单元格对象 索引是一行中的第几个单元格
                HSSFCell cell2 = nextRow.createCell(0);
                cell2.setCellStyle(cellStyle);
                cell2.setCellValue(j+1);// 序号
                cell2 = nextRow.createCell(1);
                cell2.setCellStyle(cellStyle);
                cell2.setCellValue(inspectionResult.getParam1());
                cell2 = nextRow.createCell(2);
                cell2.setCellStyle(cellStyle);
                cell2.setCellValue(inspectionResult.getParam2());
                cell2 = nextRow.createCell(3);
                cell2.setCellStyle(cellStyle);
                cell2.setCellValue(inspectionResult.getParam3());
                cell2 = nextRow.createCell(4);
                cell2.setCellStyle(cellStyle);
                cell2.setCellValue(inspectionResult.getParam4());
                cell2 = nextRow.createCell(5);
                cell2.setCellStyle(cellStyle);
                cell2.setCellValue(inspectionResult.getParam5());
                cell2 = nextRow.createCell(6);
                cell2.setCellStyle(cellStyle);
                cell2.setCellValue(inspectionResult.getParam6());
                cell2 = nextRow.createCell(7);
                cell2.setCellStyle(cellStyle);
                cell2.setCellValue(inspectionResult.getParam7());
                cell2 = nextRow.createCell(8);
                cell2.setCellStyle(cellStyle);
                cell2.setCellValue(inspectionResult.getParam8());
                cell2 = nextRow.createCell(9);
                cell2.setCellStyle(cellStyle);
                cell2.setCellValue(inspectionResult.getParam9());
                cell2 = nextRow.createCell(10);
                cell2.setCellStyle(cellStyle);
                cell2.setCellValue(inspectionResult.getDescription());
                cell2 = nextRow.createCell(11);
                cell2.setCellStyle(cellStyle);
                cell2.setCellValue(inspectionResult.getImgPath());
            }
        }
    /**
     * 删除模板表格(除了 name之外所有的)
     */
    /**
     * 删除模板表格(除了 id之外所有的)
     */
    public static void removeModelSheet(Workbook wb, int id) {
        int numberOfSheets = wb.getNumberOfSheets();
        for (int i = numberOfSheets - 1; i > -1; i--) {
            if (i != id) {
                wb.removeSheetAt(i);
            }
        }
        //设置默认显示第一页
        wb.setActiveSheet(0);
    }
    /**
     * 删除模板表格(除了 name之外所有的)
     */
    public static void removeModelSheet(Workbook wb, String name) {
        int sheetIndex = wb.getSheetIndex(wb.getSheet(name));
        removeModelSheet(wb,sheetIndex);
    }
    /**
     * 删除模板表格(除了 noDelSheet之外所有的)
     */
    public static void removeModelSheet(Workbook wb, Sheet noDelSheet) {
        int sheetIndex = wb.getSheetIndex(noDelSheet);
        removeModelSheet(wb,sheetIndex);
    }

}
