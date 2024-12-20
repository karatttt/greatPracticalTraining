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
    @Override
    public String getJson(Productbudget productbudget) {

        String json = JSONUtil.toJsonStr(productbudget);
        return json;
    }

    @Override
    public Productbudget selectProductbudgetByProductID2(Long projectId) {
        Productbudget productbudget = new Productbudget();
        productbudget.setSL(1.5);
        productbudget.setSF(2.5);
        productbudget.setRSK(3.5);
        productbudget.setPDR(4.5);
        productbudget.setF(5.5);
        productbudget.setESDC(.5);
        productbudget.setDNC(8.5);
        productbudget.setAT(9.5);
        productbudget.setQR(10.5);
        productbudget.setBD(11.5);
        productbudget.setDT(12.5);
        productbudget.setSDC(1.5);
        return productbudget;
    }

}
