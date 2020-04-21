package com.hr.fire.inspection.adapter;
import android.util.Log;

import com.deepoove.poi.NiceXWPFDocument;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.MiniTableRenderPolicy;
import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 * Created by on 2019/5/28.
 */
public class DetailTablePolicy2 extends DynamicTableRenderPolicy {

    // 货品填充数据所在行数
    //int goodsStartRow = 2;
    // 人工费填充数据所在行数
    int laborsStartRow = 1;

    @Override
    public void render(XWPFTable table, Object data) {
        if (null == data) return;
        // DetailData detailData = (DetailData) data;

        List<RowRenderData> labors =(List<RowRenderData>)data;

        if (null != labors) {
            table.removeRow(laborsStartRow);
            // 循环插入行
            for (int i = 0; i < labors.size(); i++) {
                XWPFTableRow insertNewTableRow = table.insertNewTableRow(laborsStartRow);
                //处理一行有几列
                for (int j = 0; j <8; j++)	insertNewTableRow.createCell();

                // 合并单元格
//                 NiceXWPFDocument.mergeCellsHorizonal(table, laborsStartRow, 0, 0);
                MiniTableRenderPolicy.renderRow(table, laborsStartRow, labors.get(i));
            }
        }
    }

}
