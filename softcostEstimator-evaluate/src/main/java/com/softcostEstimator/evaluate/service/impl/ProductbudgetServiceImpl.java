package com.softcostEstimator.evaluate.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softcostEstimator.evaluate.mapper.ProductbudgetMapper;
import com.softcostEstimator.evaluate.domain.Productbudget;
import com.softcostEstimator.evaluate.service.IProductbudgetService;

/**
 * 造价评估Service业务层处理
 * 
 * @author li
 * @date 2024-11-14
 */
@Service
public class ProductbudgetServiceImpl implements IProductbudgetService 
{
    @Autowired
    private ProductbudgetMapper productbudgetMapper;

    /**
     * 查询造价评估
     * 
     * @param productID 造价评估主键
     * @return 造价评估
     */
    @Override
    public Productbudget selectProductbudgetByProductID(Long productID)
    {
        return productbudgetMapper.selectProductbudgetByProductID(productID);
    }

    /**
     * 查询造价评估列表
     * 
     * @param productbudget 造价评估
     * @return 造价评估
     */
    @Override
    public List<Productbudget> selectProductbudgetList(Productbudget productbudget)
    {
        return productbudgetMapper.selectProductbudgetList(productbudget);
    }

    /**
     * 新增造价评估
     * 
     * @param productbudget 造价评估
     * @return 结果
     */
    @Override
    public int insertProductbudget(Productbudget productbudget)
    {
        return productbudgetMapper.insertProductbudget(productbudget);
    }

    /**
     * 修改造价评估
     * 
     * @param productbudget 造价评估
     * @return 结果
     */
    @Override
    public int updateProductbudget(Productbudget productbudget)
    {
        return productbudgetMapper.updateProductbudget(productbudget);
    }

    /**
     * 批量删除造价评估
     * 
     * @param productIDs 需要删除的造价评估主键
     * @return 结果
     */
    @Override
    public int deleteProductbudgetByProductIDs(Long[] productIDs)
    {
        return productbudgetMapper.deleteProductbudgetByProductIDs(productIDs);
    }

    /**
     * 删除造价评估信息
     * 
     * @param productID 造价评估主键
     * @return 结果
     */
    @Override
    public int deleteProductbudgetByProductID(Long productID)
    {
        return productbudgetMapper.deleteProductbudgetByProductID(productID);
    }
}
