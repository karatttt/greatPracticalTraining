package com.softcostEstimator.evaluate.domain;

import lombok.Data;
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
@Data
public class Productbudget extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long projectID;

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double PDR;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double SF;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double BD;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double AT;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double QR;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double SL;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double DT;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double RSK;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double F;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double DNC;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double SDC;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double ESDC;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long tenantryID;

    public void setprojectID(Long projectID)
    {
        this.projectID = projectID;
    }

    public Long getprojectID()
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
                .append("projectID", getprojectID())
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
                .append("tenantryID", getTenantryID())
                .toString();
    }
}
