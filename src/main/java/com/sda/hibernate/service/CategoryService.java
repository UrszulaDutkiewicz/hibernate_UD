package com.sda.hibernate.service;

import com.sda.hibernate.dao.CategoryDao;
import com.sda.hibernate.entity.Category;

public class CategoryService {

    private static CategoryDao categoryDao;

    public  CategoryService(){
        categoryDao = new CategoryDao();
    }

    public void save(Category category){
        categoryDao.openCurrentSession();
        categoryDao.save(category);
        categoryDao.closeCurrentSession();
    }



}
