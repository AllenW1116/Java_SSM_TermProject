package pers.allen.whu_java_terminal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pers.allen.whu_java_terminal.interceptor.CorsInterceptor;
import pers.allen.whu_java_terminal.interceptor.LoginInterceptor;

/**
 * 拦截器配置
 *
 * 不用权限可以访问的url    /api/v1/pub/  不拦截，全过
 * 要登录才可以访问的url    /api/v1/pri/  要根据规则拦截
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Bean
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    } //在ioc容器中放入一个拦截器bean

    @Bean
    CorsInterceptor corsInterceptor(){
        return new CorsInterceptor();
    }


    /**
     *配置哪些路径需要拦截
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         * 拦截全部路径，这个跨域需要放在最上面
         */
        registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");



        registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/v1/pri/*/*/**")
                //不拦截哪些路径（不然没法登陆注册，用户也妹有token啊)   斜杠一定要加!!!
                .excludePathPatterns("/api/v1/pri/user/login","/api/v1/pri/user/register");


        WebMvcConfigurer.super.addInterceptors(registry); //把registry传进去。固定写法！

    }


}
