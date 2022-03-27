package com.jlabs.graph.persistence;

import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.H2Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class TestConfig {

    public DataSource h2DataSource() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:˜file:/tmp/test");
        ds.setUser("sa");
        ds.setPassword("sa");
        return ds;
   }

    @Bean
    public TransactionAwareDataSourceProxy dataSource() {
        return new TransactionAwareDataSourceProxy(h2DataSource());
    }

    @Bean
    public JdbcTemplate jdbcTemplate(TransactionAwareDataSourceProxy orDataSource) {
        return new JdbcTemplate(orDataSource);
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        transactionManager.setDefaultTimeout(15); // default to 24h
        return transactionManager;
    }
    @Bean
    @Autowired
    public LocalSessionFactoryBean sessionFactory(TransactionAwareDataSourceProxy dataSource) throws NamingException {

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setPackagesToScan(
                PersistentVertex.class.getPackage().getName()
        );

        return sessionFactory;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.bytecode.use_reflection_optimizer", "true");
        properties.put("hibernate.dialect", H2Dialect.class);
        properties.put("hibernate.jdbc.fetch_size", "200");
        properties.put("hibernate.id.new_generator_mappings", "false");
        properties.put("hibernate.jdbc.use_get_generated_keys", "true");
        properties.put("hibernate.max_fetch_depth", "100");
        properties.put("hibernate.show_sql", false);
        properties.put("hibernate.jdbc.use_scrollable_resultset", "true");
        properties.put("hibernate.hbm2ddl.auto", "create");
//        properties.put("hibernate.hql.bulk_id_strategy", "org.hibernate.hql.spi.id.inline.InlineIdsSubSelectValueListBulkIdStrategy");
//        properties.put("hibernate.native_exception_handling_51_compliance", "true");
//        properties.put("hibernate.legacy_limit_handler", "true");

		/*
		 If it occurs problem with duplicate CacheManager in Spring Context, you must use the properties below:

 		properties.put("hibernate.cache.region.factory_class", "net.sf.ehcache.hibernate.SingletonEhCacheRegionFactory");

		 */
        return properties;
    }

}
