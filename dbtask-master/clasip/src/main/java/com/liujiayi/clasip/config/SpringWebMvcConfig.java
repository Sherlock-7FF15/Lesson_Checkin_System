//package com.liujiayi.clasip.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// * 创建时间: 2020/4/30 14:54
// * 文件备注: 解决前后端整合后无法正常访问静态资源的问题
// *          前端开发好后将build构建好的dist下static中的文件拷贝到springboot的resource的static下，index.html则直接拷贝到springboot的resource的static下
// * 编写人员: 杨伯益
// */
//
//@Configuration
//public class SpringWebMvcConfig extends WebMvcConfigurerAdapter {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry){
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        super.addResourceHandlers(registry);
//    }
//
//}
