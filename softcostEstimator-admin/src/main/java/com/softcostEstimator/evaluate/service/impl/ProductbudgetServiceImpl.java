package com.softcostEstimator.evaluate.service.impl;

import java.util.List;

import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softcostEstimator.evaluate.mapper.ProductbudgetMapper;
import com.softcostEstimator.evaluate.domain.Productbudget;
import com.softcostEstimator.evaluate.service.IProductbudgetService;

import javax.annotation.Resource;

/**
 * 综合评估Service业务层处理
 *
 * @author ruoyi
 * @date 2024-11-15
 */
@Service
public class ProductbudgetServiceImpl implements IProductbudgetService
{
    @Resource
    private ProductbudgetMapper productbudgetMapper;

    /**
     * 查询综合评估
     *
     * @param productID 综合评估主键
     * @return 综合评估
     */
    @Override
    public Productbudget selectProductbudgetByProductID(Long productID)
    {
        return productbudgetMapper.selectProductbudgetByProductID(productID);
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
     * @param productIDs 需要删除的综合评估主键
     * @return 结果
     */
    @Override
    public int deleteProductbudgetByProductIDs(Long[] productIDs)
    {
        return productbudgetMapper.deleteProductbudgetByProductIDs(productIDs);
    }

    /**
     * 删除综合评估信息
     *
     * @param productID 综合评估主键
     * @return 结果
     */
    @Override
    public int deleteProductbudgetByProductID(Long productID)
    {
        return productbudgetMapper.deleteProductbudgetByProductID(productID);
    }
    @Override
    public String getJson(Productbudget productbudget) {

        String json = JSONUtil.toJsonStr(productbudget);
        return json;
    }

}
