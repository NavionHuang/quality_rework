package com.lifesense.quality;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.lifesense.quality.api.ProductInfo;
import com.lifesense.quality.domain.Product;
import com.lifesense.quality.domain.SnInfo;
import com.lifesense.quality.service.HttpAPIService;

public class HttpTest {

	public static void main(String[] args) {
		HttpAPIService httpAPIService = new HttpAPIService();
		String str = "";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 3260);
		try {
			str = httpAPIService.doGet("http://localhost:8080/quality/api/v3/querySnInfo",map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(str);
		
		List<SnInfo> snInfos =new Gson().fromJson(str, new TypeToken<List<SnInfo>>() {
		}.getType());
		for(SnInfo sninfo:snInfos) {
			System.out.println(sninfo.getId());	
		}
		/*if (!StringUtils.isEmpty(str)) {
			Product product = new Gson().fromJson(str, new TypeToken<Product>() {
			}.getType());
			System.out.println(product.getBbsn() + product.getSheetPo());
		}*/
			
			
	}

}
