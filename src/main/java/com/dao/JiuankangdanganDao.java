package com.dao;

import com.entity.JiuankangdanganEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JiuankangdanganView;

/**
 * 健康档案 Dao 接口
 *
 * @author 
 */
public interface JiuankangdanganDao extends BaseMapper<JiuankangdanganEntity> {

   List<JiuankangdanganView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
