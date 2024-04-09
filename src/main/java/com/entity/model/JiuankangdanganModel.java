package com.entity.model;

import com.entity.JiuankangdanganEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 健康档案
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JiuankangdanganModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 档案标题
     */
    private String jiuankangdanganName;


    /**
     * 其他成员
     */
    private String jiuankangdanganQita;


    /**
     * 档案单位
     */
    private Integer jiuankangdanganTypes;


    /**
     * 记录时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 健康状况
     */
    private String jiuankangdanganContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：档案标题
	 */
    public String getJiuankangdanganName() {
        return jiuankangdanganName;
    }


    /**
	 * 设置：档案标题
	 */
    public void setJiuankangdanganName(String jiuankangdanganName) {
        this.jiuankangdanganName = jiuankangdanganName;
    }
    /**
	 * 获取：其他成员
	 */
    public String getJiuankangdanganQita() {
        return jiuankangdanganQita;
    }


    /**
	 * 设置：其他成员
	 */
    public void setJiuankangdanganQita(String jiuankangdanganQita) {
        this.jiuankangdanganQita = jiuankangdanganQita;
    }
    /**
	 * 获取：档案单位
	 */
    public Integer getJiuankangdanganTypes() {
        return jiuankangdanganTypes;
    }


    /**
	 * 设置：档案单位
	 */
    public void setJiuankangdanganTypes(Integer jiuankangdanganTypes) {
        this.jiuankangdanganTypes = jiuankangdanganTypes;
    }
    /**
	 * 获取：记录时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：记录时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：健康状况
	 */
    public String getJiuankangdanganContent() {
        return jiuankangdanganContent;
    }


    /**
	 * 设置：健康状况
	 */
    public void setJiuankangdanganContent(String jiuankangdanganContent) {
        this.jiuankangdanganContent = jiuankangdanganContent;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
