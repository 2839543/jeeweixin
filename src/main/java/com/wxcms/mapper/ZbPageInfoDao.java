package com.wxcms.mapper;

import java.util.List;

import com.wxcms.domain.ZbPageInfo;

public interface ZbPageInfoDao {

	public ZbPageInfo getById(String id);

	public ZbPageInfo getByPageId(String pageId);

	public List<ZbPageInfo> listForPage(ZbPageInfo searchEntity);

	public void add(ZbPageInfo entity);

	public void update(ZbPageInfo entity);

	public void delete(ZbPageInfo entity);

}