package com.jl.content.service;

import java.util.List;

import com.jl.common.pojo.EasyUIDataGridResult;
import com.jl.common.pojo.TaotaoResult;
import com.jl.pojo.TbContent;



public interface ContentService {
		/**
		 * 插入内容表
		 */
	public TaotaoResult saveContent(TbContent content);
	/**
	 * 内容管理查询 ，分页
	 */
	public EasyUIDataGridResult getContentList(Long categoryId,Integer page,Integer rows);
	/**
	 * 内容管理编辑
	 */
	public TaotaoResult updateContent(Long id,TbContent content);
	
	/**
	 * 内容删除
	 */
	public TaotaoResult deleteContent(String [] ids);
	
	/**
	 * 根据内容分类id查询低下的内容列表
	 */
	public List<TbContent> getContentListByCatId(Long categoryId);
}
