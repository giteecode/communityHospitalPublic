package com.dao;

import com.entity.QiantaiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.QiantaiView;

/**
 * 前台 Dao 接口
 *
 * @author 
 */
public interface QiantaiDao extends BaseMapper<QiantaiEntity> {

   List<QiantaiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
