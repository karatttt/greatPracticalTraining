package com.softcostEstimator.generator.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.softcostEstimator.common.annotation.Excel;
import com.softcostEstimator.common.core.domain.BaseEntity;

/**
 * Function_point_analysis对象 software_project
 * 
 * @author ruoyi
 * @date 2024-11-14
 */
public class SoftwareProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String projectId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double ILF;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double EIF;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double EI;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double EO;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double EQ;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double UFP;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double GSC;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double TCF;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double AFP;

    public void setProjectId(String projectId) 
    {
        this.projectId = projectId;
    }

    public String getProjectId() 
    {
        return projectId;
    }
    public void setILF(Double ILF) 
    {
        this.ILF = ILF;
    }

    public Double getILF() 
    {
        return ILF;
    }
    public void setEIF(Double EIF) 
    {
        this.EIF = EIF;
    }

    public Double getEIF() 
    {
        return EIF;
    }
    public void setEI(Double EI) 
    {
        this.EI = EI;
    }

    public Double getEI() 
    {
        return EI;
    }
    public void setEO(Double EO) 
    {
        this.EO = EO;
    }

    public Double getEO() 
    {
        return EO;
    }
    public void setEQ(Double EQ) 
    {
        this.EQ = EQ;
    }

    public Double getEQ() 
    {
        return EQ;
    }
    public void setUFP(Double UFP) 
    {
        this.UFP = UFP;
    }

    public Double getUFP() 
    {
        return UFP;
    }
    public void setGSC(Double GSC) 
    {
        this.GSC = GSC;
    }

    public Double getGSC() 
    {
        return GSC;
    }
    public void setTCF(Double TCF) 
    {
        this.TCF = TCF;
    }

    public Double getTCF() 
    {
        return TCF;
    }
    public void setAFP(Double AFP) 
    {
        this.AFP = AFP;
    }

    public Double getAFP() 
    {
        return AFP;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectId", getProjectId())
            .append("ILF", getILF())
            .append("EIF", getEIF())
            .append("EI", getEI())
            .append("EO", getEO())
            .append("EQ", getEQ())
            .append("UFP", getUFP())
            .append("GSC", getGSC())
            .append("TCF", getTCF())
            .append("AFP", getAFP())
            .toString();
    }
}
