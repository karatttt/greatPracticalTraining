package com.softcostEstimator.evaluate.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.softcostEstimator.common.annotation.Excel;
import com.softcostEstimator.common.core.domain.BaseEntity;

/**
 * 功能点分析对象 function_point_analysis
 * 
 * @author zzq
 * @date 2024-11-14
 */
public class FunctionPointAnalysis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目ID */
    private Long projectId;
    private String projectName;
    /** ILF */
    @Excel(name = "ILF")
    private Long ILF;

    /** EIF */
    @Excel(name = "EIF")
    private Long EIF;

    /** EI */
    @Excel(name = "EI")
    private Long EI;

    /** EO */
    @Excel(name = "EO")
    private Long EO;

    /** EQ */
    @Excel(name = "EQ")
    private Long EQ;

    /** UFP */
    @Excel(name = "UFP")
    private Long UFP;

    /** GSC */
    @Excel(name = "GSC")
    private Long GSC;

    /** TCF */
    @Excel(name = "TCF")
    private Float TCF;

    /** AFP */
    @Excel(name = "AFP")
    private Float AFP;

    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setILF(Long ILF) 
    {
        this.ILF = ILF;
    }

    public Long getILF() 
    {
        return ILF;
    }
    public void setEIF(Long EIF) 
    {
        this.EIF = EIF;
    }

    public Long getEIF() 
    {
        return EIF;
    }
    public void setEI(Long EI) 
    {
        this.EI = EI;
    }

    public Long getEI() 
    {
        return EI;
    }
    public void setEO(Long EO) 
    {
        this.EO = EO;
    }

    public Long getEO() 
    {
        return EO;
    }
    public void setEQ(Long EQ) 
    {
        this.EQ = EQ;
    }

    public Long getEQ() 
    {
        return EQ;
    }
    public void setUFP(Long UFP) 
    {
        this.UFP = UFP;
    }

    public Long getUFP() 
    {
        return UFP;
    }
    public void setGSC(Long GSC) 
    {
        this.GSC = GSC;
    }

    public Long getGSC() 
    {
        return GSC;
    }
    public void setTCF(float TCF)
    {
        this.TCF = TCF;
    }

    public Float getTCF()
    {
        return TCF;
    }
    public void setAFP(float AFP)
    {
        this.AFP = AFP;
    }

    public Float getAFP()
    {
        return AFP;
    }
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
