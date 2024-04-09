package com.dao;

import com.entity.YaopinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YaopinView;

/**
 * 药品 Dao 接口
 *
 * @author 
 */
public interface YaopinDao extends BaseMapper<YaopinEntity> {

   List<YaopinView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
