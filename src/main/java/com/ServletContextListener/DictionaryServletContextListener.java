package com.ServletContextListener;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.DictionaryEntity;
import com.service.DictionaryService;
import com.thread.MyThreadMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典初始化监视器  用的是服务器监听,每次项目启动,都会调用这个类
 */
@WebListener
public class DictionaryServletContextListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(DictionaryServletContextListener.class);
    private MyThreadMethod myThreadMethod;
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("----------服务器停止----------");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());

        logger.info("----------字典表初始化开始----------");
        DictionaryService dictionaryService = (DictionaryService)appContext.getBean("dictionaryService");
        List<DictionaryEntity> dictionaryEntities = dictionaryService.selectList(new EntityWrapper<DictionaryEntity>());
        Map<String, Map<Integer,String>> map = new HashMap<>();
        for(DictionaryEntity d :dictionaryEntities){
            Map<Integer, String> m = map.get(d.getDicCode());
            if(m ==null || m.isEmpty()){
                m = new HashMap<>();
            }
            m.put(d.getCodeIndex(),d.getIndexName());
            map.put(d.getDicCode(),m);
        }
        sce.getServletContext().setAttribute("dictionaryMap", map);
        logger.info("----------字典表初始化完成----------");



        logger.info("----------线程执行开始----------");
        if (myThreadMethod == null) {
            myThreadMethod = new MyThreadMethod();
            myThreadMethod.start(); // servlet 上下文初始化时启动线程myThreadMethod
        }
        logger.info("----------线程执行结束----------");
    }

}
