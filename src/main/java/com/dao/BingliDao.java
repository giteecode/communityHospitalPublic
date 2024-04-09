package com.dao;

import com.entity.BingliEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.BingliView;

/**
 * 病例信息 Dao 接口
 *
 * @author 
 */
public interface BingliDao extends BaseMapper<BingliEntity> {

   List<BingliView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
