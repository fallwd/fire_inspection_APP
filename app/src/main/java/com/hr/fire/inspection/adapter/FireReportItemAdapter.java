package com.hr.fire.inspection.adapter;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.deepoove.poi.XWPFTemplate;
//import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import com.deepoove.poi.config.Configure;
//import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.data.style.Style;
import com.hr.fire.inspection.R;
import com.hr.fire.inspection.activity.FireReportActivity;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.service.ServiceFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class FireReportItemAdapter extends BaseAdapter {

    private List<String> stringArrayList;
    private List<Long> companyInfoId;
    private List<Date> checkDate;
    private final FireReportActivity mContext;
    private String set_company_name;
    private String set_oil_name;
    private String set_Platform_name;
    private List<HashMap> getallmessage;
//    private final List<ItemInfo> co2_arr = new ArrayList<>();
//    private String PotionBottle = "药剂瓶";
//    private List PotionBottle_data;
//    private String NitrogenCylinder = "氮气瓶";
//    private List NitrogenCylinder_data;
//    private String Heptafluoropropane = "七氟丙烷钢瓶";
//    private List Heptafluoropropane_data;
//    private String NitrogenDriveBottle = "氮气驱动瓶";
//    private List NitrogenDriveBottle_data;
//    private String annihilator = "灭火器";
//    private List annihilator_data;
//    private String SmokeDetector = "感烟探测器";
//    private List SmokeDetector_data;
//    private String HeatDetector = "感温探测器";
//    private List HeatDetector_data;
//    private String FlameDetector = "火焰探测器";
//    private List FlameDetector_data;
//    private String Manualalarmbutton = "手动报警按钮";
//    private List Manualalarmbutton_data;
//    private String TGAT = "可燃气体探测器";
//    private List TGAT_data;
//    private String Hydrogendetector = "氢气探测器";
//    private List Hydrogendetector_data;
//    private String hydrogensulfidedetector = "硫化氢探测器";
//    private List hydrogensulfidedetector_data;
//    private String COdetector = "CO探测器";
//    private List COdetector_data;
//    private String facp = "火灾报警控制器";
//    private List facp_data;
//    private String DrivingBottle = "驱动瓶";
//    private List DrivingBottle_data;
//    private String yaoji_bottle = "药剂瓶";
//    private List yaoji_data;
//    private String sea_system = "海水雨淋灭火系统";
//    private List sea_system_data;
//    private String fireHose = "消防软管";
//    private List fireHose_data;
//    private String FireMonitor = "消防炮";
//    private List FireMonitor_data;
//    private String DryPowderCans = "干粉罐";
//    private List DryPowderCans_data;
//    private String actnationBottle= "启动瓶";
//    private List actnationBottle_data;
//    private String SCBABottle= "SCBA气瓶";
//    private List SCBABottle_data;
//    private String EEBDBottle= "EEBD气瓶";
//    private List EEBDBottle_data;
//    private String CAFS_system= "泡沫灭火系统";
//    private List CAFS_system_data;


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
//            Log.d("dong", "拿到了数据==  " + sele);
            Toast.makeText(mContext, "正在生成报告，请稍后...", Toast.LENGTH_SHORT).show();
            // 获取后台参数
            getallmessage = ServiceFactory.getYearCheckService().getOutputItemData(companyInfoId.get(position), checkDate.get(position));

            // 获取ItemInfo对象
            final List<ItemInfo> date = (List) getallmessage.get(0).get("data");
                assert date != null;
            ItemInfo itemObj = date.get(0);
            // 高压二氧化碳灭火系统
            List<HashMap> co2_system = getallmessage.stream().filter(d -> "高压二氧化碳灭火系统".equals(d.get("systemName"))).collect(Collectors.toList());
            List<HashMap> PotionBottle_data = co2_system.stream().filter(d -> "药剂瓶".equals(d.get("tableName"))).collect(Collectors.toList());
            List<ItemInfo> co2_yjp_data  = (List<ItemInfo>) PotionBottle_data.get(0).get("data");
            List<HashMap> NitrogenCylinder_data= co2_system.stream().filter(d -> "氮气瓶".equals(d.get("tableName"))).collect(Collectors.toList());
            List<ItemInfo> co2_N2_data  = (List<ItemInfo>) NitrogenCylinder_data.get(0).get("data");
            //泡沫灭火系统
            List<HashMap> CAFS_system_data = getallmessage.stream().filter(d -> "泡沫灭火系统".equals(d.get("systemName"))).collect(Collectors.toList());
            // 七氟丙烷灭火系统
            List<HashMap> Heliotrope_data = getallmessage.stream().filter(d -> "七氟丙烷钢瓶".equals(d.get("tableName"))).collect(Collectors.toList());
            List<ItemInfo> Hf_data  = (List<ItemInfo>) Heliotrope_data.get(0).get("data");
            List<HashMap> NitrogenDriveBottle_data = getallmessage.stream().filter(d -> "氮气驱动瓶".equals(d.get("tableName"))).collect(Collectors.toList());
            List<ItemInfo> ND_data  = (List<ItemInfo>) NitrogenDriveBottle_data.get(0).get("data");
            // 固定式干粉灭火系统
            List<HashMap> DryPowderCans = getallmessage.stream().filter(d -> "干粉罐".equals(d.get("tableName"))).collect(Collectors.toList());
            List<ItemInfo> gf_data  = (List<ItemInfo>) DryPowderCans.get(0).get("data");
            List<HashMap> actuationBottle = getallmessage.stream().filter(d -> "启动瓶".equals(d.get("tableName"))).collect(Collectors.toList());
            // 厨房设备灭火装置
            List<HashMap> chafing_system = getallmessage.stream().filter(d -> "厨房设备灭火装置".equals(d.get("systemName"))).collect(Collectors.toList());
            List<HashMap> DrivingBottle = chafing_system.stream().filter(d -> "驱动瓶".equals(d.get("tableName"))).collect(Collectors.toList());
            List<ItemInfo> qd_data  = (List<ItemInfo>) DrivingBottle.get(0).get("data");
            List<HashMap> yogi = chafing_system.stream().filter(d -> "药剂瓶".equals(d.get("tableName"))).collect(Collectors.toList());
            List<ItemInfo> yjp_data  = (List<ItemInfo>) yogi.get(0).get("data");
            //消防水灭火系统
            List<HashMap> fireHose = getallmessage.stream().filter(d -> "消防软管".equals(d.get("tableName"))).collect(Collectors.toList());
            List<ItemInfo> fireHose_data  = (List<ItemInfo>) fireHose.get(0).get("data");
            List<HashMap> FireMonitor = getallmessage.stream().filter(d -> "消防炮".equals(d.get("tableName"))).collect(Collectors.toList());
            // 海水雨淋灭火系统
            List<HashMap> sea_system = getallmessage.stream().filter(d -> "海水雨淋灭火系统".equals(d.get("systemName"))).collect(Collectors.toList());
            // 消防员装备
            List<HashMap> EEBDBottle = getallmessage.stream().filter(d -> "EEBD气瓶".equals(d.get("tableName"))).collect(Collectors.toList());
            List<ItemInfo> EEBDBottle_data  = (List<ItemInfo>) EEBDBottle.get(0).get("data");
            // 火灾自动报警系统
            List<HashMap> SmokeDetector= getallmessage.stream().filter(d -> "感烟探测器".equals(d.get("tableName"))).collect(Collectors.toList());
            List<ItemInfo> SmokeDetector_data  = (List<ItemInfo>) SmokeDetector.get(0).get("data");
            List<HashMap> HeatDetector = getallmessage.stream().filter(d -> "感温探测器".equals(d.get("tableName"))).collect(Collectors.toList());
            List<ItemInfo> HeatDetector_data  = (List<ItemInfo>) HeatDetector.get(0).get("data");
            List<HashMap> FlameDetector= getallmessage.stream().filter(d -> "火焰探测器".equals(d.get("tableName"))).collect(Collectors.toList());
            List<ItemInfo> FlameDetector_data  = (List<ItemInfo>) FlameDetector.get(0).get("data");
            List<HashMap> Malformation= getallmessage.stream().filter(d -> "手动报警按钮".equals(d.get("tableName"))).collect(Collectors.toList());
            List<ItemInfo> Malformation_data  = (List<ItemInfo>) Malformation.get(0).get("data");
            List<HashMap> TAT= getallmessage.stream().filter(d -> "可燃气体探测器".equals(d.get("tableName"))).collect(Collectors.toList());
            List<ItemInfo> TGAT_data = (List<ItemInfo>) TAT.get(0).get("data");
            List<HashMap> hydrogenate= getallmessage.stream().filter(d -> "硫化氢探测器".equals(d.get("tableName"))).collect(Collectors.toList());
            List<ItemInfo> hydrogenate_data  = (List<ItemInfo>) hydrogenate.get(0).get("data");

            // 传入模板的数据
            Map<String, Object> tempdatas = new HashMap<String, Object>() {{
                put("name", stringArrayList.get(position));         // 名称
                put("Date", getDate(itemObj.getCheckDate()));       // 日期
                put("nextCheckTime", netCheckTime(itemObj.getCheckDate())); // 下次检验日期
                put("Facility_name", set_company_name);             // 设施名称Facility Name ->> 公司
                put("oil_name", set_oil_name);                      // 设施名称Facility Name ->> ** 油田
                put("platform", set_Platform_name); // 检验地点  ->  ** 平台
                put("co2_yjp_count", PotionBottle_data.get(0).get("count"));                // 高压二氧化碳灭火系统 药剂瓶数量Quantity×药剂瓶容积
                put("protectArea", PotionBottle_data.get(0).get("protectArea"));            // 高压二氧化碳灭火系统 覆盖保护区域
                put("co2_dq_count", NitrogenCylinder_data.get(0).get("count"));             // 高压二氧化碳灭火系统 氮气瓶数量Quantity×氮气瓶容积
                assert co2_yjp_data != null;
                put("co2_yjp_Rows", getCo2_yjp_table(co2_yjp_data));                        // 高压二氧化碳灭火系统 药剂瓶 CO2 Fire Extinguishing System
                assert co2_N2_data != null;
                put("co2_N2_Rows", getCo2_N2_table(co2_N2_data));                           // 高压二氧化碳灭火系统 氮气瓶  CO2 Fire Extinguishing System
                put("mhq_table",getMHQ_table());                                            // 灭火器系统
                put("mhprotectArea",CAFS_system_data.get(0).get("protectArea"));            // 七氟丙烷灭火器系统 覆盖保护区域
                put("HFCount",Heliotrope_data.get(0).get("count"));                 // 七氟丙烷灭火系统 气瓶数量Quantity×储气瓶容积
                put("NDCount",NitrogenDriveBottle_data.get(0).get("count"));                // 七氟丙烷灭火系统 气瓶数量Quantity×储气瓶容积
                assert Hf_data != null;
                put("HfRows",get_Hf_table(Hf_data));                                       // 七氟丙烷灭火系统  七氟丙烷钢瓶
                assert ND_data != null;
                put("NDRows",get_ND_table(ND_data));                                       // 七氟丙烷灭火系统  氮气驱动瓶
                put("ganfenCount", DryPowderCans.get(0).get("count"));                     // 干粉灭火系统 气瓶数量Quantity×储气瓶容积
                put("StartCount", actuationBottle.get(0).get("count"));                     // 干粉灭火系统 气瓶数量Quantity×驱动气瓶容积
                put("gfProtectArea", actuationBottle.get(0).get("protectArea"));            // 干粉灭火系统 保护区域
                assert gf_data != null;
                put("gfRows", get_gf_table(gf_data));                                        // 干粉灭火系统 干粉罐
                put("YJCount", yogi.get(0).get("count"));                                   // 厨房设备灭火装置 药剂瓶数量Quantity×容积
                put("QDCount", DrivingBottle.get(0).get("count"));                            // 厨房设备灭火装置 气瓶数量Quantity×驱动气瓶容积
                assert yjp_data != null;
                put("YJRows", get_champ_table(yjp_data));                                     //厨房设备灭火装置 药剂瓶
                assert qd_data != null;
                put("QDRows", get_chqdp_table(qd_data));                                       // 厨房设备灭火装置 驱动瓶
                put("FireMonitorCount", FireMonitor.get(0).get("count"));                      //消防水灭火系统  消火栓数量
                put("fireHoseCount", fireHose.get(0).get("count"));                             //消防水灭火系统  消防水软管站数量
                assert fireHose_data != null;
                put("firHRows", get_fireH_table(fireHose_data));                                //消防水灭火系统  消防水软管
                put("seasystemCount", sea_system.get(0).get("count"));                          // 海水雨淋灭火系统 雨淋阀数量
                put("seaSystemProtectArea", sea_system.get(0).get("protectArea"));              // 海水雨淋灭火系统 覆盖保护区域
                assert EEBDBottle_data != null;
                put("EEBDBottleRows", get_EEBDBottle_table(EEBDBottle_data));                   // 消防员装备和EEBD Marine Fireman's Outfit & EEBD
                assert SmokeDetector_data != null;
                put("SmokeDRows", get_SmokeDete_table(SmokeDetector_data));                     // 感烟探测器Smoke
                assert HeatDetector_data != null;
                put("HeatDRows", get_HeatDetector_table(HeatDetector_data));                      // 感温探测器
                assert FlameDetector_data != null;
                put("FlameDeRows", get_FlameDetector_table(FlameDetector_data));                   // 火焰探测器
                assert TGAT_data != null;
                put("TGATRows", get_TGA_table(TGAT_data));                                          // 可燃气体探测器
                assert hydrogenate_data != null;
                put("hydrogensulRows", get_lhq_table(hydrogenate_data));                // 硫化氢探测器
                assert Malformation_data != null;
                put("ManuabtnRows", get_Manuabtn_table(Malformation_data));                    //手动报警按钮

            }
            };
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
        checkDate = new ArrayList<>();
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
        Button viewBtn;
        TextView sayTextView;
    }

    // 获取高压二氧化碳灭火系统表格参数->  药剂瓶
    private List<RowRenderData> getCo2_yjp_table(List<ItemInfo> data) {
        Style headTextStyle = new Style();
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(9);
        headTextStyle.setColor("000000");
        List<RowRenderData> RowArr = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ItemInfo itemObj_cell = data.get(i);
            RowRenderData RowCell = RowRenderData.build(
                    new TextRenderData(itemObj_cell.getLabelNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getWeight(), headTextStyle),
                    new TextRenderData(itemObj_cell.getGoodsWeight(), headTextStyle),
                    new TextRenderData(itemObj_cell.getVolume(), headTextStyle),
                    new TextRenderData(itemObj_cell.getProdFactory(), headTextStyle),
                    new TextRenderData(getDate(itemObj_cell.getProdDate()), headTextStyle),
                    new TextRenderData(itemObj_cell.getTaskNumber(), headTextStyle),
                    new TextRenderData(itemObj_cell.getIsPass(), headTextStyle)
            );
            RowArr.add(RowCell);
        }
        return RowArr;
    }

    // 获取高压二氧化碳灭火系统表格参数->  氮气瓶
    private List<RowRenderData> getCo2_N2_table(List<ItemInfo> data) {
        Style headTextStyle = new Style();
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(9);
        headTextStyle.setColor("000000");
        List<RowRenderData> RowArr = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ItemInfo itemObj_cell = data.get(i);
            RowRenderData RowCell = RowRenderData.build(
                    new TextRenderData(itemObj_cell.getLabelNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getPressure(), headTextStyle),
                    new TextRenderData(itemObj_cell.getVolume(), headTextStyle),
                    new TextRenderData(itemObj_cell.getProdFactory(), headTextStyle),
                    new TextRenderData(getDate(itemObj_cell.getProdDate()), headTextStyle),
                    new TextRenderData(itemObj_cell.getTaskNumber(), headTextStyle),
                    new TextRenderData(itemObj_cell.getIsPass(), headTextStyle)
            );
            RowArr.add(RowCell);
        }
        return RowArr;
    }

    // 灭火器系统
    private List<RowRenderData> getMHQ_table(){
        // 灭火器
        List<ItemInfo> data = (List<ItemInfo>) getallmessage.stream().filter(d -> "灭火器".equals(d.get("tableName"))).collect(Collectors.toList()).get(0).get("data");
        Style headTextStyle = new Style();
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(9);
        headTextStyle.setColor("000000");
        List<RowRenderData> RowArr = new ArrayList<>();
        assert data != null;
        for (int i = 0; i < data.size(); i++) {
            ItemInfo itemObj_cell = data.get(i);
            RowRenderData RowCell = RowRenderData.build(
                    new TextRenderData(itemObj_cell.getLabelNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getTypeNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getDeviceType(), headTextStyle),
                    new TextRenderData(itemObj_cell.getProdFactory(), headTextStyle),
                    new TextRenderData(getDate(itemObj_cell.getProdDate()), headTextStyle),
                    new TextRenderData(itemObj_cell.getLevel(), headTextStyle),
                    new TextRenderData(itemObj_cell.getTaskNumber(), headTextStyle),
                    new TextRenderData(itemObj_cell.getIsPass(), headTextStyle)
            );
            RowArr.add(RowCell);
        }
        return RowArr;
    }

    // 七氟丙烷灭火系统->  七氟丙烷钢瓶
    private List<RowRenderData> get_Hf_table(List<ItemInfo> data) {
        Style headTextStyle = new Style();
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(9);
        headTextStyle.setColor("000000");
        List<RowRenderData> RowArr = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ItemInfo itemObj_cell = data.get(i);
            RowRenderData RowCell = RowRenderData.build(
                    new TextRenderData(itemObj_cell.getLabelNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getPressure(), headTextStyle),
                    new TextRenderData(itemObj_cell.getGoodsWeight(), headTextStyle),
                    new TextRenderData(itemObj_cell.getVolume(), headTextStyle),
                    new TextRenderData(itemObj_cell.getProdFactory(), headTextStyle),
                    new TextRenderData(getDate(itemObj_cell.getProdDate()), headTextStyle),
                    new TextRenderData(itemObj_cell.getTaskNumber(), headTextStyle),
                    new TextRenderData(itemObj_cell.getIsPass(), headTextStyle)
            );
            RowArr.add(RowCell);
        }
        return RowArr;
    }

    private List<RowRenderData> get_ND_table(List<ItemInfo> data) {
        Style headTextStyle = new Style();
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(9);
        headTextStyle.setColor("000000");
        List<RowRenderData> RowArr = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ItemInfo itemObj_cell = data.get(i);
            RowRenderData RowCell = RowRenderData.build(
                    new TextRenderData(itemObj_cell.getLabelNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getPressure(), headTextStyle),
                    new TextRenderData(itemObj_cell.getVolume(), headTextStyle),
                    new TextRenderData(itemObj_cell.getProdFactory(), headTextStyle),
                    new TextRenderData(getDate(itemObj_cell.getProdDate()), headTextStyle),
                    new TextRenderData(itemObj_cell.getTaskNumber(), headTextStyle),
                    new TextRenderData(itemObj_cell.getIsPass(), headTextStyle)
            );
            RowArr.add(RowCell);
        }
        return RowArr;
    }

    // 干粉灭火系统 -》干粉罐
    private List<RowRenderData> get_gf_table(List<ItemInfo> data) {
        Style headTextStyle = new Style();
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(9);
        headTextStyle.setColor("000000");
        List<RowRenderData> RowArr = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ItemInfo itemObj_cell = data.get(i);
            RowRenderData RowCell = RowRenderData.build(
                    new TextRenderData(itemObj_cell.getLabelNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getTypeConformity(), headTextStyle),
                    new TextRenderData(itemObj_cell.getNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getVolume(), headTextStyle),
                    new TextRenderData(getDate(itemObj_cell.getFillingDate()), headTextStyle),
                    new TextRenderData(getDate(itemObj_cell.getProdDate()), headTextStyle),
                    new TextRenderData(itemObj_cell.getProdFactory(), headTextStyle),
                    new TextRenderData(itemObj_cell.getTaskNumber(), headTextStyle),
                    new TextRenderData(itemObj_cell.getIsPass(), headTextStyle)
            );
            RowArr.add(RowCell);
        }
        return RowArr;
    }

    //厨房设备灭火装置 药剂瓶
    private List<RowRenderData> get_champ_table(List<ItemInfo> data) {
        Style headTextStyle = new Style();
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(9);
        headTextStyle.setColor("000000");
        List<RowRenderData> RowArr = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ItemInfo itemObj_cell = data.get(i);
            RowRenderData RowCell = RowRenderData.build(
                    new TextRenderData(itemObj_cell.getLabelNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getVolume(), headTextStyle),
                    new TextRenderData(itemObj_cell.getProdFactory(), headTextStyle),
                    new TextRenderData(getDate(itemObj_cell.getProdDate()), headTextStyle),
                    new TextRenderData(getDate(itemObj_cell.getFillingDate()), headTextStyle),
                    new TextRenderData(itemObj_cell.getTaskNumber(), headTextStyle),
                    new TextRenderData(itemObj_cell.getIsPass(), headTextStyle)
            );
            RowArr.add(RowCell);
        }
        return RowArr;
    }

    private List<RowRenderData> get_chqdp_table(List<ItemInfo> data) {
        Style headTextStyle = new Style();
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(9);
        headTextStyle.setColor("000000");
        List<RowRenderData> RowArr = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ItemInfo itemObj_cell = data.get(i);
            RowRenderData RowCell = RowRenderData.build(
                    new TextRenderData(itemObj_cell.getLabelNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getPressure(), headTextStyle),
                    new TextRenderData(itemObj_cell.getVolume(), headTextStyle),
                    new TextRenderData(itemObj_cell.getProdFactory(), headTextStyle),
                    new TextRenderData(getDate(itemObj_cell.getProdDate()), headTextStyle),
                    new TextRenderData(itemObj_cell.getTaskNumber(), headTextStyle),
                    new TextRenderData(itemObj_cell.getIsPass(), headTextStyle)
            );
            RowArr.add(RowCell);
        }
        return RowArr;
    }

    // 消防水灭火系统 消防水系统
    private List<RowRenderData> get_fireH_table(List<ItemInfo> data) {
        Style headTextStyle = new Style();
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(9);
        headTextStyle.setColor("000000");
        List<RowRenderData> RowArr = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ItemInfo itemObj_cell = data.get(i);
            RowRenderData RowCell = RowRenderData.build(
                    new TextRenderData(itemObj_cell.getNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getTypeNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getProdFactory(), headTextStyle),
                    new TextRenderData(itemObj_cell.getDeviceType(), headTextStyle),
                    new TextRenderData(itemObj_cell.getTaskNumber(), headTextStyle),
                    new TextRenderData(itemObj_cell.getIsPass(), headTextStyle)
            );
            RowArr.add(RowCell);
        }
        return RowArr;
    }

    //消防员装备和EEBD Marine Fireman's Outfit & EEBD
    private List<RowRenderData> get_EEBDBottle_table(List<ItemInfo> data) {
        Style headTextStyle = new Style();
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(9);
        headTextStyle.setColor("000000");
        List<RowRenderData> RowArr = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ItemInfo itemObj_cell = data.get(i);
            RowRenderData RowCell = RowRenderData.build(
                    new TextRenderData(itemObj_cell.getLabelNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getPressure(), headTextStyle),
                    new TextRenderData(itemObj_cell.getVolume(), headTextStyle),
                    new TextRenderData(itemObj_cell.getProdFactory(), headTextStyle),
                    new TextRenderData(getDate(itemObj_cell.getProdDate()), headTextStyle),
                    new TextRenderData(itemObj_cell.getTaskNumber(), headTextStyle),
                    new TextRenderData(itemObj_cell.getIsPass(), headTextStyle)
            );
            RowArr.add(RowCell);
        }
        return RowArr;
    }

    // 感烟探测器Smoke detectors
    private List<RowRenderData> get_SmokeDete_table(List<ItemInfo> data) {
        Style headTextStyle = new Style();
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(8);
        headTextStyle.setColor("000000");
        List<RowRenderData> RowArr = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ItemInfo itemObj_cell = data.get(i);
            RowRenderData RowCell = RowRenderData.build(
                    new TextRenderData(data.size() - i +"", headTextStyle),
                    new TextRenderData("感烟探测器", headTextStyle),
                    new TextRenderData(itemObj_cell.getProdFactory(), headTextStyle),
                    new TextRenderData(itemObj_cell.getTypeNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getAlarmFunction(), headTextStyle),
                    new TextRenderData(itemObj_cell.getResponseTime(), headTextStyle),
                    new TextRenderData("", headTextStyle)
            );
            RowArr.add(RowCell);
        }
        return RowArr;
    }

    //感温探测器
    private List<RowRenderData> get_HeatDetector_table(List<ItemInfo> data) {
        Style headTextStyle = new Style();
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(8);
        headTextStyle.setColor("000000");
        List<RowRenderData> RowArr = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ItemInfo itemObj_cell = data.get(i);
            RowRenderData RowCell = RowRenderData.build(
                    new TextRenderData(data.size() - i +"", headTextStyle),
                    new TextRenderData("感温探测器", headTextStyle),
                    new TextRenderData(itemObj_cell.getProdFactory(), headTextStyle),
                    new TextRenderData(itemObj_cell.getTypeNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getAlarmFunction(), headTextStyle),
                    new TextRenderData(itemObj_cell.getResponseTime(), headTextStyle),
                    new TextRenderData("", headTextStyle)
            );
            RowArr.add(RowCell);
        }
        return RowArr;
    }

    //火焰探测器
    private List<RowRenderData> get_FlameDetector_table(List<ItemInfo> data) {
        Style headTextStyle = new Style();
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(8);
        headTextStyle.setColor("000000");
        List<RowRenderData> RowArr = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ItemInfo itemObj_cell = data.get(i);
            RowRenderData RowCell = RowRenderData.build(
                    new TextRenderData(data.size() - i + "", headTextStyle),
                    new TextRenderData("火焰探测器", headTextStyle),
                    new TextRenderData(itemObj_cell.getProdFactory(), headTextStyle),
                    new TextRenderData(itemObj_cell.getTypeNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getAlarmFunction(), headTextStyle),
                    new TextRenderData(itemObj_cell.getResponseTime(), headTextStyle),
                    new TextRenderData("", headTextStyle)
            );
            RowArr.add(RowCell);
        }
        return RowArr;
    }

    // 可燃气体探测器
    private List<RowRenderData> get_TGA_table(List<ItemInfo> data) {
        Style headTextStyle = new Style();
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(8);
        headTextStyle.setColor("000000");
        List<RowRenderData> RowArr = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ItemInfo itemObj_cell = data.get(i);
            RowRenderData RowCell = RowRenderData.build(
                    new TextRenderData(itemObj_cell.getProdFactory(), headTextStyle),
                    new TextRenderData(itemObj_cell.getTypeNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getNo(), headTextStyle),
                    new TextRenderData("", headTextStyle),// 量程
                    new TextRenderData(itemObj_cell.getAlarmFunction(), headTextStyle),
                    new TextRenderData(itemObj_cell.getSetAlarm25(), headTextStyle),
                    new TextRenderData(itemObj_cell.getSetAlarm50(), headTextStyle),
                    new TextRenderData(itemObj_cell.getTestAlarm25(), headTextStyle),
                    new TextRenderData(itemObj_cell.getTestAlarm25(), headTextStyle),
                    new TextRenderData(itemObj_cell.getTestAlarm50(), headTextStyle),
                    new TextRenderData(itemObj_cell.getTestAlarm50(), headTextStyle),
                    new TextRenderData(itemObj_cell.getResponseTime(), headTextStyle)
            );
            RowArr.add(RowCell);
        }
        return RowArr;
    }

    //硫化氢探测器
    private List<RowRenderData> get_lhq_table(List<ItemInfo> data) {
        Style headTextStyle = new Style();
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(8);
        headTextStyle.setColor("000000");
        List<RowRenderData> RowArr = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ItemInfo itemObj_cell = data.get(i);
            RowRenderData RowCell = RowRenderData.build(
                    new TextRenderData(data.size() - i + "", headTextStyle),
                    new TextRenderData("硫化氢探测器", headTextStyle),
                    new TextRenderData(itemObj_cell.getProdFactory(), headTextStyle),
                    new TextRenderData(itemObj_cell.getTypeNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getAlarmFunction(), headTextStyle),
                    new TextRenderData(itemObj_cell.getSetAlarm25(), headTextStyle),
                    new TextRenderData(itemObj_cell.getSetAlarm50(), headTextStyle),
                    new TextRenderData(itemObj_cell.getTestAlarm25(), headTextStyle),
                    new TextRenderData(itemObj_cell.getTestAlarm25(), headTextStyle),
                    new TextRenderData(itemObj_cell.getResponseTime(), headTextStyle),
                    new TextRenderData("", headTextStyle)
            );
            RowArr.add(RowCell);
        }
        return RowArr;
    }

    //手动火灾报警按钮
    private List<RowRenderData> get_Manuabtn_table(List<ItemInfo> data) {
        Style headTextStyle = new Style();
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(8);
        headTextStyle.setColor("000000");
        List<RowRenderData> RowArr = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ItemInfo itemObj_cell = data.get(i);
            RowRenderData RowCell = RowRenderData.build(
                    new TextRenderData(data.size() - i + "", headTextStyle),
                    new TextRenderData("手动火灾报警按钮", headTextStyle),
                    new TextRenderData(itemObj_cell.getProdFactory(), headTextStyle),
                    new TextRenderData(itemObj_cell.getTypeNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getNo(), headTextStyle),
                    new TextRenderData(itemObj_cell.getAlarmFunction(), headTextStyle),
                    new TextRenderData(itemObj_cell.getResponseTime(), headTextStyle),
                    new TextRenderData("", headTextStyle)
            );
            RowArr.add(RowCell);
        }
        return RowArr;
    }
    // 获取各表的参数，报告没问题可删除
    //    private void initSystemData(){
