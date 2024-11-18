package com.softcostEstimator.evaluate.mapper;

import java.util.List;
import com.softcostEstimator.evaluate.domain.Productbudget;

/**
 * 综合评估Mapper接口
 *
 * @author ruoyi
 * @date 2024-11-15
 */
public interface ProductbudgetMapper
{
    /**
     * 查询综合评估
     *
     * @param projectID 综合评估主键
     * @return 综合评估
     */
    public Productbudget selectProductbudgetByProjectID(Long projectID);

    /**
     * 查询综合评估列表
     *
     * @param productbudget 综合评估
     * @return 综合评估集合
     */
    public List<Productbudget> selectProductbudgetList(Productbudget productbudget);

    /**
     * 新增综合评估
     *
     * @param productbudget 综合评估
     * @return 结果
     */
    public int insertProductbudget(Productbudget productbudget);

    /**
     * 修改综合评估
     *
     * @param productbudget 综合评估
     * @return 结果
     */
    public int updateProductbudget(Productbudget productbudget);

    /**
     * 删除综合评估
     *
     * @param projectID 综合评估主键
     * @return 结果
     */
    public int deleteProductbudgetByProjectID(Long projectID);

    /**
     * 批量删除综合评估
     *
     * @param projectIDs 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductbudgetByProjectIDs(Long[] projectIDs);
}
