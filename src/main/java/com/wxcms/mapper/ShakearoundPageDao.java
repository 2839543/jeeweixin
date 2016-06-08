package com.wxcms.mapper;

import java.util.Date;
import java.util.List;

import com.wxcms.domain.ShakearoundPage;

public interface ShakearoundPageDao {

	public ShakearoundPage getById(String id);

	public ShakearoundPage getByPageId(String pageId,Date createTime);
	
	public List<ShakearoundPage> getByDate(Integer date);

	public List<ShakearoundPage> listForPage(ShakearoundPage searchEntity);

	public void add(ShakearoundPage entity);

	public void update(ShakearoundPage entity);

	public void delete(ShakearoundPage entity);

}