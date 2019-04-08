package com.shopping.service.impl;

import com.shopping.common.ServerResponse;
import com.shopping.dao.CategoryMapper;
import com.shopping.pojo.Category;
import com.shopping.service.ICategoryService;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iCategoryService")
@XSlf4j
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryManager;
    @Override
    public ServerResponse addCategory(String categoryName, Integer parentId) {
        return null;
    }

    @Override
    public ServerResponse updateCategoryName(Integer categoryId, String categoryName) {
        return null;
    }

    @Override
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId) {
        return null;
    }

    @Override
    public ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId) {
        return null;
    }
}
