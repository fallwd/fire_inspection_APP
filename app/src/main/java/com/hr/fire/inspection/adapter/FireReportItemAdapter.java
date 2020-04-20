package com.hr.fire.inspection.adapter;

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

import com.deepoove.poi.XWPFTemplate;
import com.hr.fire.inspection.R;
import com.hr.fire.inspection.activity.FireReportActivity;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.service.ServiceFactory;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FireReportItemAdapter extends BaseAdapter {

    private List<String> stringArrayList;
    private List<Long> companyInfoId;
    private List checkDate;
    private final FireReportActivity mContext;
    private String sele;
    private String set_company_name;
    private String set_oil_name;
    private String set_Platform_name;

    public FireReportItemAdapter(FireReportActivity mContext) {
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
        holder.viewBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Log.d("dong", "拿到了数据==  " + sele);

                // 获取后台参数

                final List<HashMap> getallmessage = ServiceFactory.getYearCheckService().getOutputItemData(companyInfoId.get(position), (Date) checkDate.get(position));

                // 获取第一个系统
                Log.d("getallmessage", String.valueOf(getallmessage.get(0)));
                // 获取ItemInfo对象
                final List<ItemInfo> date = (List) getallmessage.get(0).get("data");
                Log.d("getallmessage", String.valueOf(date.get(0)));
                ItemInfo itemObj = date.get(0);
                // 获取时间
                final Date checkDate = itemObj.getCheckDate();
                Log.d("getallmessage", checkDate+"");



                // 传入模板的数据
                Map<String, Object> tempdatas = new HashMap<String, Object>() {{
                    put("name", stringArrayList.get(position)); // 名称
                    put("Data", getDate(checkDate)); // 日期
                    put("nextCheckTime", netCheckTime(checkDate)); // 下次检验日期
                    put("Facility_name", set_company_name); // 设施名称Facility Name ->> 公司
                    put("oil_name", set_oil_name); // 设施名称Facility Name ->> ** 油田
                    put("platform", set_Platform_name); // 检验地点  ->  ** 平台
                    put("protectArea", ""); // 保护区域 protectArea
                }};
                try {
                    initWordTem(stringArrayList.get(position),tempdatas);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return convertView;
    }

    public void setData(List<HashMap> mapList) {
        stringArrayList = new ArrayList<>();
        companyInfoId = new ArrayList<>();
        checkDate = new ArrayList();
        for (int i = 0; i < mapList.size(); i++) {
            HashMap hashMap = mapList.get(i);
            String ret = (String) hashMap.get("ret");
            Long infoId = (Long) hashMap.get("companyInfoId");
            Date check_date = (Date) hashMap.get("checkDate");
            companyInfoId.add(infoId);
            stringArrayList.add(ret);
            checkDate.add(check_date);
        }
//        Log.d("key12222", String.valueOf(stringArrayList));
//        Log.d("key12222", String.valueOf(companyInfoId));
//        Log.d("key12222", String.valueOf(checkDate));

    }

    public void setSelectedData(String data) {
        sele = data;
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

    static class ViewHolder {
        public Button viewBtn;
        TextView sayTextView;
    }


    // 生成报告 参数
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initWordTem(String itemCon, Map<String, Object> tempdatas) throws IOException {
        Log.d("文件名称", String.valueOf(tempdatas));
        System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");

        InputStream open = mContext.getAssets().open("In_template.docx");
        XWPFTemplate template = XWPFTemplate.compile(open).render(tempdatas);


        try {
            FileOutputStream out;
            String path = Environment.getExternalStorageDirectory().getPath();
            File file = new File(path + "/" + itemCon + ".docx");
            Log.d("key", String.valueOf(file));
            out = new FileOutputStream(file);
            template.write(out);
            out.flush();
            out.close();
            template.close();
            Toast.makeText(mContext, "报告生成成功", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(mContext, "报告生成失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取手机时间  下次检验日期推迟一年减一天
     * return 年/月/日
     *
     * @param checkDate */

    private String netCheckTime(Date checkDate) {
        Calendar calendar = Calendar.getInstance();
//        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(checkDate);
        calendar.add(Calendar.YEAR, +1);
        calendar.add(Calendar.DATE, -1);//减1天
        checkDate = calendar.getTime();
        System.out.println(checkDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String sim = dateFormat.format(checkDate);
        Log.i("md", "推迟的时间为： " + sim);
        return sim;
    }

    /**
     * 获取手机时间  年/月/日
     *
     * @param date*/

    private String getDate(Date date) {
//        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String sim = dateFormat.format(date);
//        Log.i("md", "时间sim为： "+sim);
        return sim;
    }

    /**
     * 为表格插入数据，行数不够添加新行
     *
     * @param table     需要插入数据的表格
     * @param tableList 插入数据集合
     */
//    public static void insertTable(XWPFTable table, List<String[]> tableList) {
//        //table.addNewRowBetween 没实现，官网文档也说明，只有函数名，但没具体实现，但很多文章还介绍如何使用这个函数，真是害人
//        //table.insertNewTableRow 本文用这个可以，但是要创建 cell，否则不显示数据
//        //table.addRow() 在表格最后加一行
//        // table.addRow(XWPFTableRow row, int pos) 没试过，你可以试试。
//        //table.createRow() 在表格最后一加行
//
//        for (int i = 0; i < tableList.size(); i++) {//遍历要添加的数据的list
//            XWPFTableRow newRow = table.insertNewTableRow(i + 1);//为表格添加行
//            String[] strings = tableList.get(i);//获取list中的字符串数组
//            for (int j = 0; j < strings.length; j++) {//遍历list中的字符串数组
//                String strings1 = strings[j];
//                newRow.createCell();//在新增的行上面创建cell
//                newRow.getCell(j).setText(strings1);//给每个cell赋值。
//
//            }
//        }
//    }

    public static class MainTest {
        public static void main(String[] args) throws Exception {
            word2();
        }

        public static void word2() {
            XWPFDocument doc = new XWPFDocument();
            titleStyle(doc, "项目信息列表");


            // 创建20行7列
            XWPFTable table = doc.createTable(21, 7);
            tableBorderStyle(table);
            // table.set
            List<XWPFTableCell> tableCells1 = table.getRow(0).getTableCells();
            tableTextStyle(tableCells1, 0, "编码");
            tableTextStyle(tableCells1, 1, "名称");
            tableTextStyle(tableCells1, 2, "地址");
            tableTextStyle(tableCells1, 3, "电话");
            tableTextStyle(tableCells1, 4, "负责人");
            tableTextStyle(tableCells1, 5, "类型");
            tableTextStyle(tableCells1, 6, "备注");
            for (int i = 1; i < 21; i++) {
                List<XWPFTableCell> tableCells2 = table.getRow(i).getTableCells();
                for (int j = 0; j < 7; j++) {
                    tableTextStyle(tableCells2.get(j), i + "->" + j);
                }
            }
//            try {
//                File f = new File("E:\\tmp\\wordTest\\aaa-" + Math.random() + ".docx");
//                if (f.exists() == false) {
//                    f.createNewFile();
//                }
//                FileOutputStream out = new FileOutputStream(f);
//                doc.write(out);
//                out.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }

        private static void tableBorderStyle(XWPFTable table) {
            //表格属性
            CTTblPr tablePr = table.getCTTbl().addNewTblPr();
            //表格宽度
            CTTblWidth width = tablePr.addNewTblW();
            width.setW(BigInteger.valueOf(8000));
            //表格颜色
            CTTblBorders borders = table.getCTTbl().getTblPr().addNewTblBorders();
            //表格内部横向表格颜色
            CTBorder hBorder = borders.addNewInsideH();
            hBorder.setVal(STBorder.Enum.forString("single"));
            hBorder.setSz(new BigInteger("1"));
            hBorder.setColor("dddddd");
            //表格内部纵向表格颜色
            CTBorder vBorder = borders.addNewInsideV();
            vBorder.setVal(STBorder.Enum.forString("single"));
            vBorder.setSz(new BigInteger("1"));
            vBorder.setColor("dddddd");
            //表格最左边一条线的样式
            CTBorder lBorder = borders.addNewLeft();
            lBorder.setVal(STBorder.Enum.forString("single"));
            lBorder.setSz(new BigInteger("1"));
            lBorder.setColor("dddddd");
            //表格最左边一条线的样式
            CTBorder rBorder = borders.addNewRight();
            rBorder.setVal(STBorder.Enum.forString("single"));
            rBorder.setSz(new BigInteger("1"));
            rBorder.setColor("dddddd");
            //表格最上边一条线（顶部）的样式
            CTBorder tBorder = borders.addNewTop();
            tBorder.setVal(STBorder.Enum.forString("single"));
            tBorder.setSz(new BigInteger("1"));
            tBorder.setColor("dddddd");
            //表格最下边一条线（底部）的样式
            CTBorder bBorder = borders.addNewBottom();
            bBorder.setVal(STBorder.Enum.forString("single"));
            bBorder.setSz(new BigInteger("1"));
            bBorder.setColor("dddddd");
        }

        private static void tableTextStyle(List<XWPFTableCell> tableCells1, int index, String text) {
            tableTextStyle(tableCells1.get(index), text);
        }

        private static void tableTextStyle(XWPFTableCell tableCell, String text) {
            XWPFParagraph p0 = tableCell.addParagraph();
            tableCell.setParagraph(p0);
            XWPFRun r0 = p0.createRun();
            // 设置字体是否加粗
//        r0.setBold(true);
            r0.setFontSize(12);
            // 设置使用何种字体
            r0.setFontFamily("Helvetica Neue");
            // 设置上下两行之间的间距
            r0.setTextPosition(12);
            r0.setColor("333333");
            r0.setText(text);
        }

        private static void titleStyle(XWPFDocument doc, String title) {
            XWPFParagraph p1 = doc.createParagraph();
            // 设置字体对齐方式
            p1.setAlignment(ParagraphAlignment.CENTER);
            p1.setVerticalAlignment(TextAlignment.TOP);
            // 第一页要使用p1所定义的属性
            XWPFRun r1 = p1.createRun();
            // 设置字体是否加粗
            r1.setBold(true);
            r1.setFontSize(20);
            // 设置使用何种字体
            r1.setFontFamily("Courier");
            // 设置上下两行之间的间距
            r1.setTextPosition(20);
            r1.setText(title);
        }
    }
}
