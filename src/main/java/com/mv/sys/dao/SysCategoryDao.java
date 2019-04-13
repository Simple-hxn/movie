package com.mv.sys.dao;

import java.util.List;
import com.mv.common.vo.Node;

public interface SysCategoryDao {
	List<Node> findCategorys();
	Node findObjectById(Integer id);
}
