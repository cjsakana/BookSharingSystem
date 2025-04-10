package kg.us.sakanatang.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
@Configuration
@PropertySource("classpath:jdbc.properties")
public class JdbcConfig {
    // 注解绑定 jdbc.properties 文件的内容
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    // 利用阿里的德鲁伊包初始化连接
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        // 连接池
        dataSource.setInitialSize(5); // 初始化连接数
        dataSource.setMaxActive(20);  // 最大连接数
        dataSource.setMinIdle(5);     // 最小空闲连接数
        // 监测功能
        dataSource.setTimeBetweenEvictionRunsMillis(60000); // 检测间隔
        dataSource.setValidationQuery("SELECT 1"); // 检测连接是否有效
        return dataSource;
    }

    // 配置 Spring 的事务管理器
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager ds = new DataSourceTransactionManager();
        ds.setDataSource(dataSource);
        return ds;
    }
}
