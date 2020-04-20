package com.jl.service;

import java.util.List;

import com.jl.common.pojo.EasyUITreeNode;

public interface ItemCatService {
	
	List<EasyUITreeNode> getItemCatList(long parentId);
}
