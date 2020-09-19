package com.bestcxx.stu.springmybatis.controller;


import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.bestcxx.stu.springmybatis.model.IndexStu;
import com.bestcxx.stu.springmybatis.service.IndexStuService;
import com.bestcxx.stu.springmybatis.threadpool.ThreadPoolFactory;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * WebMvcConfigurer.addViewControllers 用于 / 直接映射到某页面或者新路径
 * 本例子为跳转 新路径
 */
@Controller
public class IndexController implements WebMvcConfigurer {

    @Autowired
    private IndexStuService indexStuService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //重定向要 /users
        registry.addRedirectViewController("/", "/index");

        //如果直接跳转到 某 页面
        //registry.addRedirectViewController("/","/页面名称");
    }

    @GetMapping("/index")
    @SentinelResource("HelloWorld")
    public ModelAndView index() {
        String resourceName = "index";
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        initFlowRules(resourceName);
        Entry entry = null;
        try {
            entry = SphU.entry(resourceName);
            /*您的业务逻辑 - 开始*/
            System.out.println("insert 开始");
            final IndexStu indexStu = new IndexStu();
            indexStu.setUpdateTime(new Date());
            indexStu.setType("A");
            indexStu.setStatus(0);

            final List list = Lists.newArrayList();
            for (int i = 0; i < 50; i++) {
                indexStu.setCreateTime(new Date());
                list.add(indexStu);
            }

            for (int i = 0; i < 1000000 / 50; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ThreadPoolFactory.newTask(ThreadPoolFactory.getThreadFactory().newThread(new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        indexStuService.insertList(list);

                    }
                }));

            }
            /*您的业务逻辑 - 结束*/
        } catch (BlockException e1) {
            /*流控逻辑处理 - 开始*/
            System.out.println("block!");
            /*流控逻辑处理 - 结束*/
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }

        return modelAndView;
    }

    @GetMapping("/indexNoDb")
    @SentinelResource("HelloWorldNoDb")
    public ModelAndView indexNoDb() {
        String resourceName = "indexNoDb";
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        initFlowRules(resourceName);
        Entry entry = null;
        try {
            entry = SphU.entry(resourceName);
            /*您的业务逻辑 - 开始*/

            /*您的业务逻辑 - 结束*/
        } catch (BlockException e1) {
            /*流控逻辑处理 - 开始*/
            System.out.println("block!");
            /*流控逻辑处理 - 结束*/
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }

        return modelAndView;
    }

    private void initFlowRules(String resourceName){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource(resourceName);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 1.
        rule.setCount(1);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
