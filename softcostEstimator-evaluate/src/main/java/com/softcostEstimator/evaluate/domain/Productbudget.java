package com.softcostEstimator.evaluate.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.softcostEstimator.common.annotation.Excel;
import com.softcostEstimator.common.core.domain.BaseEntity;

/**
 * 造价评估对象 productbudget
 * 
 * @author li
 * @date 2024-11-14
 */
public class Productbudget extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long productID;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long PDR;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long SF;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long BD;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long AT;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long QR;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long SL;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long DT;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long RSK;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long F;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long DNC;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long SDC;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long ESDC;

    public void setProductID(Long productID) 
    {
        this.productID = productID;
    }

    public Long getProductID() 
    {
        return productID;
    }
    public void setPDR(Long PDR) 
    {
        this.PDR = PDR;
    }

    public Long getPDR() 
    {
        return PDR;
    }
    public void setSF(Long SF) 
    {
        this.SF = SF;
    }

    public Long getSF() 
    {
        return SF;
    }
    public void setBD(Long BD) 
    {
        this.BD = BD;
    }

    public Long getBD() 
    {
        return BD;
    }
    public void setAT(Long AT) 
    {
        this.AT = AT;
    }

    public Long getAT() 
    {
        return AT;
    }
    public void setQR(Long QR) 
    {
        this.QR = QR;
    }

    public Long getQR() 
    {
        return QR;
    }
    public void setSL(Long SL) 
    {
        this.SL = SL;
    }

    public Long getSL() 
    {
        return SL;
    }
    public void setDT(Long DT) 
    {
        this.DT = DT;
    }

    public Long getDT() 
    {
        return DT;
    }
    public void setRSK(Long RSK) 
    {
        this.RSK = RSK;
    }

    public Long getRSK() 
    {
        return RSK;
    }
    public void setF(Long F) 
    {
        this.F = F;
    }

    public Long getF() 
    {
        return F;
    }
    public void setDNC(Long DNC) 
    {
        this.DNC = DNC;
    }

    public Long getDNC() 
    {
        return DNC;
    }
    public void setSDC(Long SDC) 
    {
        this.SDC = SDC;
    }

    public Long getSDC() 
    {
        return SDC;
    }
    public void setESDC(Long ESDC) 
    {
        this.ESDC = ESDC;
    }

    public Long getESDC() 
    {
        return ESDC;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productID", getProductID())
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
            .toString();
    }
}
