package com.dao;

import com.entity.JiuzhenEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JiuzhenView;

/**
 * 就诊信息 Dao 接口
 *
 * @author 
 */
public interface JiuzhenDao extends BaseMapper<JiuzhenEntity> {

   List<JiuzhenView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