//        // 根据表明筛选数据
//        Log.d("getallmessageSize---", getallmessage.size()+"");
//        // 高压二氧化碳灭火系统
//        List<HashMap> co2_system = getallmessage.stream().filter(d -> "高压二氧化碳灭火系统".equals(d.get("systemName"))).collect(Collectors.toList());
//        Log.d("高压二氧化碳灭火系统", co2_system+"");
//        PotionBottle_data = co2_system.stream().filter(d -> "药剂瓶".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("药剂瓶", PotionBottle_data+"");
//        NitrogenCylinder_data= co2_system.stream().filter(d -> "氮气瓶".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("氮气瓶", NitrogenCylinder_data+"");
//
//        // 七氟丙烷灭火系统
//        Heptafluoropropane_data = getallmessage.stream().filter(d -> "七氟丙烷钢瓶".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("七氟丙烷钢瓶", Heptafluoropropane_data+"");
//
//        NitrogenDriveBottle_data = getallmessage.stream().filter(d -> "氮气驱动瓶".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("氮气驱动瓶", NitrogenDriveBottle_data+"");
//
//        // 灭火器
//        annihilator_data = getallmessage.stream().filter(d -> "灭火器".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("灭火器", annihilator_data+"");
//
//        // 火灾自动报警系统
//        SmokeDetector_data= getallmessage.stream().filter(d -> "感烟探测器".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("感烟探测器", SmokeDetector_data+"");
//        HeatDetector_data = getallmessage.stream().filter(d -> "感温探测器".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("感温探测器", HeatDetector_data+"");
//        FlameDetector_data= getallmessage.stream().filter(d -> "火焰探测器".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("火焰探测器", FlameDetector_data+"");
//        Manualalarmbutton_data= getallmessage.stream().filter(d -> "手动报警按钮".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("手动报警按钮", Manualalarmbutton_data+"");
//        TGAT_data= getallmessage.stream().filter(d -> "可燃气体探测器".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("可燃气体探测器",  TGAT_data+"");
//        Hydrogendetector_data= getallmessage.stream().filter(d -> "氢气探测器".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("氢气探测器",  Hydrogendetector_data+"");
//        hydrogensulfidedetector_data= getallmessage.stream().filter(d -> "硫化氢探测器".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("硫化氢探测器",  hydrogensulfidedetector_data+"");
//        COdetector_data = getallmessage.stream().filter(d -> "CO探测器".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("CO探测器",  COdetector_data+"");
//        facp_data = getallmessage.stream().filter(d -> "火灾报警控制器".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("火灾报警控制器",  facp_data+"");
//
//        // 厨房设备灭火装置
//        List<HashMap> chufang_system = getallmessage.stream().filter(d -> "厨房设备灭火装置".equals(d.get("systemName"))).collect(Collectors.toList());
//        Log.d("厨房设备灭火装置", chufang_system+"");
//        DrivingBottle_data = chufang_system.stream().filter(d -> "驱动瓶".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("驱动瓶",  DrivingBottle_data+"");
//        yaoji_data = chufang_system.stream().filter(d -> "药剂瓶".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("药剂瓶",  yaoji_data+"");
//
//        // 海水雨淋灭火系统
//        sea_system_data = getallmessage.stream().filter(d -> "海水雨淋灭火系统".equals(d.get("systemName"))).collect(Collectors.toList());
//        Log.d("厨房设备灭火装置", sea_system_data+"");
//
//        //消防水灭火系统
//        fireHose_data = getallmessage.stream().filter(d -> "消防软管".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("消防软管",  fireHose_data+"");
//        FireMonitor_data = getallmessage.stream().filter(d -> "消防炮".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("消防软管",  FireMonitor_data+"");
//        // 固定式干粉灭火系统
//        DryPowderCans_data = getallmessage.stream().filter(d -> "干粉罐".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("干粉罐",  DryPowderCans_data+"");
//        actnationBottle_data = getallmessage.stream().filter(d -> "启动瓶".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("启动瓶",  actnationBottle_data+"");
//
//        // 消防员装备
//        SCBABottle_data = getallmessage.stream().filter(d -> "SCBA气瓶".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("SCBA气瓶",  SCBABottle_data+"");
//        EEBDBottle_data = getallmessage.stream().filter(d -> "EEBD气瓶".equals(d.get("tableName"))).collect(Collectors.toList());
//        Log.d("EEBD气瓶",  EEBDBottle_data+"");
//
//        // 泡沫灭火系统
//        CAFS_system_data = getallmessage.stream().filter(d -> "泡沫灭火系统".equals(d.get("systemName"))).collect(Collectors.toList());
//        Log.d("泡沫灭火系统", CAFS_system_data+"");
//    }

    // 生成报告
    private void initWordTem(String itemCon, Map<String, Object> tempdatas) throws IOException {
        System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");

        InputStream open = mContext.getAssets().open("In_template.docx");
        Configure configs=Configure.createDefault();
        configs.customPolicy("co2_yjp_Rows", new DetailTablePolicy());
        configs.customPolicy("mhq_table", new MHQDetailTablePolicy());
        configs.customPolicy("co2_N2_Rows", new N2DetailTablePolicy());
        configs.customPolicy("HfRows", new HfDetailTablePolicy());
        configs.customPolicy("NDRows", new NDDetailTablePolicy());
        configs.customPolicy("gfRows", new gfDetailTablePolicy());
        configs.customPolicy("YJRows", new yjpDetailTablePolicy());
        configs.customPolicy("QDRows", new QDDetailTablePolicy());
        configs.customPolicy("firHRows", new FireHoseDetailTablePolicy());
        configs.customPolicy("EEBDBottleRows", new EEBDDetailTablePolicy());
        configs.customPolicy("SmokeDRows", new SmokeDetailTablePolicy());
        configs.customPolicy("HeatDRows", new HeatDDetailTablePolicy());
        configs.customPolicy("FlameDeRows", new FlameDDetailTablePolicy());
        configs.customPolicy("TGATRows", new TGADetailTablePolicy());
        configs.customPolicy("hydrogensulRows", new lhqDDetailTablePolicy());
        configs.customPolicy("ManuabtnRows", new MabtnDDetailTablePolicy());


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
        calendar.setTime(checkDate);
        calendar.add(Calendar.YEAR, +1);
        calendar.add(Calendar.DATE, -1);//减1天
        checkDate = calendar.getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(checkDate);
    }

    /**
     * 获取手机时间  年/月/日
     *
     * @param date
     * @return*/

    private String getDate(Date date) {
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            return dateFormat.format(date);
        }catch (Exception e) {
            return null;
        }
    }
}
