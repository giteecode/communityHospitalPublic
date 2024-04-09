package com.dao;

import com.entity.YishengEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YishengView;

/**
 * 医生 Dao 接口
 *
 * @author 
 */
public interface YishengDao extends BaseMapper<YishengEntity> {

   List<YishengView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
