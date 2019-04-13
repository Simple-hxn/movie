package com.mv.sys.service;

import java.util.List;
import com.mv.common.vo.Node;

public interface SysCategoryService {
	List<Node> findCategory();
	Node findObjectById(Integer id);
}
