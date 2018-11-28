package com.lifesense.quality.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lifesense.quality.domain.ProcessRecord;
import com.lifesense.quality.domain.Product;
import com.lifesense.quality.domain.SnInfo;
import com.lifesense.quality.facade.ProcessRecordServiceFacadeImpl;
import com.lifesense.quality.facade.ProductServiceFacadeImpl;
import com.lifesense.quality.service.HttpAPIService;
import com.lifesense.quality.service.ReworkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 赵春定 on 2017/8/30.
 */
@RequestMapping("/product")
@Controller
public class ProductController {

	@Autowired
	private ProductServiceFacadeImpl productServiceFacade;
	@Autowired
	private ProcessRecordServiceFacadeImpl processRecordServiceFacade;
	@Autowired
	private HttpAPIService httpAPIService;
	@Autowired
	private ReworkService reworkService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	ModelAndView productList() {
		ModelAndView view = new ModelAndView();
		return view;
	}

	@RequestMapping(value = "/list_data", method = RequestMethod.POST)
	@ResponseBody
	Page listData(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit, Product entity) {
		PageInfo<Product> pageData = productServiceFacade.getPageData(entity, page, limit);
		return new Page("200", "查询成功", pageData.getTotal(), pageData.getList());
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	ModelAndView sheetProductList(Long id) {
		ModelAndView view = new ModelAndView();
		Product product = productServiceFacade.findProductById(id);
		List<ProcessRecord> processRecords = processRecordServiceFacade.findProcessRecord(id);
		view.addObject("product", product);
		view.addObject("processRecords", processRecords);
		return view;
	}

	/**
	 * 从生产系统中查询产品和操作记录
	 * 
	 * @param blsn
	 * @return
	 */
	@RequestMapping(value = "/detail_rework", method = RequestMethod.GET)
	ModelAndView sheetProductList_rework(String blsn) {
		ModelAndView view = new ModelAndView();
		List<ProcessRecord> processRecords = null;
		Product product = null;
		// 查询返工产品
		Product productByBlsn = productServiceFacade.findProductByBlsn(blsn);
		if (productByBlsn != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createTime = sdf.format(productByBlsn.getCreateTime());
			//根据创建时间查询正常产品
			product = reworkService.queryProductByCreateTime(createTime);
			if (product != null) {
				processRecords = reworkService.queryProcessRecordByProductId(product.getId());
			}
		}

		/*
		  if (!StringUtils.isEmpty(blsn)) { Product productByBlsn =
		  productServiceFacade.findProductByBlsn(blsn); if (productByBlsn != null) {
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); String
		  createTime = sdf.format(productByBlsn.getCreateTime()); Map<String, Object>
		  map = new HashMap<String, Object>(); // 根据创建时间查询产品 map.put("createTime",
		  createTime); String json = null; try { json = httpAPIService.doPost(
		  "http://127.0.0.1:8080/quality/api/v3/queryProductByCreateTime", map); }
		  catch (Exception e) { e.printStackTrace(); } if (!StringUtils.isEmpty(json))
		  { product = new GsonBuilder() .setDateFormat("yyyy-MM-dd HH:mm:ss")
		  .create().fromJson(json, new TypeToken<Product>() { }.getType()); } // 查询操作记录
		 if (product != null) { map.clear(); map.put("id", product.getId()); try {
		  json = httpAPIService.doPost(
		  "http://127.0.0.1:8080/quality/api/v3/queryProductById", map); } catch
		  (Exception e) { e.printStackTrace(); } if (!StringUtils.isEmpty(json) &&
		  !"[]".equals(json)) { processRecords = new GsonBuilder()
		  .setDateFormat("yyyy-MM-dd HH:mm:ss") .create().fromJson(json, new
		  TypeToken<List<ProcessRecord>>() { }.getType()); } }
		  
		  } }
		 */
		view.addObject("product", product);
		view.addObject("processRecords", processRecords);
		return view;
	}

}
