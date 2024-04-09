package com.dao;

import com.entity.JiatingyishengEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JiatingyishengView;

/**
 * 家庭医生 Dao 接口
 *
 * @author 
 */
public interface JiatingyishengDao extends BaseMapper<JiatingyishengEntity> {

   List<JiatingyishengView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
