package com.softcostEstimator.evaluate.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.softcostEstimator.common.annotation.Excel;
import com.softcostEstimator.common.core.domain.BaseEntity;

/**
 * 项目信息对象 project
 * 
 * @author zzq
 * @date 2024-11-14
 */
public class Project extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目ID */
    private Long projectID;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 项目需求 */
    @Excel(name = "项目需求")
    private String requireContent;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 评估员 */
    @Excel(name = "评估员")
    private Long appraiserID;

    /** 审核员 */
    @Excel(name = "审核员")
    private Long auditorID;

    /** 租户 */
    @Excel(name = "租户")
    private Long tenantryID;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    public void setProjectID(Long projectID) 
    {
        this.projectID = projectID;
    }

    public Long getProjectID() 
    {
        return projectID;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setRequireContent(String requireContent) 
    {
        this.requireContent = requireContent;
    }

    public String getRequireContent() 
    {
        return requireContent;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setAppraiserID(Long appraiserID) 
    {
        this.appraiserID = appraiserID;
    }

    public Long getAppraiserID() 
    {
        return appraiserID;
    }
    public void setAuditorID(Long auditorID) 
    {
        this.auditorID = auditorID;
    }

    public Long getAuditorID() 
    {
        return auditorID;
    }
    public void setTenantryID(Long tenantryID) 
    {
        this.tenantryID = tenantryID;
    }

    public Long getTenantryID() 
    {
        return tenantryID;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectID", getProjectID())
            .append("name", getName())
            .append("requireContent", getRequireContent())
            .append("createTime", getCreateTime())
            .append("endTime", getEndTime())
            .append("appraiserID", getAppraiserID())
            .append("auditorID", getAuditorID())
            .append("tenantryID", getTenantryID())
            .append("description", getDescription())
            .toString();
    }
}
