package com.lifesense.quality.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.lifesense.quality.dao.ReworkDao;
import com.lifesense.quality.domain.ProcessRecord;
import com.lifesense.quality.domain.Product;
import com.lifesense.quality.service.ReworkService;

@Service
public class ReworkServiceImpl implements ReworkService {
	@Autowired
	private ReworkDao reworkDao;

	

	@Override
	public List<ProcessRecord> queryProcessRecordByProductId(Long id) {
		return reworkDao.queryProcessRecordByProductId(id);
	}



	@Override
	public String queryCreateTimeByBlsn(String blsn) {
		return reworkDao.queryCreateTimeByBlsn(blsn);
	}



	@Override
	public Product queryProductByCreateTime(String createTime) {
		return reworkDao.queryProductByCreateTime(createTime);
	}

	

}
