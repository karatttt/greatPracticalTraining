package com.softcostEstimator.evaluate.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softcostEstimator.evaluate.mapper.ProductbudgetMapper;
import com.softcostEstimator.evaluate.domain.Productbudget;
import com.softcostEstimator.evaluate.service.IProductbudgetService;

/**
 * 综合评估Service业务层处理
 *
 * @author ruoyi
 * @date 2024-11-15
 */
@Service
public class ProductbudgetServiceImpl implements IProductbudgetService
{
    @Autowired
    private ProductbudgetMapper productbudgetMapper;

    /**
     * 查询综合评估
     *
     * @param projectID 综合评估主键
     * @return 综合评估
     */
    @Override
    public Productbudget selectProductbudgetByProjectID(Long projectID)
    {
        return productbudgetMapper.selectProductbudgetByProjectID(projectID);
    }

    /**
     * 查询综合评估列表
     *
     * @param productbudget 综合评估
     * @return 综合评估
     */
    @Override
    public List<Productbudget> selectProductbudgetList(Productbudget productbudget)
    {
        return productbudgetMapper.selectProductbudgetList(productbudget);
    }

    /**
     * 新增综合评估
     *
     * @param productbudget 综合评估
     * @return 结果
     */
    @Override
    public int insertProductbudget(Productbudget productbudget)
    {
        return productbudgetMapper.insertProductbudget(productbudget);
    }

    /**
     * 修改综合评估
     *
     * @param productbudget 综合评估
     * @return 结果
     */
    @Override
    public int updateProductbudget(Productbudget productbudget)
    {
        return productbudgetMapper.updateProductbudget(productbudget);
    }

    /**
     * 批量删除综合评估
     *
     * @param projectIDs 需要删除的综合评估主键
     * @return 结果
     */
    @Override
    public int deleteProductbudgetByProjectIDs(Long[] projectIDs)
    {
        return productbudgetMapper.deleteProductbudgetByProjectIDs(projectIDs);
    }

    /**
     * 删除综合评估信息
     *
     * @param projectID 综合评估主键
     * @return 结果
     */
    @Override
    public int deleteProductbudgetByProjectID(Long projectID)
    {
        return productbudgetMapper.deleteProductbudgetByProjectID(projectID);
    }
}
