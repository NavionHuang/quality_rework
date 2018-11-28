package com.lifesense.quality.facade;

import com.github.pagehelper.PageInfo;
import com.lifesense.quality.base.BaseExample;
import com.lifesense.quality.base.IBaseService;
import com.lifesense.quality.criteria.ProductGeneratedCriteria;
import com.lifesense.quality.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther zcd
 * @Date
 */
@Service
public class ProductServiceFacadeImpl {

    @Autowired
    private IBaseService<Product> productIBaseService;

    /**
     * 根据产品sn查询
     *
     * @param sn 产品sn
     * @return
     */
    public Product findProductByCpsn(String sn) {
        BaseExample<Product> example = new BaseExample<>();
        ProductGeneratedCriteria criteria = new ProductGeneratedCriteria();
        criteria.andCpsnEqualTo(sn);
        example.or(criteria);
        List<Product> products = productIBaseService.findByCriteria(example);
        if (products != null && !products.isEmpty()) {
            return products.get(0);
        }
        return null;
    }

    /**
     * 根据玻璃sn 查询
     *
     * @param sn 玻璃sn
     * @return
     */
    public Product findProductByBlsn(String sn) {
        BaseExample<Product> example = new BaseExample<>();
        ProductGeneratedCriteria criteria = new ProductGeneratedCriteria();
        criteria.andBlsnEqualTo(sn);
        example.or(criteria);
        List<Product> products = productIBaseService.findByCriteria(example);
        if (products != null && !products.isEmpty()) {
            return products.get(0);
        }
        return null;
    }

    /**
     * 根据绑板sn 查询
     *
     * @param sn 绑板sn
     * @return
     */
    public Product findProductByBbsn(String sn) {
        BaseExample<Product> example = new BaseExample<>();
        ProductGeneratedCriteria criteria = new ProductGeneratedCriteria();
        criteria.andBbsnEqualTo(sn);
        example.or(criteria);
        List<Product> products = productIBaseService.findByCriteria(example);
        if (products != null && !products.isEmpty()) {
            return products.get(0);
        }
        return null;
    }

    /**
     * 根据绑板sn 查询
     *
     * @param sn 绑板sn
     * @return
     */
    public Product findProductByCgqsn(String sn) {
        BaseExample<Product> example = new BaseExample<>();
        ProductGeneratedCriteria criteria = new ProductGeneratedCriteria();
        criteria.andCgqsnEqualTo(sn);
        example.or(criteria);
        List<Product> products = productIBaseService.findByCriteria(example);
        if (products != null && !products.isEmpty()) {
            return products.get(0);
        }
        return null;
    }


    /**
     * 分页查询
     *
     * @param record
     * @param pageNum
     * @param limit
     * @return
     */
    public PageInfo<Product> getPageData(Product record, int pageNum, int limit) {
        return productIBaseService.queryPage(record, pageNum, limit);

    }


    /**
     * 插入或更新，根据Id判断
     *
     * @param product
     * @return
     */
    public int saveOrUpdate(Product product) {
        if (product.getId() != null) {
            return productIBaseService.edit(product);
        } else {
            return productIBaseService.insert(product);
        }
    }

    public Product findProductById(Long productId) {
        return productIBaseService.fetchById(productId);
    }

    public int countBySheetPo(String sheetPo) {
        Product product = new Product();
        product.setSheetPo(sheetPo);
        return productIBaseService.count(product);
    }

    public void deleteById(Long productId) {
        productIBaseService.deleteById(productId);
    }
}
