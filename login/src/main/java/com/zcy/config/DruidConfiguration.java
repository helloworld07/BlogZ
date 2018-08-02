package com.zcy.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zcy on 2018/8/2.
 */
@Configuration
public class DruidConfiguration {
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.initialSize}")
    private Integer initialSize;

    @Value("${spring.datasource.minIdle}")
    private Integer minIdle;

    @Value("${spring.datasource.maxActive}")
    private Integer maxActive;

    @Value("${spring.datasource.maxWait}")
    private Integer maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private Integer timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.removeAbandoned}")
    private Boolean removeAbandoned;

    @Value("${spring.datasource.removeAbandonedTimeout}")
    private Integer removeAbandonedTimeout;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    private Boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private Boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private Boolean testOnReturn;

    @Value("${spring.datasource.poolPreparedStatements}")
    private Boolean poolPreparedStatements;

    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private Integer maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.logAbandoned}")
    private Boolean logAbandoned;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("${spring.datasource.connectionProperties}")
    private String connectionProperties;

    @Value("${spring.datasource.useGlobalDataSourceStat}")
    private Boolean useGlobalDataSourceStat;


    @Bean
    public ServletRegistrationBean druidServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");//http://localhost:8090/datebook/druid/login.html
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("resetEnable", "false"); //禁用HTML页面上的“Rest All”功能
        initParameters.put("allow", "");  //ip白名单（没有配置或者为空，则允许所有访问）
        initParameters.put("deny", "10.8.9.116"); //ip黑名单
        initParameters.put("loginUsername", "admin");  //++监控页面登录用户名
        initParameters.put("loginPassword", "admin");  //++监控页面登录用户密码
        //如果某个ip同时存在，deny优先于allow
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    @Bean     //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(this.dbUrl);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driverClassName);
        //configuration
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setRemoveAbandoned(removeAbandoned);
        druidDataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        druidDataSource.setLogAbandoned(logAbandoned);//Statements和连接的日志添加在每个连接打开或者生成新的Statement,因为需要生成stack trace
        druidDataSource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
        try {
            druidDataSource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        druidDataSource.setConnectionProperties(connectionProperties);
        return druidDataSource;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
