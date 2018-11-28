package com.lifesense.quality.dao;

import java.util.Date;
import java.util.List;

import com.lifesense.quality.domain.ProcessRecord;
import com.lifesense.quality.domain.Product;

public interface ReworkDao {
	String queryCreateTimeByBlsn(String blsn);
	Product queryProductByCreateTime(String createTime);
	List<ProcessRecord> queryProcessRecordByProductId(Long id);
}
