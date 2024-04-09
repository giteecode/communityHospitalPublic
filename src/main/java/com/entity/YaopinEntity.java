package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 药品
 *
 * @author 
 * @email
 */
@TableName("yaopin")
public class YaopinEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public YaopinEntity() {

	}

	public YaopinEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 药品编号
     */
    @TableField(value = "yaopin_uuid_number")

    private String yaopinUuidNumber;


    /**
     * 药品名称
     */
    @TableField(value = "yaopin_name")

    private String yaopinName;


    /**
     * 药品价格
     */
    @TableField(value = "yaopin_new_money")

    private Double yaopinNewMoney;


    /**
     * 药品库存
     */
    @TableField(value = "yaopin_kucun_number")

    private Integer yaopinKucunNumber;


    /**
     * 主要药效
     */
    @TableField(value = "yaopin_zuoyong")

    private String yaopinZuoyong;


    /**
     * 副作用
     */
    @TableField(value = "fuzuoyong")

    private String fuzuoyong;


    /**
     * 药品详情
     */
    @TableField(value = "yaopin_content")

    private String yaopinContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：药品编号
	 */
    public String getYaopinUuidNumber() {
        return yaopinUuidNumber;
    }


    /**
	 * 获取：药品编号
	 */

    public void setYaopinUuidNumber(String yaopinUuidNumber) {
        this.yaopinUuidNumber = yaopinUuidNumber;
    }
    /**
	 * 设置：药品名称
	 */
    public String getYaopinName() {
        return yaopinName;
    }


    /**
	 * 获取：药品名称
	 */

    public void setYaopinName(String yaopinName) {
        this.yaopinName = yaopinName;
    }
    /**
	 * 设置：药品价格
	 */
    public Double getYaopinNewMoney() {
        return yaopinNewMoney;
    }


    /**
	 * 获取：药品价格
	 */

    public void setYaopinNewMoney(Double yaopinNewMoney) {
        this.yaopinNewMoney = yaopinNewMoney;
    }
    /**
	 * 设置：药品库存
	 */
    public Integer getYaopinKucunNumber() {
        return yaopinKucunNumber;
    }


    /**
	 * 获取：药品库存
	 */

    public void setYaopinKucunNumber(Integer yaopinKucunNumber) {
        this.yaopinKucunNumber = yaopinKucunNumber;
    }
    /**
	 * 设置：主要药效
	 */
    public String getYaopinZuoyong() {
        return yaopinZuoyong;
    }


    /**
	 * 获取：主要药效
	 */

    public void setYaopinZuoyong(String yaopinZuoyong) {
        this.yaopinZuoyong = yaopinZuoyong;
    }
    /**
	 * 设置：副作用
	 */
    public String getFuzuoyong() {
        return fuzuoyong;
    }


    /**
	 * 获取：副作用
	 */

    public void setFuzuoyong(String fuzuoyong) {
        this.fuzuoyong = fuzuoyong;
    }
    /**
	 * 设置：药品详情
	 */
    public String getYaopinContent() {
        return yaopinContent;
    }


    /**
	 * 获取：药品详情
	 */

    public void setYaopinContent(String yaopinContent) {
        this.yaopinContent = yaopinContent;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Yaopin{" +
            "id=" + id +
            ", yaopinUuidNumber=" + yaopinUuidNumber +
            ", yaopinName=" + yaopinName +
            ", yaopinNewMoney=" + yaopinNewMoney +
            ", yaopinKucunNumber=" + yaopinKucunNumber +
            ", yaopinZuoyong=" + yaopinZuoyong +
            ", fuzuoyong=" + fuzuoyong +
            ", yaopinContent=" + yaopinContent +
            ", createTime=" + createTime +
        "}";
    }
}
