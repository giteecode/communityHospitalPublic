package com.entity.model;

import com.entity.DictionaryEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 字典表
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class DictionaryModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 字段
     */
    private String dicCode;


    /**
     * 字段名
     */
    private String dicName;


    /**
     * 编码
     */
    private Integer codeIndex;


    /**
     * 编码名字
     */
    private String indexName;


    /**
     * 父字段id
     */
    private Integer superId;


    /**
     * 备注
     */
    private String beizhu;


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
	 * 获取：字段
	 */
    public String getDicCode() {
        return dicCode;
    }


    /**
	 * 设置：字段
	 */
    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }
    /**
	 * 获取：字段名
	 */
    public String getDicName() {
        return dicName;
    }


    /**
	 * 设置：字段名
	 */
    public void setDicName(String dicName) {
        this.dicName = dicName;
    }
    /**
	 * 获取：编码
	 */
    public Integer getCodeIndex() {
        return codeIndex;
    }


    /**
	 * 设置：编码
	 */
    public void setCodeIndex(Integer codeIndex) {
        this.codeIndex = codeIndex;
    }
    /**
	 * 获取：编码名字
	 */
    public String getIndexName() {
        return indexName;
    }


    /**
	 * 设置：编码名字
	 */
    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }
    /**
	 * 获取：父字段id
	 */
    public Integer getSuperId() {
        return superId;
    }


    /**
	 * 设置：父字段id
	 */
    public void setSuperId(Integer superId) {
        this.superId = superId;
    }
    /**
	 * 获取：备注
	 */
    public String getBeizhu() {
        return beizhu;
    }


    /**
	 * 设置：备注
	 */
    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
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
