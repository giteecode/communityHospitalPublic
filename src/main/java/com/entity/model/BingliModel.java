package com.entity.model;

import com.entity.BingliEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 病例信息
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class BingliModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 病例编号
     */
    private String bingliUuidNumber;


    /**
     * 病例名称
     */
    private String bingliName;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 病情
     */
    private String bingliBingqing;


    /**
     * 检查项目
     */
    private String jianchaxiangmu;


    /**
     * 检查结果
     */
    private String jianchajieguo;


    /**
     * 药单
     */
    private String yaodanText;


    /**
     * 病例附件
     */
    private String bingliFile;


    /**
     * 日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date bingliTime;


    /**
     * 医生
     */
    private Integer yishengId;


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
	 * 获取：病例编号
	 */
    public String getBingliUuidNumber() {
        return bingliUuidNumber;
    }


    /**
	 * 设置：病例编号
	 */
    public void setBingliUuidNumber(String bingliUuidNumber) {
        this.bingliUuidNumber = bingliUuidNumber;
    }
    /**
	 * 获取：病例名称
	 */
    public String getBingliName() {
        return bingliName;
    }


    /**
	 * 设置：病例名称
	 */
    public void setBingliName(String bingliName) {
        this.bingliName = bingliName;
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
	 * 获取：病情
	 */
    public String getBingliBingqing() {
        return bingliBingqing;
    }


    /**
	 * 设置：病情
	 */
    public void setBingliBingqing(String bingliBingqing) {
        this.bingliBingqing = bingliBingqing;
    }
    /**
	 * 获取：检查项目
	 */
    public String getJianchaxiangmu() {
        return jianchaxiangmu;
    }


    /**
	 * 设置：检查项目
	 */
    public void setJianchaxiangmu(String jianchaxiangmu) {
        this.jianchaxiangmu = jianchaxiangmu;
    }
    /**
	 * 获取：检查结果
	 */
    public String getJianchajieguo() {
        return jianchajieguo;
    }


    /**
	 * 设置：检查结果
	 */
    public void setJianchajieguo(String jianchajieguo) {
        this.jianchajieguo = jianchajieguo;
    }
    /**
	 * 获取：药单
	 */
    public String getYaodanText() {
        return yaodanText;
    }


    /**
	 * 设置：药单
	 */
    public void setYaodanText(String yaodanText) {
        this.yaodanText = yaodanText;
    }
    /**
	 * 获取：病例附件
	 */
    public String getBingliFile() {
        return bingliFile;
    }


    /**
	 * 设置：病例附件
	 */
    public void setBingliFile(String bingliFile) {
        this.bingliFile = bingliFile;
    }
    /**
	 * 获取：日期
	 */
    public Date getBingliTime() {
        return bingliTime;
    }


    /**
	 * 设置：日期
	 */
    public void setBingliTime(Date bingliTime) {
        this.bingliTime = bingliTime;
    }
    /**
	 * 获取：医生
	 */
    public Integer getYishengId() {
        return yishengId;
    }


    /**
	 * 设置：医生
	 */
    public void setYishengId(Integer yishengId) {
        this.yishengId = yishengId;
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
