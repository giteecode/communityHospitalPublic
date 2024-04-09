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
 * 就诊信息
 *
 * @author 
 * @email
 */
@TableName("jiuzhen")
public class JiuzhenEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JiuzhenEntity() {

	}

	public JiuzhenEntity(T t) {
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
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 就诊号
     */
    @TableField(value = "jiuzhen_jiuzhen")

    private String jiuzhenJiuzhen;


    /**
     * 就诊费用
     */
    @TableField(value = "jiuzhen_feiyong")

    private String jiuzhenFeiyong;


    /**
     * 科室
     */
    @TableField(value = "keshi_types")

    private Integer keshiTypes;


    /**
     * 日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 挂号备注
     */
    @TableField(value = "jiuzhen_text")

    private String jiuzhenText;


    /**
     * 挂号详情
     */
    @TableField(value = "jiuzhen_content")

    private String jiuzhenContent;


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
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：就诊号
	 */
    public String getJiuzhenJiuzhen() {
        return jiuzhenJiuzhen;
    }


    /**
	 * 获取：就诊号
	 */

    public void setJiuzhenJiuzhen(String jiuzhenJiuzhen) {
        this.jiuzhenJiuzhen = jiuzhenJiuzhen;
    }
    /**
	 * 设置：就诊费用
	 */
    public String getJiuzhenFeiyong() {
        return jiuzhenFeiyong;
    }


    /**
	 * 获取：就诊费用
	 */

    public void setJiuzhenFeiyong(String jiuzhenFeiyong) {
        this.jiuzhenFeiyong = jiuzhenFeiyong;
    }
    /**
	 * 设置：科室
	 */
    public Integer getKeshiTypes() {
        return keshiTypes;
    }


    /**
	 * 获取：科室
	 */

    public void setKeshiTypes(Integer keshiTypes) {
        this.keshiTypes = keshiTypes;
    }
    /**
	 * 设置：日期
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：日期
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：挂号备注
	 */
    public String getJiuzhenText() {
        return jiuzhenText;
    }


    /**
	 * 获取：挂号备注
	 */

    public void setJiuzhenText(String jiuzhenText) {
        this.jiuzhenText = jiuzhenText;
    }
    /**
	 * 设置：挂号详情
	 */
    public String getJiuzhenContent() {
        return jiuzhenContent;
    }


    /**
	 * 获取：挂号详情
	 */

    public void setJiuzhenContent(String jiuzhenContent) {
        this.jiuzhenContent = jiuzhenContent;
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
        return "Jiuzhen{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", jiuzhenJiuzhen=" + jiuzhenJiuzhen +
            ", jiuzhenFeiyong=" + jiuzhenFeiyong +
            ", keshiTypes=" + keshiTypes +
            ", insertTime=" + insertTime +
            ", jiuzhenText=" + jiuzhenText +
            ", jiuzhenContent=" + jiuzhenContent +
            ", createTime=" + createTime +
        "}";
    }
}
