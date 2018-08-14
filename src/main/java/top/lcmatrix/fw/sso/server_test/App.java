package top.lcmatrix.fw.sso.server_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import top.lcmatrix.fw.sso.server.Constant;
import top.lcmatrix.fw.sso.server_test.interceptor.LoginInterceptor;

@Configuration
@EnableAutoConfiguration
@ServletComponentScan({"top.lcmatrix.fw.sso.server_test", "top.lcmatrix.fw.sso.server"})
@ComponentScan({"top.lcmatrix.fw.sso.server_test", "top.lcmatrix.fw.sso.server"})
public class App extends WebMvcConfigurerAdapter {
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	
    public static void main( String[] args ){
    	SpringApplication.run(App.class, args);
    }
    
    @Override
	public void addInterceptors(InterceptorRegistry registry) {
    	//拦截请求验证登录时，必须排除Constant.SSO_SERVER_PATH下的所有请求
		registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
			.excludePathPatterns("/error", "/open/**", "/" + Constant.SSO_SERVER_PATH + "/**");
		super.addInterceptors(registry);
	}
}
