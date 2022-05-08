package com.nemo.footballassociation.DAL;

import org.hibernate.dialect.identity.IdentityColumnSupport;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.mapping.MappingException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

//public class SQLiteIdentityColumnSupport extends IdentityColumnSupportImpl {
//
//    @Override
//    public boolean supportsIdentityColumns() {
//        return true;
//    }
//
//    @Override
//    public String getIdentitySelectString(String table, String column, int type)
//            throws MappingException {
//        return "select last_insert_rowid()";
//    }
//
//    @Override
//    public String getIdentityColumnString(int type) throws MappingException {
//        return "integer";
//    }
//
//    @Autowired
//    Environment env;
//
//    @Bean
//    public DriverManagerDataSource dataSource() {
//        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("driverClassName"));
//        dataSource.setUrl(env.getProperty("url"));
//        dataSource.setUsername(env.getProperty("user"));
//        dataSource.setPassword(env.getProperty("password"));
//        return dataSource;
//    }
//}