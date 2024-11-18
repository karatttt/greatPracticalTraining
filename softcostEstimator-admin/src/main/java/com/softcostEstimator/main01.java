package com.softcostEstimator;

import com.softcostEstimator.evaluate.transform.TransUtil;
import com.softcostEstimator.evaluate.transform.TransFactory;
public class main01 {
    public static void main(String[] args) {

        String str = new String("### 软件造价评估报告\n" +
                "\n" +
                "#### 项目基本信息\n" +
                "- **产品ID**: 1\n" +
                "- **项目名称**: 未提供\n" +
                "- **项目目标**: 未提供\n" +
                "- **项目范围**: 未提供\n" +
                "\n" +
                "#### 功能点分析\n" +
                "根据提供的数据，使用COSMIC方法进行功能点分析。\n" +
                "\n" +
                "- **PDR (过程驱动角色)**: 10\n" +
                "- **SF (系统功能)**: 12\n" +
                "- **BD (边界数据实体)**: 5\n" +
                "- **AT (应用触发器)**: 1\n" +
                "        - **SL (系统逻辑)**: 13\n" +
                "        - **DT (数据传输)**: 7\n" +
                "        - **RSK (风险因素)**: 11\n" +
                "        - **F (文件数量)**: 9\n" +
                "        - **DNC (数据网络复杂度)**: 6\n" +
                "        - **ESDC (外部系统数据复杂度)**: 8\n" +
                "\n" +
                "        #### 功能点计算\n" +
                "        功能点数（FP）的计算公式如下：\n" +
                "\\[ FP = PDR + SF + BD + AT + SL + DT \\]\n" +
                "\n" +
                "        代入具体数值：\n" +
                "\\[ FP = 10 + 12 + 5 + 1 + 13 + 7 = 48 \\]\n" +
                "\n" +
                "        #### 调整因子设置\n" +
                "        - **风险因素 (RSK)**: 11\n" +
                "        - **文件数量 (F)**: 9\n" +
                "        - **数据网络复杂度 (DNC)**: 6\n" +
                "        - **外部系统数据复杂度 (ESDC)**: 8\n" +
                "\n" +
                "        调整因子的计算公式如下：\n" +
                "        \\[ AF = 0.65 + (0.01 \\times RSK) + (0.01 \\times F) + (0.01 \\times DNC) + (0.01 \\times ESDC) \\]\n" +
                "\n" +
                "        代入具体数值：\n" +
                "        \\[ AF = 0.65 + (0.01 \\times 11) + (0.01 \\times 9) + (0.01 \\times 6) + (0.01 \\times 8) \\]\n" +
                "        \\[ AF = 0.65 + 0.11 + 0.09 + 0.06 + 0.08 \\]\n" +
                "        \\[ AF = 0.99 \\]\n" +
                "\n" +
                "        #### 造价评估结果计算\n" +
                "        假设每功能点的成本为 $1000（可根据实际情况调整）。\n" +
                "\n" +
                "        总成本的计算公式如下：\n" +
                "        \\[ 总成本 = FP \\times AF \\times 成本/FP \\]\n" +
                "\n" +
                "        代入具体数值：\n" +
                "        \\[ 总成本 = 48 \\times 0.99 \\times 1000 \\]\n" +
                "        \\[ 总成本 = 48 \\times 990 \\]\n" +
                "        \\[ 总成本 = 47520 \\]\n" +
                "\n" +
                "        #### 报告总结\n" +
                "        - **功能点数 (FP)**: 48\n" +
                "        - **调整因子 (AF)**: 0.99\n" +
                "        - **总成本**: $47,520\n" +
                "\n" +
                "        #### 建议\n" +
                "        - 详细定义项目目标和范围，以便更准确地进行评估。\n" +
                "        - 考虑进一步细化功能点分析，以确保所有需求都被涵盖。\n" +
                "        - 根据实际情况调整每功能点的成本。\n" +
                "\n" +
                "        #### 附录\n" +
                "        - 详细的调整因子选择理由\n" +
                "        - 功能点分析的详细描述\n" +
                "\n" +
                "        ---\n" +
                "\n" +
                "        **注**: 本报告基于提供的数据生成。如有更多详细信息，可以进一步优化评估结果。");
        TransUtil transUtil = TransFactory.newInstance("html");
        String path = transUtil.transForm(str);
        System.out.println(path);
    }
}
