package com.dao;

import com.entity.YonghuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YonghuView;

/**
 * 用户 Dao 接口
 *
 * @author 
 */
public interface YonghuDao extends BaseMapper<YonghuEntity> {

   List<YonghuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
