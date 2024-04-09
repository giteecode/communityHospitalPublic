
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 病例信息
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/bingli")
public class BingliController {
    private static final Logger logger = LoggerFactory.getLogger(BingliController.class);

    @Autowired
    private BingliService bingliService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private YishengService yishengService;
    @Autowired
    private YonghuService yonghuService;

    @Autowired
    private QiantaiService qiantaiService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("前台".equals(role))
            params.put("qiantaiId",request.getSession().getAttribute("userId"));
        else if("医生".equals(role))
            params.put("yishengId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = bingliService.queryPage(params);

        //字典表数据转换
        List<BingliView> list =(List<BingliView>)page.getList();
        for(BingliView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        BingliEntity bingli = bingliService.selectById(id);
        if(bingli !=null){
            //entity转view
            BingliView view = new BingliView();
            BeanUtils.copyProperties( bingli , view );//把实体数据重构到view中

                //级联表
                YishengEntity yisheng = yishengService.selectById(bingli.getYishengId());
                if(yisheng != null){
                    BeanUtils.copyProperties( yisheng , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYishengId(yisheng.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(bingli.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody BingliEntity bingli, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,bingli:{}",this.getClass().getName(),bingli.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            bingli.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("医生".equals(role))
            bingli.setYishengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<BingliEntity> queryWrapper = new EntityWrapper<BingliEntity>()
            .eq("bingli_uuid_number", bingli.getBingliUuidNumber())
            .eq("bingli_name", bingli.getBingliName())
            .eq("yonghu_id", bingli.getYonghuId())
            .eq("bingli_bingqing", bingli.getBingliBingqing())
            .eq("jianchaxiangmu", bingli.getJianchaxiangmu())
            .eq("jianchajieguo", bingli.getJianchajieguo())
            .eq("yaodan_text", bingli.getYaodanText())
            .eq("bingli_time", new SimpleDateFormat("yyyy-MM-dd").format(bingli.getBingliTime()))
            .eq("yisheng_id", bingli.getYishengId())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BingliEntity bingliEntity = bingliService.selectOne(queryWrapper);
        if(bingliEntity==null){
            bingli.setCreateTime(new Date());
            bingliService.insert(bingli);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody BingliEntity bingli, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,bingli:{}",this.getClass().getName(),bingli.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            bingli.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("医生".equals(role))
//            bingli.setYishengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<BingliEntity> queryWrapper = new EntityWrapper<BingliEntity>()
            .notIn("id",bingli.getId())
            .andNew()
            .eq("bingli_uuid_number", bingli.getBingliUuidNumber())
            .eq("bingli_name", bingli.getBingliName())
            .eq("yonghu_id", bingli.getYonghuId())
            .eq("bingli_bingqing", bingli.getBingliBingqing())
            .eq("jianchaxiangmu", bingli.getJianchaxiangmu())
            .eq("jianchajieguo", bingli.getJianchajieguo())
            .eq("yaodan_text", bingli.getYaodanText())
            .eq("bingli_time", new SimpleDateFormat("yyyy-MM-dd").format(bingli.getBingliTime()))
            .eq("yisheng_id", bingli.getYishengId())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BingliEntity bingliEntity = bingliService.selectOne(queryWrapper);
        if("".equals(bingli.getBingliFile()) || "null".equals(bingli.getBingliFile())){
                bingli.setBingliFile(null);
        }
        if(bingliEntity==null){
            bingliService.updateById(bingli);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        bingliService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<BingliEntity> bingliList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            BingliEntity bingliEntity = new BingliEntity();
//                            bingliEntity.setBingliUuidNumber(data.get(0));                    //病例编号 要改的
//                            bingliEntity.setBingliName(data.get(0));                    //病例名称 要改的
//                            bingliEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            bingliEntity.setBingliBingqing(data.get(0));                    //病情 要改的
//                            bingliEntity.setJianchaxiangmu(data.get(0));                    //检查项目 要改的
//                            bingliEntity.setJianchajieguo(data.get(0));                    //检查结果 要改的
//                            bingliEntity.setYaodanText(data.get(0));                    //药单 要改的
//                            bingliEntity.setBingliFile(data.get(0));                    //病例附件 要改的
//                            bingliEntity.setBingliTime(new Date(data.get(0)));          //日期 要改的
//                            bingliEntity.setYishengId(Integer.valueOf(data.get(0)));   //医生 要改的
//                            bingliEntity.setCreateTime(date);//时间
                            bingliList.add(bingliEntity);


                            //把要查询是否重复的字段放入map中
                                //病例编号
                                if(seachFields.containsKey("bingliUuidNumber")){
                                    List<String> bingliUuidNumber = seachFields.get("bingliUuidNumber");
                                    bingliUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> bingliUuidNumber = new ArrayList<>();
                                    bingliUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("bingliUuidNumber",bingliUuidNumber);
                                }
                        }

                        //查询是否重复
                         //病例编号
                        List<BingliEntity> bingliEntities_bingliUuidNumber = bingliService.selectList(new EntityWrapper<BingliEntity>().in("bingli_uuid_number", seachFields.get("bingliUuidNumber")));
                        if(bingliEntities_bingliUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(BingliEntity s:bingliEntities_bingliUuidNumber){
                                repeatFields.add(s.getBingliUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [病例编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        bingliService.insertBatch(bingliList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
