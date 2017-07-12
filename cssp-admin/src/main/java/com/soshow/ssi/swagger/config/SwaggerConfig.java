package com.soshow.ssi.swagger.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@Configuration
@EnableSwagger
@EnableWebMvc
@ComponentScan(basePackages ={"com.soshow.ssi"})
public class SwaggerConfig {

    private SpringSwaggerConfig springSwaggerConfig; 
    
    @Resource
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    /**
     * Required to autowire SpringSwaggerConfig
     */
    @Resource
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig)
    {
    	springSwaggerConfig.jacksonSwaggerSupport();
        this.springSwaggerConfig = springSwaggerConfig;
    }

    /**
     * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc
     * framework - allowing for multiple swagger groups i.e. same code base
     * multiple swagger resource listings.
     */
    @Bean
    public SwaggerSpringMvcPlugin customImplementation()
    {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo())
                .includePatterns(".*");
    }

    /*
	 * "标题 title",
	 * "描述 description", 
	 * "termsOfServiceUrl", 
	 * "联系邮箱 contact email",
	 * "许可证的类型 license type", 
	 * "许可证的链接 license url"
	 */
    private ApiInfo apiInfo()
    {
    	
    	requestMappingHandlerAdapter.getMessageConverters();
        ApiInfo apiInfo = new ApiInfo(
                "后台管理系统",
                "后台管理系统-所有接口。",
                "开发者: ",
                "soshow@cssp.cn",
                "CSSP  License",
                "/CSSPLICENSE");
        return apiInfo;
    }
}
