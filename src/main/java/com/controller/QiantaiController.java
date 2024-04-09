
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
 * 前台
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/qiantai")
public class QiantaiController {
    private static final Logger logger = LoggerFactory.getLogger(QiantaiController.class);

    @Autowired
    private QiantaiService qiantaiService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private YishengService yishengService;


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
        params.put("qiantaiDeleteStart",1);params.put("qiantaiDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = qiantaiService.queryPage(params);

        //字典表数据转换
        List<QiantaiView> list =(List<QiantaiView>)page.getList();
        for(QiantaiView c:list){
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
        QiantaiEntity qiantai = qiantaiService.selectById(id);
        if(qiantai !=null){
            //entity转view
            QiantaiView view = new QiantaiView();
            BeanUtils.copyProperties( qiantai , view );//把实体数据重构到view中

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
    public R save(@RequestBody QiantaiEntity qiantai, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,qiantai:{}",this.getClass().getName(),qiantai.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<QiantaiEntity> queryWrapper = new EntityWrapper<QiantaiEntity>()
            .eq("username", qiantai.getUsername())
            .or()
            .eq("qiantai_phone", qiantai.getQiantaiPhone())
            .andNew()
            .eq("qiantai_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        QiantaiEntity qiantaiEntity = qiantaiService.selectOne(queryWrapper);
        if(qiantaiEntity==null){
            qiantai.setQiantaiDelete(1);
            qiantai.setCreateTime(new Date());
            qiantai.setPassword("123456");
            qiantaiService.insert(qiantai);
            return R.ok();
        }else {
            return R.error(511,"账户或者联系方式已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody QiantaiEntity qiantai, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,qiantai:{}",this.getClass().getName(),qiantai.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<QiantaiEntity> queryWrapper = new EntityWrapper<QiantaiEntity>()
            .notIn("id",qiantai.getId())
            .andNew()
            .eq("username", qiantai.getUsername())
            .or()
            .eq("qiantai_phone", qiantai.getQiantaiPhone())
            .andNew()
            .eq("qiantai_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        QiantaiEntity qiantaiEntity = qiantaiService.selectOne(queryWrapper);
        if("".equals(qiantai.getQiantaiPhoto()) || "null".equals(qiantai.getQiantaiPhoto())){
                qiantai.setQiantaiPhoto(null);
        }
        if(qiantaiEntity==null){
            qiantaiService.updateById(qiantai);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者联系方式已经被使用");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<QiantaiEntity> list = new ArrayList<>();
        for(Integer id:ids){
            QiantaiEntity qiantaiEntity = new QiantaiEntity();
            qiantaiEntity.setId(id);
            qiantaiEntity.setQiantaiDelete(2);
            list.add(qiantaiEntity);
        }
        if(list != null && list.size() >0){
            qiantaiService.updateBatchById(list);
        }
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<QiantaiEntity> qiantaiList = new ArrayList<>();//上传的东西
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
                            QiantaiEntity qiantaiEntity = new QiantaiEntity();
//                            qiantaiEntity.setQiantaiUuidNumber(data.get(0));                    //工号 要改的
//                            qiantaiEntity.setUsername(data.get(0));                    //账户 要改的
//                            //qiantaiEntity.setPassword("123456");//密码
//                            qiantaiEntity.setQiantaiName(data.get(0));                    //前台姓名 要改的
//                            qiantaiEntity.setQiantaiPhoto("");//照片
//                            qiantaiEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            qiantaiEntity.setQiantaiPhone(data.get(0));                    //联系方式 要改的
//                            qiantaiEntity.setQiantaiEmail(data.get(0));                    //邮箱 要改的
//                            qiantaiEntity.setQiantaiDelete(1);//逻辑删除字段
//                            qiantaiEntity.setCreateTime(date);//时间
                            qiantaiList.add(qiantaiEntity);


                            //把要查询是否重复的字段放入map中
                                //工号
                                if(seachFields.containsKey("qiantaiUuidNumber")){
                                    List<String> qiantaiUuidNumber = seachFields.get("qiantaiUuidNumber");
                                    qiantaiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> qiantaiUuidNumber = new ArrayList<>();
                                    qiantaiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("qiantaiUuidNumber",qiantaiUuidNumber);
                                }
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //联系方式
                                if(seachFields.containsKey("qiantaiPhone")){
                                    List<String> qiantaiPhone = seachFields.get("qiantaiPhone");
                                    qiantaiPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> qiantaiPhone = new ArrayList<>();
                                    qiantaiPhone.add(data.get(0));//要改的
                                    seachFields.put("qiantaiPhone",qiantaiPhone);
                                }
                        }

                        //查询是否重复
                         //工号
                        List<QiantaiEntity> qiantaiEntities_qiantaiUuidNumber = qiantaiService.selectList(new EntityWrapper<QiantaiEntity>().in("qiantai_uuid_number", seachFields.get("qiantaiUuidNumber")).eq("qiantai_delete", 1));
                        if(qiantaiEntities_qiantaiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(QiantaiEntity s:qiantaiEntities_qiantaiUuidNumber){
                                repeatFields.add(s.getQiantaiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [工号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //账户
                        List<QiantaiEntity> qiantaiEntities_username = qiantaiService.selectList(new EntityWrapper<QiantaiEntity>().in("username", seachFields.get("username")).eq("qiantai_delete", 1));
                        if(qiantaiEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(QiantaiEntity s:qiantaiEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //联系方式
                        List<QiantaiEntity> qiantaiEntities_qiantaiPhone = qiantaiService.selectList(new EntityWrapper<QiantaiEntity>().in("qiantai_phone", seachFields.get("qiantaiPhone")).eq("qiantai_delete", 1));
                        if(qiantaiEntities_qiantaiPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(QiantaiEntity s:qiantaiEntities_qiantaiPhone){
                                repeatFields.add(s.getQiantaiPhone());
                            }
                            return R.error(511,"数据库的该表中的 [联系方式] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        qiantaiService.insertBatch(qiantaiList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }


    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        QiantaiEntity qiantai = qiantaiService.selectOne(new EntityWrapper<QiantaiEntity>().eq("username", username));
        if(qiantai==null || !qiantai.getPassword().equals(password))
            return R.error("账号或密码不正确");
        else if(qiantai.getQiantaiDelete() != 1)
            return R.error("账户已被删除");
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(.getRoleTypes());
        String token = tokenService.generateToken(qiantai.getId(),username, "qiantai", "前台");
        R r = R.ok();
        r.put("token", token);
        r.put("role","前台");
        r.put("username",qiantai.getQiantaiName());
        r.put("tableName","qiantai");
        r.put("userId",qiantai.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody QiantaiEntity qiantai){
//    	ValidatorUtils.validateEntity(user);
        Wrapper<QiantaiEntity> queryWrapper = new EntityWrapper<QiantaiEntity>()
            .eq("username", qiantai.getUsername())
            .or()
            .eq("qiantai_phone", qiantai.getQiantaiPhone())
            .andNew()
            .eq("qiantai_delete", 1)
            ;
        QiantaiEntity qiantaiEntity = qiantaiService.selectOne(queryWrapper);
        if(qiantaiEntity != null)
            return R.error("账户或者联系方式已经被使用");
        qiantai.setQiantaiDelete(1);
        qiantai.setCreateTime(new Date());
        qiantaiService.insert(qiantai);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        QiantaiEntity qiantai = new QiantaiEntity();
        qiantai.setPassword("123456");
        qiantai.setId(id);
        qiantaiService.updateById(qiantai);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        QiantaiEntity qiantai = qiantaiService.selectOne(new EntityWrapper<QiantaiEntity>().eq("username", username));
        if(qiantai!=null){
            qiantai.setPassword("123456");
            boolean b = qiantaiService.updateById(qiantai);
            if(!b){
               return R.error();
            }
        }else{
           return R.error("账号不存在");
        }
        return R.ok();
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrQiantai(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        QiantaiEntity qiantai = qiantaiService.selectById(id);
        if(qiantai !=null){
            //entity转view
            QiantaiView view = new QiantaiView();
            BeanUtils.copyProperties( qiantai , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }





}
