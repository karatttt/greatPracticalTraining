package com.softcostEstimator.evaluate.domain;

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
public class Productbudget extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目ID */
    private Long productID;

    /** PDR */
    @Excel(name = "PDR")
    private float PDR;

    /** SF */
    @Excel(name = "SF")
    private float SF;

    /** BD */
    @Excel(name = "BD")
    private float BD;

    /** AT */
    @Excel(name = "AT")
    private float AT;

    /** QR */
    @Excel(name = "QR")
    private float QR;

    /** SL */
    @Excel(name = "SL")
    private float SL;

    /** DT */
    @Excel(name = "DT")
    private float DT;

    /** RSK */
    @Excel(name = "RSK")
    private float RSK;

    /** F */
    @Excel(name = "F")
    private float F;

    /** DNC */
    @Excel(name = "DNC")
    private float DNC;

    /** SDC */
    @Excel(name = "SDC")
    private float SDC;

    /** ESDC */
    @Excel(name = "ESDC")
    private float ESDC;

    @Excel(name = "tenantryID")
    private int tenantryID;

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

    public float getPDR()
    {
        return PDR;
    }
    public void setSF(float SF)
    {
        this.SF = SF;
    }

    public float getSF()
    {
        return SF;
    }
    public void setBD(float BD)
    {
        this.BD = BD;
    }

    public float getBD()
    {
        return BD;
    }
    public void setAT(float AT)
    {
        this.AT = AT;
    }

    public float getAT()
    {
        return AT;
    }
    public void setQR(float QR)
    {
        this.QR = QR;
    }

    public float getQR()
    {
        return QR;
    }
    public void setSL(float SL)
    {
        this.SL = SL;
    }

    public float getSL()
    {
        return SL;
    }
    public void setDT(float DT)
    {
        this.DT = DT;
    }

    public float getDT()
    {
        return DT;
    }
    public void setRSK(float RSK)
    {
        this.RSK = RSK;
    }

    public float getRSK()
    {
        return RSK;
    }
    public void setF(float F)
    {
        this.F = F;
    }

    public float getF()
    {
        return F;
    }
    public void setDNC(float DNC)
    {
        this.DNC = DNC;
    }

    public float getDNC()
    {
        return DNC;
    }
    public void setSDC(float SDC)
    {
        this.SDC = SDC;
    }

    public float getSDC()
    {
        return SDC;
    }
    public void setESDC(float ESDC)
    {
        this.ESDC = ESDC;
    }

    public float getESDC()
    {
        return ESDC;
    }

    public void setPDR(float PDR) {
        this.PDR = PDR;
    }

    public int getTenantryID() {
        return tenantryID;
    }

    public void setTenantryID(int tenantryID) {
        this.tenantryID = tenantryID;
    }

    @Override
    public String toString() {
        return "Productbudget{" +
                "productID=" + productID +
                ", PDR=" + PDR +
                ", SF=" + SF +
                ", BD=" + BD +
                ", AT=" + AT +
                ", QR=" + QR +
                ", SL=" + SL +
                ", DT=" + DT +
                ", RSK=" + RSK +
                ", F=" + F +
                ", DNC=" + DNC +
                ", SDC=" + SDC +
                ", ESDC=" + ESDC +
                ", tenantryID=" + tenantryID +
                '}';
    }
}
