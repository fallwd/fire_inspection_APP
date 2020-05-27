package com.hr.fire.inspection.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
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
import androidx.core.content.FileProvider;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.data.style.Style;
import com.hr.fire.inspection.R;
import com.hr.fire.inspection.activity.FireReportActivity;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.service.ServiceFactory;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.N)
public class FireReportItemAdapter extends BaseAdapter {

    private List<String> stringArrayList;
    private List<Long> companyInfoId;
    private List<Date> checkDate;
    private final FireReportActivity mContext;
    private String set_company_name;
    private String set_oil_name;
    private String set_Platform_name;
    private List<HashMap> getallmessage;
    private String fileName;

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

        ViewHolder holder;
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
            fileName = stringArrayList.get(position);
            String year = DateFormatUtils.format(checkDate.get(position),"yyyy");

            getallmessage = ServiceFactory.getYearCheckService().getOutputItemData(companyInfoId.get(position), year);// 获取后台参数
            final List templateData = (List) getallmessage.get(0).get("data"); // 获取ItemInfo对象
                assert templateData != null;
            try {
                new MergeDoc().initMerge(templateData, position); // 模板合并
            } catch (Exception e) {
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

    public void refresh(List<String> arr) {
        this.stringArrayList = arr;
        Log.d("更新报告列表", this.stringArrayList+ "");
    }

    static class ViewHolder {
        Button viewBtn;
        TextView sayTextView;
    }
    /**
     * @author: 吴伟
     *
     * @Date: 2020/5/21
     *
     * @name: 多个word文件合并，采用poi实现,兼容图片的迁移
     *
     * @Description: docx模板合并渲染
     */
    private class MergeDoc {
        private void initMerge(List<ItemInfo> data, int position) throws Exception {
            System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
            System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
            System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");
            Map<String, Object> templateData = initTemplateData(data, position); // 初始化模板数据
            Log.d("templateData-------", templateData+"");
            new systemTableIsNull().initTemplate(templateData); // 初始化模板
            renderTemplate(templateData);
        }
        // 模板合并
        private void appendBody(XWPFDocument src, XWPFDocument append) throws Exception {
            CTBody src1Body = src.getDocument().getBody();
            CTBody src2Body = append.getDocument().getBody();

            List<XWPFPictureData> allPictures = append.getAllPictures();
            // 记录图片合并前及合并后的ID
            HashMap map = new HashMap();
            for (XWPFPictureData picture : allPictures) {
                String before = append.getRelationId(picture);
                //将原文档中的图片加入到目标文档中
                String after = src.addPictureData(picture.getData(), Document.PICTURE_TYPE_PNG);
                map.put(before, after);
            }
            appendBody(src1Body, src2Body,map);
        }
        private void appendBody(CTBody src, CTBody append,Map<String,String> map) throws Exception {
            XmlOptions optionsOuter = new XmlOptions();
            optionsOuter.setSaveOuter();
            String appendString = append.xmlText(optionsOuter);

            String srcString = src.xmlText();
            String prefix = srcString.substring(0,srcString.indexOf(">")+1);
            String mainPart = srcString.substring(srcString.indexOf(">")+1,srcString.lastIndexOf("<"));
            String sufix = srcString.substring( srcString.lastIndexOf("<") );
            String addPart = appendString.substring(appendString.indexOf(">") + 1, appendString.lastIndexOf("<"));

            if (map != null && !map.isEmpty()) {
                //对xml字符串中图片ID进行替换
                for (Map.Entry<String, String> set : map.entrySet()) {
                    addPart = addPart.replace(set.getKey(), set.getValue());
                }
            }
            //将两个文档的xml内容进行拼接
            CTBody makeBody = CTBody.Factory.parse(prefix+mainPart+addPart+sufix);

            src.set(makeBody);
        }
        // 初始化模板参数
        @SuppressLint("LongLogTag")
        private Map<String, Object> initTemplateData(List<ItemInfo> date, int position){
            String finalTem_date = null;
            String finalTem_next_date = null;
            if (date.size()>0){
                ItemInfo itemObj = date.get(0);
                finalTem_date = getDate(itemObj.getCheckDate());
                finalTem_next_date = netCheckTime(itemObj.getCheckDate());
            }
            String systemName = stringArrayList.get(position);
            Log.d("getallmessage检查---------", getallmessage+"");
            // 高压二氧化碳灭火系统
            List<HashMap> co2_system = getallmessage.stream().filter(d -> "高压二氧化碳灭火系统".equals(d.get("systemName"))).collect(Collectors.toList());
            List<HashMap> PotionBottle_data = co2_system.stream().filter(d -> "药剂瓶".equals(d.get("tableName"))).collect(Collectors.toList());
            List co2_yjp_data  = (List) PotionBottle_data.get(0).get("data");
            List<HashMap> NitrogenCylinder_data= co2_system.stream().filter(d -> "氮气瓶".equals(d.get("tableName"))).collect(Collectors.toList());
            List co2_N2_data  = (List) NitrogenCylinder_data.get(0).get("data");
            //泡沫灭火系统
            List<HashMap> CAFS_system_data = getallmessage.stream().filter(d -> "泡沫灭火系统".equals(d.get("systemName"))).collect(Collectors.toList());
            // 七氟丙烷灭火系统
            List<HashMap> Heliotrope_data = getallmessage.stream().filter(d -> "七氟丙烷钢瓶".equals(d.get("tableName"))).collect(Collectors.toList());
            List Hf_data  = (List) Heliotrope_data.get(0).get("data");
            List<HashMap> NitrogenDriveBottle_data = getallmessage.stream().filter(d -> "氮气驱动瓶".equals(d.get("tableName"))).collect(Collectors.toList());
            List ND_data  = (List) NitrogenDriveBottle_data.get(0).get("data");
            // 固定式干粉灭火系统
            List<HashMap> DryPowderCans = getallmessage.stream().filter(d -> "干粉罐".equals(d.get("tableName"))).collect(Collectors.toList());
            List gf_data  = (List) DryPowderCans.get(0).get("data");
            List<HashMap> actuationBottle = getallmessage.stream().filter(d -> "启动瓶".equals(d.get("tableName"))).collect(Collectors.toList());
            // 厨房设备灭火装置
            List<HashMap> chafing_system = getallmessage.stream().filter(d -> "厨房设备灭火装置".equals(d.get("systemName"))).collect(Collectors.toList());
            List<HashMap> DrivingBottle = chafing_system.stream().filter(d -> "驱动瓶".equals(d.get("tableName"))).collect(Collectors.toList());
            List qd_data  = (List) DrivingBottle.get(0).get("data");
            List<HashMap> yogi = chafing_system.stream().filter(d -> "药剂瓶".equals(d.get("tableName"))).collect(Collectors.toList());
            List yjp_data  = (List) yogi.get(0).get("data");
            //消防水灭火系统
            List<HashMap> fireHose = getallmessage.stream().filter(d -> "消防软管".equals(d.get("tableName"))).collect(Collectors.toList());
            List fireHose_data  = (List) fireHose.get(0).get("data");
            List<HashMap> FireMonitor = getallmessage.stream().filter(d -> "消防炮".equals(d.get("tableName"))).collect(Collectors.toList());
            // 海水雨淋灭火系统
            List<HashMap> sea_system = getallmessage.stream().filter(d -> "海水雨淋灭火系统".equals(d.get("systemName"))).collect(Collectors.toList());
            // 消防员装备
            List<HashMap> EEBDBottle = getallmessage.stream().filter(d -> "EEBD气瓶".equals(d.get("tableName"))).collect(Collectors.toList());
            List EEBDBottle_data  = (List) EEBDBottle.get(0).get("data");
            // 火灾自动报警系统
            List<HashMap> SmokeDetector= getallmessage.stream().filter(d -> "感烟探测器".equals(d.get("tableName"))).collect(Collectors.toList());
            List SmokeDetector_data  = (List) SmokeDetector.get(0).get("data");
            List<HashMap> HeatDetector = getallmessage.stream().filter(d -> "感温探测器".equals(d.get("tableName"))).collect(Collectors.toList());
            List HeatDetector_data  = (List) HeatDetector.get(0).get("data");
            List<HashMap> FlameDetector= getallmessage.stream().filter(d -> "火焰探测器".equals(d.get("tableName"))).collect(Collectors.toList());
            List FlameDetector_data  = (List) FlameDetector.get(0).get("data");
            List<HashMap> Malformation= getallmessage.stream().filter(d -> "手动报警按钮".equals(d.get("tableName"))).collect(Collectors.toList());
            List Malformation_data  = (List) Malformation.get(0).get("data");
            List<HashMap> TAT= getallmessage.stream().filter(d -> "可燃气体探测器".equals(d.get("tableName"))).collect(Collectors.toList());
            List TGAT_data = (List) TAT.get(0).get("data");
            List<HashMap> hydrogenate= getallmessage.stream().filter(d -> "硫化氢探测器".equals(d.get("tableName"))).collect(Collectors.toList());
            List hydrogenate_data  = (List) hydrogenate.get(0).get("data");
            // 传入模板的数据
            String finalTem_date1 = finalTem_date;
            String finalTem_next_date1 = finalTem_next_date;
            return new HashMap<String, Object>() {{
                put("show", false);
                put("name", systemName);         // 名称
                put("Date", finalTem_date1);       // 日期
                put("nextCheckTime", finalTem_next_date1); // 下次检验日期
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
        }
        // 填充模板数据
        private void renderTemplate(Map<String, Object> templates) {

            @SuppressLint("SdCardPath") String url = "/storage/self/primary/output.docx";
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

            XWPFTemplate template = XWPFTemplate.compile(url,configs).render(templates);

            try {
                //保存到ExportData这个文件夹下
                File path = Environment.getExternalStorageDirectory();
                path = Environment.getExternalStoragePublicDirectory("年检报告");
                File file = new File(path + "/" + fileName + ".docx");
                OutputStream out = new FileOutputStream(file);
                template.write(out);
                out.flush();
                out.close();
                template.close();
                Toast.makeText(mContext, "报告生成成功", Toast.LENGTH_SHORT).show();

                if (file.isFile()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri uri = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() + ".fileProvider", file);
                    intent.setDataAndType(uri, "application/msword");
                    mContext.startActivity(intent);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(mContext, "报告生成失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * @author: 吴伟
     *
     * @Date: 2020/5/21
     *
     * @name: 系统表格数据不为空时合并该模板到初始模板
     *
     * @Description: 判断模板数据不为空则进行合并
     */
    // 判断表格数据是否为空，初始化模板
    private class systemTableIsNull {

        private void initTemplate(Map<String, Object> templateData) throws Exception {
                // 输出的模板文件名
                String path = Environment.getExternalStorageDirectory().getPath();
                File file = new File(path + "/" + "output" + ".docx");
                OutputStream des = new FileOutputStream(file);
                InputStream originTemplate = mContext.getAssets().open("report/起始页.docx");
                OPCPackage src1Package = OPCPackage.open(originTemplate);
                XWPFDocument src1Document = new XWPFDocument(src1Package);
                mergeTemplate(templateData, src1Document);
                src1Document.write(des); // 生成合并后的output模板
        }


        private void mergeTemplate(Map<String, Object> templateData, XWPFDocument src1Document) throws Exception {
            mhq_table(templateData, src1Document);
            co2FireSystem(templateData, src1Document);
            FM200FireFightingSystem(templateData, src1Document);
            PowderExtinguishingSystems(templateData, src1Document);
            RestaurantFireSuppressionDevice(templateData, src1Document);
//            paomomiehuoSystems(templateData, src1Document);
            FireFightingWaterSystem(templateData, src1Document);
            MarineFiremansOutfitEEBD(templateData, src1Document);
            SmokeDetectors(templateData, src1Document);
            TemperatureDetector(templateData, src1Document);
            FlameDetector(templateData, src1Document);
            GasDetector(templateData, src1Document);
            H2SDetectors(templateData, src1Document);
            ManualAlarmButton(templateData, src1Document);
        }
        // 灭火器模板
        private void mhq_table(Map<String, Object> templateData, XWPFDocument src1Document) throws Exception {
            List mhq_table = (List) templateData.get("mhq_table");
            assert mhq_table != null;
            if(mhq_table.size() != 0) {
                Log.d("灭火器系统------", mhq_table+"");
                InputStream open2 = mContext.getAssets().open("report/灭火器.docx");
                OPCPackage srcPackage = OPCPackage.open(open2);
                XWPFDocument src2Document = new XWPFDocument(srcPackage);
                new MergeDoc().appendBody(src1Document, src2Document);
            }
        }
        //二氧化碳灭火系统模板
        private void co2FireSystem(Map<String, Object> templateData, XWPFDocument src1Document) throws Exception {
            // 药剂瓶
            List co2_yjp_Rows = (List) templateData.get("co2_yjp_Rows");
            assert co2_yjp_Rows != null;
            if(co2_yjp_Rows.size() != 0) {
                OPCPackage src2Package;
                InputStream open = mContext.getAssets().open("report/药剂瓶.docx");
                src2Package = OPCPackage.open(open);
                XWPFDocument src2Document = new XWPFDocument(src2Package);
                new MergeDoc().appendBody(src1Document, src2Document);
            }
            // 氮气瓶
            List co2_N2_Rows = (List) templateData.get("co2_N2_Rows");
            assert co2_N2_Rows != null;
            if(co2_N2_Rows.size() != 0) {
                OPCPackage src2Package;
                InputStream open = mContext.getAssets().open("report/氮气瓶.docx");
                src2Package = OPCPackage.open(open);
                XWPFDocument src2Document = new XWPFDocument(src2Package);
                new MergeDoc().appendBody(src1Document, src2Document);
            }
        }
        //七氟丙烷灭火系统
        private void FM200FireFightingSystem(Map<String, Object> templateData, XWPFDocument src1Document) throws Exception {
            // 七氟丙烷钢瓶
            List HfRows = (List) templateData.get("HfRows");
            assert HfRows != null;
            if(HfRows.size() != 0) {
                OPCPackage src2Package;
                InputStream open = mContext.getAssets().open("report/七氟丙烷钢瓶.docx");
                src2Package = OPCPackage.open(open);
                XWPFDocument src2Document = new XWPFDocument(src2Package);
                new MergeDoc().appendBody(src1Document, src2Document);
            }

            // 氮气驱动瓶
            List NDRows = (List) templateData.get("NDRows");
            assert NDRows != null;
            if(NDRows.size() != 0) {
                OPCPackage src2Package;
                InputStream open = mContext.getAssets().open("report/氮气驱动瓶.docx");
                src2Package = OPCPackage.open(open);
                XWPFDocument src2Document = new XWPFDocument(src2Package);
                new MergeDoc().appendBody(src1Document, src2Document);
            }
        }
        //干粉灭火系统
        private void PowderExtinguishingSystems(Map<String, Object> templateData, XWPFDocument src1Document) throws Exception {
            // 干粉罐
            List gfRows = (List) templateData.get("gfRows");
            assert gfRows != null;
            if(gfRows.size() != 0) {
                OPCPackage src2Package;
                InputStream open = mContext.getAssets().open("report/干粉罐.docx");
                src2Package = OPCPackage.open(open);
                XWPFDocument src2Document = new XWPFDocument(src2Package);
                new MergeDoc().appendBody(src1Document, src2Document);
            }
        }
//        //泡沫灭火系统
//        private void paomomiehuoSystems(Map<String, Object> templateData, XWPFDocument src1Document) throws Exception {
//            // 干粉罐
//            List gfRows = (List) templateData.get("gfRows");
//            assert gfRows != null;
//            if(gfRows.size() != 0) {
//                OPCPackage src2Package;
//                InputStream open = mContext.getAssets().open("report/泡沫灭火系统.docx");
//                src2Package = OPCPackage.open(open);
//                XWPFDocument src2Document = new XWPFDocument(src2Package);
//                new MergeDoc().appendBody(src1Document, src2Document);
//            }
//        }
        //厨房设备灭火装置
        private void RestaurantFireSuppressionDevice(Map<String, Object> templateData, XWPFDocument src1Document) throws Exception{
            // 药剂瓶
            List YJRows = (List) templateData.get("YJRows");
            assert YJRows != null;
            if(YJRows.size() != 0) {
                OPCPackage src2Package;
                InputStream open = mContext.getAssets().open("report/厨房药剂瓶.docx");
                src2Package = OPCPackage.open(open);
                XWPFDocument src2Document = new XWPFDocument(src2Package);
                new MergeDoc().appendBody(src1Document, src2Document);
            }
            //驱动瓶
            List QDRows = (List) templateData.get("QDRows");
            assert QDRows != null;
            if(QDRows.size() != 0) {
                OPCPackage src2Package;
                InputStream open = mContext.getAssets().open("report/厨房驱动瓶1.docx");
                src2Package = OPCPackage.open(open);
                XWPFDocument src2Document = new XWPFDocument(src2Package);
                new MergeDoc().appendBody(src1Document, src2Document);
            }
        }
        //消防水系统
        private void FireFightingWaterSystem(Map<String, Object> templateData, XWPFDocument src1Document) throws Exception{

            List firHRows = (List) templateData.get("firHRows");
            assert firHRows != null;
            if(firHRows.size() != 0) {
                OPCPackage src2Package;
                InputStream open = mContext.getAssets().open("report/消防水系统.docx");
                src2Package = OPCPackage.open(open);
                XWPFDocument src2Document = new XWPFDocument(src2Package);
                new MergeDoc().appendBody(src1Document, src2Document);
            }
        }
        // 海水雨林系统




        //消防员装备和EEBD
        private void MarineFiremansOutfitEEBD(Map<String, Object> templateData, XWPFDocument src1Document) throws Exception{
            List EEBDBottleRows = (List) templateData.get("EEBDBottleRows");
            assert EEBDBottleRows != null;
            if(EEBDBottleRows.size() != 0) {
                OPCPackage src2Package;
                InputStream open = mContext.getAssets().open("report/EEBD.docx");
                src2Package = OPCPackage.open(open);
                XWPFDocument src2Document = new XWPFDocument(src2Package);
                new MergeDoc().appendBody(src1Document, src2Document);
            }
        }
        //感烟探测器
        private void SmokeDetectors(Map<String, Object> templateData, XWPFDocument src1Document) throws Exception{
            List SmokeDRows = (List) templateData.get("SmokeDRows");
            assert SmokeDRows != null;
            if(SmokeDRows.size() != 0) {
                OPCPackage src2Package;
                InputStream open = mContext.getAssets().open("report/感烟探测器.docx");
                src2Package = OPCPackage.open(open);
                XWPFDocument src2Document = new XWPFDocument(src2Package);
                new MergeDoc().appendBody(src1Document, src2Document);
            }
        }
        //感温探测器
        private void TemperatureDetector(Map<String, Object> templateData, XWPFDocument src1Document) throws Exception{
            List HeatDRows = (List) templateData.get("HeatDRows");
            assert HeatDRows != null;
            if(HeatDRows.size() != 0) {
                OPCPackage src2Package;
                InputStream open = mContext.getAssets().open("report/感温探测器.docx");
                src2Package = OPCPackage.open(open);
                XWPFDocument src2Document = new XWPFDocument(src2Package);
                new MergeDoc().appendBody(src1Document, src2Document);
            }
        }
        //火焰探测器
        private void FlameDetector(Map<String, Object> templateData, XWPFDocument src1Document) throws Exception{
            List FlameDeRows = (List) templateData.get("FlameDeRows");
            assert FlameDeRows != null;
            if(FlameDeRows.size() != 0) {
                OPCPackage src2Package;
                InputStream open = mContext.getAssets().open("report/火焰探测器.docx");
                src2Package = OPCPackage.open(open);
                XWPFDocument src2Document = new XWPFDocument(src2Package);
                new MergeDoc().appendBody(src1Document, src2Document);
            }
        }
        //可燃气体探测器
        private void GasDetector(Map<String, Object> templateData, XWPFDocument src1Document) throws Exception{
            List TGATRows = (List) templateData.get("TGATRows");
            assert TGATRows != null;
            if(TGATRows.size() != 0) {
                OPCPackage src2Package;
                InputStream open = mContext.getAssets().open("report/可燃气体探测器.docx");
                src2Package = OPCPackage.open(open);
                XWPFDocument src2Document = new XWPFDocument(src2Package);
                new MergeDoc().appendBody(src1Document, src2Document);
            }
        }
        //硫化氢探测器
        private void H2SDetectors(Map<String, Object> templateData, XWPFDocument src1Document) throws Exception{
            List hydrogensulRows = (List) templateData.get("hydrogensulRows");
            assert hydrogensulRows != null;
            if(hydrogensulRows.size() != 0) {
                OPCPackage src2Package;
                InputStream open = mContext.getAssets().open("report/硫化氢探测器.docx");
                src2Package = OPCPackage.open(open);
                XWPFDocument src2Document = new XWPFDocument(src2Package);
                new MergeDoc().appendBody(src1Document, src2Document);
            }
        }
        //手动火灾报警按钮
        private void ManualAlarmButton(Map<String, Object> templateData, XWPFDocument src1Document) throws Exception{
            List ManuabtnRows = (List) templateData.get("ManuabtnRows");
            assert ManuabtnRows != null;
            if(ManuabtnRows.size() != 0) {
                OPCPackage src2Package;
                InputStream open = mContext.getAssets().open("report/手动火灾报警按钮.docx");
                src2Package = OPCPackage.open(open);
                XWPFDocument src2Document = new XWPFDocument(src2Package);
                new MergeDoc().appendBody(src1Document, src2Document);
            }
        }
    }

    /**
     * 获取手机时间  下次检验日期推迟一年减一天
     * return 年/月/日
     * */

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
     * */

    private String getDate(Date date) {
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            return dateFormat.format(date);
        }catch (Exception e) {
            return null;
        }
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
    /**
     * @creatBy 2020/5/19
     * @description 创建各个系统表格格式
     * @autor 吴伟
    **/

    // 高压二氧化碳灭火系统表格参数->  氮气瓶
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
        List<HashMap> mhqData = getallmessage.stream().filter(d -> "灭火器".equals(d.get("tableName"))).collect(Collectors.toList());
        final List data = (List) mhqData.get(0).get("data");
        Style headTextStyle = new Style();
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(9);
        headTextStyle.setColor("000000");
        List<RowRenderData> RowArr = new ArrayList<>();
        assert data != null;
        for (int i = 0; i < data.size(); i++) {
            ItemInfo itemObj_cell = (ItemInfo) data.get(i);
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
}
