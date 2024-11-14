package com.softcostEstimator.evaluate.service;

import java.util.List;
import com.softcostEstimator.evaluate.domain.Productbudget;

/**
 * 造价评估Service接口
 * 
 * @author li
 * @date 2024-11-14
 */
public interface IProductbudgetService 
{
    /**
     * 查询造价评估
     * 
     * @param productID 造价评估主键
     * @return 造价评估
     */
    public Productbudget selectProductbudgetByProductID(Long productID);

    /**
     * 查询造价评估列表
     * 
     * @param productbudget 造价评估
     * @return 造价评估集合
     */
    public List<Productbudget> selectProductbudgetList(Productbudget productbudget);

    /**
     * 新增造价评估
     * 
     * @param productbudget 造价评估
     * @return 结果
     */
    public int insertProductbudget(Productbudget productbudget);

    /**
     * 修改造价评估
     * 
     * @param productbudget 造价评估
     * @return 结果
     */
    public int updateProductbudget(Productbudget productbudget);

    /**
     * 批量删除造价评估
     * 
     * @param productIDs 需要删除的造价评估主键集合
     * @return 结果
     */
    public int deleteProductbudgetByProductIDs(Long[] productIDs);

    /**
     * 删除造价评估信息
     * 
     * @param productID 造价评估主键
     * @return 结果
     */
    public int deleteProductbudgetByProductID(Long productID);
}
