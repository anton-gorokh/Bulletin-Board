package config;

import org.bulletin_board.config.EmailConfig;
import org.bulletin_board.config.ValidatorConfig;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {
        "org.bulletin_board.dao",
        "org.bulletin_board.service",
        "org.bulletin_board.controller"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
@Import({EmailConfig.class, ValidatorConfig.class})
@PropertySource("classpath:db_test.properties")
public class AppConfigTest implements WebMvcConfigurer, EnvironmentAware {

    private Environment environment;

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {
        JpaTransactionManager jptm = new JpaTransactionManager();

        jptm.setDataSource(dataSource());
        jptm.setEntityManagerFactory(factory);

        return jptm;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       JpaVendorAdapter adapter) {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(dataSource);
        lcemfb.setJpaVendorAdapter(adapter);
        lcemfb.setPackagesToScan("org.bulletin_board.domain");
        return lcemfb;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dmds = new DriverManagerDataSource();

        String username = environment.getProperty("jdbc.username");
        String password = environment.getProperty("jdbc.password");
        String url = environment.getProperty("jdbc.url");
        String driver = environment.getProperty("jdbc.driver");
        String scheme = environment.getProperty("jdbc.scheme");

        dmds.setDriverClassName(driver);
        dmds.setUsername(username);
        dmds.setPassword(password);
        dmds.setUrl(url);
        dmds.setSchema(scheme);

        return dmds;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hjva = new HibernateJpaVendorAdapter();

        String database = environment.getProperty("jdbc.database");
        String dialect = environment.getProperty("jdbc.dialect");

        hjva.setDatabase(Database.valueOf(database));
        hjva.setShowSql(true);
        hjva.setDatabasePlatform(dialect);
        hjva.setGenerateDdl(true);
        return hjva;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
