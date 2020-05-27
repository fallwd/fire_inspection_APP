package com.hr.fire.inspection.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.utils.Class2Map;
import com.hr.fire.inspection.utils.ImportExcel;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.utils.MapToItemInfo;
import com.hr.fire.inspection.utils.SystemConstant;
import com.hr.fire.inspection.utils.TimeUtil;


import org.apache.commons.collections4.MapUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.lang.Long.parseLong;

public class ImportDataActicity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    private ListView lvFiles;
    private File[] currentFiles;
    private AutoCompleteTextView search;
    private List<Map<String,Object>> listItems3 = new ArrayList<>();;
    // 记录当前的父文件夹
    File currentParent;
    private List<CompanyInfo> dataList;
    private List<CheckType> checkTypes;
    private List<ItemInfo> itemDataList = new ArrayList<>();
    private Long companyInfoId;
    private String companyName;
    private String oilfieldName;
    private String platformName;
    private String number;
    private Date srt_Date;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_data);
        ImageView iv_finish = findViewById(R.id.iv_finish);
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //隐藏顶部位号、保护区域、及检查时间
        LinearLayout isShowTopText = (LinearLayout) this.findViewById(R.id.isShowTopText);
        isShowTopText.setVisibility(View.GONE);

        lvFiles = this.findViewById(R.id.law_content_list);
        getSdcardFileList();

        // 点击列表
        lvFiles.setOnItemClickListener((adapterView, view, position, id) -> {
            // 如果用户单击了文件，直接返回，不做任何处理
            if (currentFiles[position].isFile()) {
                // 也可自定义扩展打开这个文件等
                File stringtofile = currentFiles[position];

                String path = stringtofile.toString().substring(stringtofile.toString().indexOf("/"));

                String[] nameList = path.split("[_]");

                for (int i = 0; i < nameList.length; i++) {
                    Log.i("AAAA","strarray:"+ nameList[i]);
                }

                String[] initCompanyName = nameList[0].split("[/]");
                String[] initSrt_Date = nameList[5].split("[.]");

                companyName = initCompanyName[5];
                oilfieldName = nameList[1];
                platformName = nameList[2];
                number = nameList[4];

                String year= initSrt_Date[0].substring(0,4);
                String month= initSrt_Date[0].substring(4,6);
                String day= initSrt_Date[0].substring(6,8);
                String date = year+"-"+month+"-"+day;
                srt_Date = TimeUtil.parse(date,"yyyy-MM-dd");


                Log.i("AAAA","我的公司油田平台:"+ companyName+"====="+oilfieldName+ "=====" + platformName);

                String sys_name = nameList[3];
                int systemId = 1;
                switch ((String) sys_name) {
                    case "高压二氧化碳灭火系统":
                        systemId = 1;
                        break;
                    case "七氟丙烷灭火系统":
                        systemId = 9;
                        break;
                    case "灭火器":
                        systemId = 17;
                        break;
                    case "火灾自动报警系统":
                        systemId = 19;
                        break;
                    case "厨房设备灭火装置":
                        systemId = 29;
                        break;
                    case "海水雨淋灭火系统":
                        systemId = 36;
                        break;
                    case "消防水灭火系统":
                        systemId = 40;
                        break;
                    case "固定式干粉灭火系统":
                        systemId = 47;
                        break;
                    case "泡沫灭火系统":
                        systemId = 54;
                        break;
                    case "消防员装备":
                        systemId = 59;
                        break;
                }


                dataList = ServiceFactory.getCompanyInfoService().getPlatformList(nameList[1]);
                CompanyInfo CompanyListItem = null;
                for (int i = 0; i<dataList.size(); i++) {
                    CompanyListItem = dataList.get(i);
                    if (CompanyListItem.getPlatformName() != null) {
                        if (nameList[2].equals(CompanyListItem.getPlatformName())) {
                            Log.i("AAAA","CompanyListItem当前数据:"+ CompanyListItem);
                            companyInfoId = CompanyListItem.getId();
                        }
                    }
                }


                Log.i("AAAA","我当前系统的ID:"+ systemId);
                checkTypes = ServiceFactory.getYearCheckService().gettableNameData(systemId);
                System.out.println("CheckType : " + checkTypes.toString());
                if (checkTypes == null) {
                    Toast.makeText(this, "没有获取到检查表的数据", Toast.LENGTH_SHORT).show();
                }


                ImportExcel excelUtils = new ImportExcel();
                try {
                    List<List<Map<String, Object>>> result = excelUtils.readExcel(stringtofile, checkTypes);

                    //对数据做处理，插入数据库
                    int checkReusltIndex = SystemConstant.getCheckReusltIndexBySystemId(systemId);
                    for (int i=0;i<result.size();i ++) {
                        List<Map<String,Object>> list = result.get(i);

                        if (i < checkReusltIndex) {//itemInfo
                            for (Map<String,Object> map : list) {
//                                System.out.println("设备表："+new Gson().toJson(map));
                                ServiceFactory.getCompanyInfoService().addData(companyName, oilfieldName, platformName);
                                ItemInfo itemInfo = new ItemInfo();
                                itemInfo.setTypeNo(MapUtils.getString(map,"typeNo"));
                                itemInfo.setDeviceType(MapUtils.getString(map,"deviceType"));
                                itemInfo.setAgentsType(MapUtils.getString(map,"agentsType"));
                                itemInfo.setFillingDate(TimeUtil.parse(MapUtils.getString(map,"fillingDate"),"yyyy-MM"));
                                itemInfo.setNo(MapUtils.getString(map,"no"));
                                itemInfo.setLevel(MapUtils.getString(map,"level"));
                                itemInfo.setVolume(MapUtils.getString(map,"volume"));
                                itemInfo.setWeight(MapUtils.getString(map,"weight"));
                                itemInfo.setGoodsWeight(MapUtils.getString(map,"goodsWeight"));
                                itemInfo.setPressure(MapUtils.getString(map,"pressure"));
                                itemInfo.setProdFactory(MapUtils.getString(map,"prodFactory"));
                                itemInfo.setProdDate(TimeUtil.parse(MapUtils.getString(map,"prodDate"),"yyyy-MM"));
                                itemInfo.setTypeConformity(MapUtils.getString(map,"typeConformity"));
                                itemInfo.setPositionConformity(MapUtils.getString(map,"positionConformity"));
                                itemInfo.setAppearance(MapUtils.getString(map,"appearance"));
                                itemInfo.setIsPressure(MapUtils.getString(map,"isPressure"));
                                itemInfo.setCheck(MapUtils.getString(map,"check"));
                                itemInfo.setSlience(MapUtils.getString(map,"slience"));
                                itemInfo.setReset(MapUtils.getString(map,"reset"));
                                itemInfo.setPowerAlarmFunction(MapUtils.getString(map,"powerAlarmFunction"));
                                itemInfo.setAlarmFunction(MapUtils.getString(map,"alarmFunction"));
                                itemInfo.setEffectiveness(MapUtils.getString(map,"effectiveness"));
                                itemInfo.setResponseTime(MapUtils.getString(map,"responseTime"));
                                itemInfo.setDescription(MapUtils.getString(map,"description"));
                                itemInfo.setSetAlarm25(MapUtils.getString(map,"setAlarm25"));
                                itemInfo.setSetAlarm50(MapUtils.getString(map,"setAlarm50"));
                                itemInfo.setTestAlarm25(MapUtils.getString(map,"testAlarm25"));
                                itemInfo.setTestAlarm50(MapUtils.getString(map,"testAlarm50"));
                                itemInfo.setObserveDate(TimeUtil.parse(MapUtils.getString(map,"observeDate"),"yyyy-MM"));
                                itemInfo.setTaskNumber(MapUtils.getString(map,"taskNumber"));
                                itemInfo.setIsPass(MapUtils.getString(map,"isPass"));
                                itemInfo.setLabelNo(MapUtils.getString(map,"labelNo"));
                                itemInfo.setImageUrl(MapUtils.getString(map,"imageUrl"));
                                itemInfo.setCodePath(MapUtils.getString(map,"codePath"));


                                try {
                                    ServiceFactory.getYearCheckService().insertItemDataEasy(itemInfo, companyInfoId, checkTypes.get(i).getId(), number=="" ? null : number, srt_Date);
                                } catch (Exception e) {
                                    ServiceFactory.getYearCheckService().update(itemInfo);
                                }


                                String checkDataEasy = MapUtils.getString(map, "checkDataEasy");
                                List<YearCheck> yearChecks = new Gson().fromJson(checkDataEasy,new TypeToken<List<YearCheck>>(){}.getType());
//                                    Log.i("AAAA","yearChecks : "+ yearChecks);

                                String yearCheckResults = MapUtils.getString(map, "yearCheckResults");
                                List<YearCheckResult> YearCheckResult = new Gson().fromJson(yearCheckResults,new TypeToken<List<YearCheckResult>>(){}.getType());

                                if (YearCheckResult != null) {
                                    for (int y = 0; y < YearCheckResult.size(); y++) {
//                                          Log.i("AAAA","YearCheckResult : "+ YearCheckResult);
                                        YearCheckResult ycr = YearCheckResult.get(y);
                                        ycr.setIsPass(YearCheckResult.get(y).getIsPass());
                                        ycr.setImageUrl(YearCheckResult.get(y).getImageUrl());
                                        ycr.setDescription(YearCheckResult.get(y).getDescription());
                                        ycr.setSystemNumber(number=="" ? null : number);
                                        ycr.setProtectArea(" "); // 保护位号
                                        ycr.setCheckDate(srt_Date);  //检查日期
                                        ycr.setUuid(UUID.randomUUID().toString().replace("-",""));  // 数据导入时候做去重判断
//                                          Log.i("AAAA","我的二级表右侧数据:"+ ycr);
                                        try {
                                            ServiceFactory.getYearCheckService().insertCheckResultDataEasy(ycr, itemInfo.getId(), yearChecks.get(y).getId(), companyInfoId, checkTypes.get(i).getId(), number == "" ? null : number, srt_Date);
                                        } catch (Exception e) {
                                            ServiceFactory.getYearCheckService().update(ycr);
                                        }
                                    }
                                }
                            }

                        } else { //checkResult

                            for (Map<String,Object> map : list) {
                                System.out.println("检查表："+new Gson().toJson(map));
                                ServiceFactory.getCompanyInfoService().addData(companyName, oilfieldName, platformName);
                                ItemInfo itemInfo = new ItemInfo();


                                YearCheckResult ycr = new YearCheckResult();



                                YearCheck yearCheck = new YearCheck();


                                yearCheck.setRequirement(MapUtils.getString(map,"yearCheck.requirement"));
                                yearCheck.setProject(MapUtils.getString(map,"yearCheck.project"));
                                yearCheck.setRequirement(MapUtils.getString(map,"yearCheck.requirement"));
                                yearCheck.setContent(MapUtils.getString(map,"yearCheck.content"));
                                yearCheck.setStandard(MapUtils.getString(map,"yearCheck.standard"));

                                ycr.setYearCheck(yearCheck);
                                ycr.setImageUrl(MapUtils.getString(map,"imageUrl"));
                                ycr.setDescription(MapUtils.getString(map,"description"));
                                ycr.setIsPass(MapUtils.getString(map,"isPass"));



                                Log.i("AAAA","我的ycr : "+ ycr);
                                try {
//                                    Log.i("AAAA","我的二级表右侧数据1:"+ ycr);
//                                    Log.i("AAAA","我的二级表右侧数据2:"+ itemInfo.getId());
//                                    Log.i("AAAA","我的二级表右侧数据3:"+ yearChecks.get(i).getId());
//                                    Log.i("AAAA","我的二级表右侧数据4:"+ companyInfoId);
//                                    Log.i("AAAA","我的二级表右侧数据5:"+ checkTypes.get(i).getId());
//                                    Log.i("AAAA","我的二级表右侧数据6:"+ number);
//                                    Log.i("AAAA","我的二级表右侧数据7:"+ srt_Date);
                                    // ToDo
                                    long yearcheckId = parseLong(MapUtils.getString(map,"yearCheckId"));
                                    Log.i("AAAA","yearcheckIdyearcheckIdyearcheckId:"+ yearcheckId);

                                    ServiceFactory.getYearCheckService().insertCheckResultDataEasy(ycr, 0, yearcheckId, companyInfoId, checkTypes.get(i).getId(), number == "" ? null : number, srt_Date);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    LogUtils.e(e.toString());
                                    Log.i("AAAA","updateupdateupdateupdateupdateupdate : "+ ycr);
                                    ServiceFactory.getYearCheckService().update(ycr);
                                }

//                                String checkDataEasy = MapUtils.getString(map, "checkDataEasy");
//                                List<YearCheck> yearChecks = new Gson().fromJson(checkDataEasy,new TypeToken<List<YearCheck>>(){}.getType());
//
//                                String yearCheckResults = MapUtils.getString(map, "yearCheckResults");
//                                List<YearCheckResult> YearCheckResult = new Gson().fromJson(yearCheckResults,new TypeToken<List<YearCheckResult>>(){}.getType());
//                                Log.i("AAAA","YearCheckResult : "+ YearCheckResult);
//                                if (YearCheckResult != null) {
//                                    for (int y = 0; y < YearCheckResult.size(); y++) {
//                                        YearCheckResult ycr = YearCheckResult.get(y);
//                                    /*YearCheckResult ycr = new YearCheckResult();
//                                    ycr.setIsPass(YearCheckResult.get(y).getIsPass());
//                                    ycr.setImageUrl(YearCheckResult.get(y).getImageUrl());
//                                    ycr.setDescription(YearCheckResult.get(y).getDescription());
//                                    ycr.setSystemNumber(number=="" ? null : number);
//                                    ycr.setProtectArea(" "); // 保护位号
//                                    ycr.setCheckDate(srt_Date);  //检查日期
//                                    ycr.setUuid(UUID.randomUUID().toString().replace("-",""));  // 数据导入时候做去重判断*/
//                                        Log.i("AAAA","我的二级表右侧数据1:"+ ycr);
//                                        Log.i("AAAA","我的二级表右侧数据2:"+ itemInfo.getId());
//                                        Log.i("AAAA","我的二级表右侧数据3:"+ yearChecks.get(y).getId());
//                                        Log.i("AAAA","我的二级表右侧数据4:"+ companyInfoId);
//                                        Log.i("AAAA","我的二级表右侧数据5:"+ checkTypes.get(y).getId());
//                                        Log.i("AAAA","我的二级表右侧数据6:"+ number);
//                                        Log.i("AAAA","我的二级表右侧数据7:"+ srt_Date);
//                                        try {
//                                            ServiceFactory.getYearCheckService().insertCheckResultDataEasy(ycr, itemInfo.getId(), yearChecks.get(y).getId(), companyInfoId, checkTypes.get(y).getId(), number == "" ? null : number, srt_Date);
//                                        } catch (Exception e) {
//                                            ServiceFactory.getYearCheckService().update(ycr);
//                                        }
//                                    }
//                                }
                            }




//                            try {
//
////                                ServiceFactory.getYearCheckService().insertItemDataEasy(itemInfo, companyInfoId, checkTypes.get(i).getId(), number=="" ? null : number, srt_Date);
//
//                                String checkDataEasy = MapUtils.getString(map, "checkDataEasy");
//                                List<YearCheck> yearChecks = new Gson().fromJson(checkDataEasy,new TypeToken<List<YearCheck>>(){}.getType());
////                                    Log.i("AAAA","yearChecks : "+ yearChecks);
//
//                                YearCheckResult ycr = new YearCheckResult();
//                                YearCheck yearCheck = new YearCheck();
//
//                                yearCheck.setRequirement(MapUtils.getString(map,"yearCheck.requirement"));
//                                yearCheck.setProject(MapUtils.getString(map,"yearCheck.project"));
//                                yearCheck.setRequirement(MapUtils.getString(map,"yearCheck.requirement"));
//                                yearCheck.setContent(MapUtils.getString(map,"yearCheck.content"));
//                                yearCheck.setStandard(MapUtils.getString(map,"yearCheck.standard"));
//
//                                ycr.setYearCheck(yearCheck);
//                                ycr.setImageUrl(MapUtils.getString(map,"imageUrl"));
//                                ycr.setDescription(MapUtils.getString(map,"description"));
//                                ycr.setIsPass(MapUtils.getString(map,"isPass"));
//
//                                ServiceFactory.getYearCheckService().insertCheckResultDataEasy(ycr, itemInfo.getId(), yearChecks.get(i).getId(), companyInfoId, checkTypes.get(i).getId(), number == "" ? null : number, srt_Date);
//                            } catch (Exception e) {
//                                ServiceFactory.getYearCheckService().update(itemInfo);
//                            }






                        }
                    }
                    for (List<Map<String,Object>> list : result) {
                        for (Map<String,Object> map : list) {
                            System.out.println(new Gson().toJson(map));
                        }
                    }
                    Toast.makeText(this, "导入数据成功", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtils.e(e.toString());
                    Toast.makeText(this, "导入数据失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    // 获取系统的SDCard的目录
    private void getSdcardFileList(){
        File sdcard = Environment.getExternalStoragePublicDirectory("ExportData");
        // 如果SD卡存在的话
        if (sdcard.exists()) {
            currentParent = sdcard;
            currentFiles = sdcard.listFiles();
            // 使用当前目录下的全部文件、文件夹来填充ListView
            inflateListView(currentFiles);
        }
    }
    /**
     * 根据文件夹填充ListView
     *
     * @param files
     */
    private void inflateListView(File[] files) {
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < files.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            if (files[i].isDirectory()) {
                // 如果是文件夹就显示的图片为文件夹的图片
                listItem.put("icon", R.drawable.bg_gray);
            } else {
                listItem.put("icon", R.drawable.bottom);
            }

            // 添加一个文件名称
            listItem.put("filename", files[i].getName());
            File myFile = new File(files[i].getName());

            // 获取文件的最后修改日期

            long modTime = myFile.lastModified();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");

            System.out.println(dateFormat.format(new Date(modTime)));

            // 添加一个最后修改日期
            listItem.put("modify",  "修改日期：" + dateFormat.format(new Date(modTime)));
            listItems.add(listItem);
            Log.d("listItems", listItems+"");
        }
        // 缓存遍历列表数据
        listItems3 = listItems;
        setAdpterdata(listItems);
    }

    // 定义一个SimpleAdapter 渲染列表
    private void setAdpterdata(List<Map<String, Object>> listItems){
        SimpleAdapter adapter = new SimpleAdapter(
                ImportDataActicity.this, listItems, R.layout.listitemsimple,
                new String[] { "filename", "icon", "modify" }, new int[] {  R.id.file_name, R.id.icon, R.id.file_modify });
        // 填充数据集
        lvFiles.setAdapter(adapter);
    }
}
