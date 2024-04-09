package com.entity.view;

import com.entity.YaopinEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 药品
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("yaopin")
public class YaopinView extends YaopinEntity implements Serializable {
    private static final long serialVersionUID = 1L;




	public YaopinView() {

	}

	public YaopinView(YaopinEntity yaopinEntity) {
		try {
			BeanUtils.copyProperties(this, yaopinEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}















}
