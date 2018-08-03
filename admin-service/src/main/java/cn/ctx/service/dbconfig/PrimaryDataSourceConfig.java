/**    
* @Title: PrimaryDataSourceConfig.java  
* @Package cn.ctx.admin.conf.db  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年12月25日 下午4:42:52  
* @version V1.0    
*/
package cn.ctx.service.dbconfig;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**  
* @ClassName: PrimaryDataSourceConfig  
* @Description: TODO(主数据源)  
* @author gyu
* @date 2017年12月25日 下午4:42:52  
*    
*/
@Configuration
@MapperScan(basePackages ="cn.ctx.service.mapper.primary",sqlSessionTemplateRef = "primarySqlSessionTemplate")
public class PrimaryDataSourceConfig{
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    @Primary
    public DataSource primaryDataSource() {  
        return new DruidDataSource();
    } 

    @Bean(name = "primaryTransactionManager")
	@Primary
    public PlatformTransactionManager primaryTransactionManager(@Qualifier("primaryDataSource") DataSource dataSource) {
    	 return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "primarySqlSessionFactory")
	@Primary
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("cn.ctx.service.model");

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "primarySqlSessionTemplate")
	@Primary
    public SqlSessionTemplate primarySqlSessionTemplate(@Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    @Bean
	@Primary
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("primarySqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("cn.ctx.service.mapper.primary");
        Properties properties = new Properties();
        properties.setProperty("mappers", "cn.ctx.common.framework.util.MyMapper");
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}
