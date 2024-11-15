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
 * @date 2024-11-14
 */
@Data
public class Productbudget extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目ID */
    private Long productID;

    /** PDR */
    @Excel(name = "PDR")
    private Long PDR;

    /** SF */
    @Excel(name = "SF")
    private Long SF;

    /** BD */
    @Excel(name = "BD")
    private Long BD;

    /** AT */
    @Excel(name = "AT")
    private Long AT;

    /** QR */
    @Excel(name = "QR")
    private Long QR;

    /** SL */
    @Excel(name = "SL")
    private Long SL;

    /** DT */
    @Excel(name = "DT")
    private Long DT;

    /** RSK */
    @Excel(name = "RSK")
    private Long RSK;

    /** F */
    @Excel(name = "F")
    private Long F;

    /** DNC */
    @Excel(name = "DNC")
    private Long DNC;

    /** SDC */
    @Excel(name = "SDC")
    private Long SDC;

    /** ESDC */
    @Excel(name = "ESDC")
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
