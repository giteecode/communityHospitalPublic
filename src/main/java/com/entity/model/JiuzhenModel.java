package com.entity.model;

import com.entity.JiuzhenEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 就诊信息
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JiuzhenModel implements Serializable {
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
     * 就诊号
     */
    private String jiuzhenJiuzhen;


    /**
     * 就诊费用
     */
    private String jiuzhenFeiyong;


    /**
     * 科室
     */
    private Integer keshiTypes;


    /**
     * 日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 挂号备注
     */
    private String jiuzhenText;


    /**
     * 挂号详情
     */
    private String jiuzhenContent;


    /**
     * 创建时间 show1 show2 photoShow
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
	 * 获取：就诊号
	 */
    public String getJiuzhenJiuzhen() {
        return jiuzhenJiuzhen;
    }


    /**
	 * 设置：就诊号
	 */
    public void setJiuzhenJiuzhen(String jiuzhenJiuzhen) {
        this.jiuzhenJiuzhen = jiuzhenJiuzhen;
    }
    /**
	 * 获取：就诊费用
	 */
    public String getJiuzhenFeiyong() {
        return jiuzhenFeiyong;
    }


    /**
	 * 设置：就诊费用
	 */
    public void setJiuzhenFeiyong(String jiuzhenFeiyong) {
        this.jiuzhenFeiyong = jiuzhenFeiyong;
    }
    /**
	 * 获取：科室
	 */
    public Integer getKeshiTypes() {
        return keshiTypes;
    }


    /**
	 * 设置：科室
	 */
    public void setKeshiTypes(Integer keshiTypes) {
        this.keshiTypes = keshiTypes;
    }
    /**
	 * 获取：日期
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：日期
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：挂号备注
	 */
    public String getJiuzhenText() {
        return jiuzhenText;
    }


    /**
	 * 设置：挂号备注
	 */
    public void setJiuzhenText(String jiuzhenText) {
        this.jiuzhenText = jiuzhenText;
    }
    /**
	 * 获取：挂号详情
	 */
    public String getJiuzhenContent() {
        return jiuzhenContent;
    }


    /**
	 * 设置：挂号详情
	 */
    public void setJiuzhenContent(String jiuzhenContent) {
        this.jiuzhenContent = jiuzhenContent;
    }
    /**
	 * 获取：创建时间 show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
