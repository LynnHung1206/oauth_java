package com.lynn.oauth_demo.config;

import org.apache.ibatis.type.InstantTypeHandler;
import org.apache.ibatis.type.JapaneseDateTypeHandler;
import org.apache.ibatis.type.LocalDateTimeTypeHandler;
import org.apache.ibatis.type.LocalDateTypeHandler;
import org.apache.ibatis.type.LocalTimeTypeHandler;
import org.apache.ibatis.type.MonthTypeHandler;
import org.apache.ibatis.type.OffsetDateTimeTypeHandler;
import org.apache.ibatis.type.OffsetTimeTypeHandler;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.YearMonthTypeHandler;
import org.apache.ibatis.type.YearTypeHandler;
import org.apache.ibatis.type.ZonedDateTimeTypeHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lynn on 2025/5/1
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.lynn.oauth_demo.dao")
public class MyBatisConfiguration {
  private TypeHandler[] getTypeHandlers() {
    List<TypeHandler> list = new ArrayList<>();
    list.add(new InstantTypeHandler());
    list.add(new JapaneseDateTypeHandler());
    list.add(new LocalDateTimeTypeHandler());
    list.add(new LocalDateTypeHandler());
    list.add(new LocalTimeTypeHandler());
    list.add(new MonthTypeHandler());
    list.add(new OffsetDateTimeTypeHandler());
    list.add(new OffsetTimeTypeHandler());
    list.add(new YearMonthTypeHandler());
    list.add(new YearTypeHandler());
    list.add(new ZonedDateTimeTypeHandler());
    return list.toArray(new TypeHandler[0]);
  }

  @Bean
  @Primary
  public DataSourceTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}
