package com.mv.sys.service.impl;

import com.mv.sys.dao.*;
import com.mv.sys.service.SysCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mv.common.vo.Node;
import java.util.List;

/**
 * 电影分类
 */
@Service
public class SysCategoryServiceImpl implements SysCategoryService {
	@Autowired
	private SysCategoryDao sysCateGoryDao;
	@Override
	public List<Node> findCategory() {
		return sysCateGoryDao.findCategorys();
	}
	@Override
	public Node findObjectById(Integer id) {
		return sysCateGoryDao.findObjectById(id);
	}

}
