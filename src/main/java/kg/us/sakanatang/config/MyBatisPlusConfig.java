package kg.us.sakanatang.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.sql.DataSource;

@MapperScan("kg.us.sakanatang.mapper")
@Configuration
public class MyBatisPlusConfig {

    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("kg.us.sakanatang.domain.entity");

        // 使用 MyBatis-Plus 的 Configuration
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(false); // 关闭驼峰转下划线

        // 启用 SQL 日志输出到控制台
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);

        // TODO 开启二级缓存。还要配置其他.反正又没作要求，不搞
        // configuration.setCacheEnabled(true);

        factoryBean.setConfiguration(configuration);

        return factoryBean;
    }
}
