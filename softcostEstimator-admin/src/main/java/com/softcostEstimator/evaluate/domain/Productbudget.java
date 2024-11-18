package com.softcostEstimator.evaluate.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.softcostEstimator.common.annotation.Excel;
import com.softcostEstimator.common.core.domain.BaseEntity;

/**
 * 综合评估对象 productbudget
 *
 * @author ruoyi
 * @date 2024-11-15
 */
public class Productbudget extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目ID */
    private Long projectID;

    /** PDR */
    @Excel(name = "PDR", readConverterExp = "PDR")
    private Double PDR;

    /** SF */
    @Excel(name = "SF", readConverterExp = "SF")
    private Double SF;

    /** BD */
    @Excel(name = "BD", readConverterExp = "BD")
    private Double BD;

    /** AT */
    @Excel(name = "AT", readConverterExp = "AT")
    private Double AT;

    /** QR */
    @Excel(name = "QR", readConverterExp = "QR")
    private Double QR;

    /** SL */
    @Excel(name = "SL", readConverterExp = "SL")
    private Double SL;

    /** DT */
    @Excel(name = "DT", readConverterExp = "DT")
    private Double DT;

    /** RSK */
    @Excel(name = "RSK", readConverterExp = "RSK")
    private Double RSK;

    /** F */
    @Excel(name = "F", readConverterExp = "F")
    private Double F;

    /** DNC */
    @Excel(name = "DNC", readConverterExp = "DNC")
    private Double DNC;

    /** SDC */
    @Excel(name = "SDC", readConverterExp = "SDC")
    private Double SDC;

    /** ESDC */
    @Excel(name = "ESDC", readConverterExp = "ESDC")
    private Double ESDC;

    /** AE */
    @Excel(name = "评估工作量", readConverterExp = "AE")
    private Double AE; // 新增的评估工作量字段

    /** tenantryID */
    @Excel(name = "TenantryID", readConverterExp = "tenantryID")
    private Long tenantryID;

    // Getter 和 Setter 方法

    public void setProjectID(Long projectID)
    {
        this.projectID = projectID;
    }

    public Long getProjectID()
    {
        return projectID;
    }

    public void setPDR(Double PDR)
    {
        this.PDR = PDR;
    }

    public Double getPDR()
    {
        return PDR;
    }

    public void setSF(Double SF)
    {
        this.SF = SF;
    }

    public Double getSF()
    {
        return SF;
    }

    public void setBD(Double BD)
    {
        this.BD = BD;
    }

    public Double getBD()
    {
        return BD;
    }

    public void setAT(Double AT)
    {
        this.AT = AT;
    }

    public Double getAT()
    {
        return AT;
    }

    public void setQR(Double QR)
    {
        this.QR = QR;
    }

    public Double getQR()
    {
        return QR;
    }

    public void setSL(Double SL)
    {
        this.SL = SL;
    }

    public Double getSL()
    {
        return SL;
    }

    public void setDT(Double DT)
    {
        this.DT = DT;
    }

    public Double getDT()
    {
        return DT;
    }

    public void setRSK(Double RSK)
    {
        this.RSK = RSK;
    }

    public Double getRSK()
    {
        return RSK;
    }

    public void setF(Double F)
    {
        this.F = F;
    }

    public Double getF()
    {
        return F;
    }

    public void setDNC(Double DNC)
    {
        this.DNC = DNC;
    }

    public Double getDNC()
    {
        return DNC;
    }

    public void setSDC(Double SDC)
    {
        this.SDC = SDC;
    }

    public Double getSDC()
    {
        return SDC;
    }

    public void setESDC(Double ESDC)
    {
        this.ESDC = ESDC;
    }

    public Double getESDC()
    {
        return ESDC;
    }

    public void setAE(Double AE)
    {
        this.AE = AE;
    }

    public Double getAE()
    {
        return AE;
    }

    public void setTenantryID(Long tenantryID)
    {
        this.tenantryID = tenantryID;
    }

    public Long getTenantryID()
    {
        return tenantryID;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("projectID", getProjectID())
                .append("PDR", getPDR())
                .append("SF", getSF())
                .append("BD", getBD())
                .append("AT", getAT())
                .append("QR", getQR())
                .append("SL", getSL())
                .append("DT", getDT())
                .append("RSK", getRSK())
                .append("F", getF())
                .append("DNC", getDNC())
                .append("SDC", getSDC())
                .append("ESDC", getESDC())
                .append("AE", getAE()) // 更新toString方法，添加AE字段
                .append("tenantryID", getTenantryID())
                .toString();
    }
}
