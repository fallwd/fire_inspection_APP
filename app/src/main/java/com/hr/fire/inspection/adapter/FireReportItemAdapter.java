package com.hr.fire.inspection.adapter;

import android.annotation.SuppressLint;
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
//import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import com.deepoove.poi.config.Configure;
//import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.data.style.TableStyle;
import com.hr.fire.inspection.R;
import com.hr.fire.inspection.activity.FireReportActivity;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.service.ServiceFactory;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FireReportItemAdapter extends BaseAdapter {

    private List<String> stringArrayList;
    private List<Long> companyInfoId;
    private List<Date> checkDate;
    private final FireReportActivity mContext;
    private String sele;
    private String set_company_name;
    private String set_oil_name;
    private String set_Platform_name;

    // ————————————————————————————————————————————————————————
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
        holder.viewBtn.setOnClickListener(v -> {
            Log.d("dong", "拿到了数据==  " + sele);

            // 获取后台参数

            final List<HashMap> getallmessage = ServiceFactory.getYearCheckService().getOutputItemData(companyInfoId.get(position), checkDate.get(position));

            // 获取第一个系统
            Log.d("getallmessage", String.valueOf(getallmessage.get(0)));
            // 获取ItemInfo对象
            final List<ItemInfo> date = (List) getallmessage.get(0).get("data");
                assert date != null;
                Log.d("getallmessage", String.valueOf(date.get(0)));
            ItemInfo itemObj = date.get(0);
            // 获取时间
            final Date checkDate = itemObj.getCheckDate();
                Log.d("getallmessage", checkDate+"");

            final List<ItemInfo> co2_arr = new ArrayList<>();
            // 循环遍历数据库返回值 分配每个系统的参数 插入到每个系统的表中
            for (int i = 0; i < getallmessage.size() ; i++) {
                String systemName = (String) getallmessage.get(i).get("systemName"); // 获取每个表的名称
                final List getItemInfo = (List) getallmessage.get(i).get("data");
                assert getItemInfo != null;
                for (int j = 0; j < getItemInfo.size() ; j++) {
                    assert systemName != null;
                    ItemInfo itemObj1 = (ItemInfo) getItemInfo.get(j);
                    if ("高压二氧化碳灭火系统".equals(systemName)) {
                        co2_arr.add(itemObj1);
                    }
                }
            }

            Style headTextStyle = new Style();
            headTextStyle.setFontFamily("Hei");
            headTextStyle.setFontSize(9);
            headTextStyle.setColor("000000");
            List RowArr=new ArrayList();
                for (int i = 0; i < co2_arr.size() ; i++) {
                    ItemInfo itemObj_cell = co2_arr.get(i);
                    RowRenderData RowCell = RowRenderData.build(
                            new TextRenderData(itemObj_cell.getLabelNo(),headTextStyle),
                            new TextRenderData(itemObj_cell.getNo(),headTextStyle),
                            new TextRenderData(itemObj_cell.getWeight(),headTextStyle),
                            new TextRenderData(itemObj_cell.getGoodsWeight(),headTextStyle),
                            new TextRenderData(itemObj_cell.getVolume(),headTextStyle),
                            new TextRenderData(itemObj_cell.getProdFactory(),headTextStyle),
                            new TextRenderData(getDate(itemObj_cell.getProdDate()),headTextStyle),
                            new TextRenderData(itemObj_cell.getTaskNumber(),headTextStyle),
                            new TextRenderData(itemObj_cell.getIsPass(),headTextStyle)
                    );
                    RowArr.add(RowCell);
                }

//                Log.d("模板字符", String.valueOf(headArr));
            // 传入模板的数据
            Map<String, Object> tempdatas = new HashMap<String, Object>() {{
                put("name", stringArrayList.get(position)); // 名称
                put("Data", getDate(checkDate)); // 日期
                put("nextCheckTime", netCheckTime(checkDate)); // 下次检验日期
                put("Facility_name", set_company_name); // 设施名称Facility Name ->> 公司
                put("oil_name", set_oil_name); // 设施名称Facility Name ->> ** 油田
                put("platform", set_Platform_name); // 检验地点  ->  ** 平台
                put("protectArea", getallmessage.get(0).get("protectArea")); // 保护区域 protectArea
                put("Data", getDate(checkDate)); // 日期
                // 高压二氧化碳灭火系统 CO2 Fire Extinguishing System
                put("co2_Rows", RowArr);
            }};
            try {
                initWordTem(stringArrayList.get(position),tempdatas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return convertView;
    }

    public void setData(List<HashMap> mapList) {
        stringArrayList = new ArrayList<>();
        companyInfoId = new ArrayList<>();
        checkDate = new ArrayList<Date>();
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
    private void initWordTem(String itemCon, Map<String, Object> tempdatas) throws IOException {
        Log.d("文件名称", String.valueOf(tempdatas));
        System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");

        InputStream open = mContext.getAssets().open("In_template.docx");
        Configure configs=Configure.createDefault();
        configs.customPolicy("co2_Rows",  new DetailTablePolicy());

        XWPFTemplate template = XWPFTemplate.compile(open,configs).render(tempdatas);
        try {
            FileOutputStream out;
            String path = Environment.getExternalStorageDirectory().getPath();
            File file = new File(path + "/" + itemCon + ".docx");
            Log.d("生成的文件路径名：", String.valueOf(file));
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String sim = dateFormat.format(checkDate);
        Log.i("md", "推迟的时间为： " + sim);
        return sim;
    }

    /**
     * 获取手机时间  年/月/日
     *
     * @param date
     * @return*/

    private String getDate(Date date) {
//        Date date = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        //        Log.i("md", "时间sim为： "+sim);
        return dateFormat.format(date);
    }

    /**
     * 为表格插入数据，行数不够添加新行
     *
     * @param table     需要插入数据的表格
     * @param tableList 插入数据集合
     */
    public static void insertTable(XWPFTable table, List<String[]> tableList) {
        Log.d("表格数据", String.valueOf(tableList));
        //table.addNewRowBetween 没实现，官网文档也说明，只有函数名，但没具体实现，但很多文章还介绍如何使用这个函数，真是害人
        //table.insertNewTableRow 本文用这个可以，但是要创建 cell，否则不显示数据
        //table.addRow() 在表格最后加一行
        // table.addRow(XWPFTableRow row, int pos) 没试过，你可以试试。
        //table.createRow() 在表格最后一加行

        for (int i = 0; i < tableList.size(); i++) {//遍历要添加的数据的list
            XWPFTableRow newRow = table.insertNewTableRow(i + 1);//为表格添加行
            String[] strings = tableList.get(i);//获取list中的字符串数组
            for (int j = 0; j < strings.length; j++) {//遍历list中的字符串数组
                String strings1 = strings[j];
                newRow.createCell();//在新增的行上面创建cell
                newRow.getCell(j).setText(strings1);//给每个cell赋值。
            }
        }
    }
}
