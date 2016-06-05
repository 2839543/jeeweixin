package com.wxcms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxcms.domain.ShakearoundPage;
import com.wxcms.mapper.ShakearoundPageDao;
import com.wxcms.service.ShakearoundPageService;


@Service
public class ShakearoundPageServiceImpl implements ShakearoundPageService{

	@Autowired
	private ShakearoundPageDao entityDao;

	public ShakearoundPage getById(String id){
		return entityDao.getById(id);
	}
	
	public ShakearoundPage getByPageId(String pageId,Date createTime){
		return entityDao.getByPageId(pageId, createTime);
	}

	public List<ShakearoundPage> listForPage(ShakearoundPage searchEntity){
		return entityDao.listForPage(searchEntity);
	}

	public void add(ShakearoundPage entity){
		entityDao.add(entity);
	}

	public void update(ShakearoundPage entity){
		entityDao.update(entity);
	}

	public void delete(ShakearoundPage entity){
		entityDao.delete(entity);
	} 

}