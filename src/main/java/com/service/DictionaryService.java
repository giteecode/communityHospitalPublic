package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DictionaryEntity;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * 字典表 服务类
 */
public interface DictionaryService extends IService<DictionaryEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
      /**
      * 字典表转换
      * @param obj
      */
     void dictionaryConvert(Object obj, HttpServletRequest request);
}