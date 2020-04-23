package com.hr.fire.inspection.adapter;
        import com.deepoove.poi.data.RowRenderData;
        import com.deepoove.poi.policy.DynamicTableRenderPolicy;
        import com.deepoove.poi.policy.MiniTableRenderPolicy;
        import java.util.List;
        import org.apache.poi.xwpf.usermodel.XWPFTable;
        import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class SmokeDetailTablePolicy extends DynamicTableRenderPolicy {
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
                for (int j = 0; j < 8; j++)	insertNewTableRow.createCell();

                // 合并单元格
//                 NiceXWPFDocument.mergeCellsHorizonal(table, laborsStartRow, 0, 0);
                MiniTableRenderPolicy.renderRow(table, laborsStartRow, labors.get(i));
            }
        }
    }

}


