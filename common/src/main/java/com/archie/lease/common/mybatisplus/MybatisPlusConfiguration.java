package com.archie.lease.common.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.archie.lease.web.*.mapper")
public class MybatisPlusConfiguration {

}