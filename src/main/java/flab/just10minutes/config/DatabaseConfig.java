package flab.just10minutes.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
/**
 * @Bean : (스프링 IOC 컨테이너에서 관리하는 자바 객체 : 수동등록, 메소드 레벨)을 스프링 IOC 컨테이너에 등록하기 위해 @Configuration 사용 필요.
 * @Configuration : @Component(스프링 IOC 컨테이너에서 관리하는 자바 객체 등록 : 자동등록, 클래스 레벨)가 정의되어 있어, 자동 으로 스프링 빈으로 등록.

 * @Configuration을 사용해 @Bean을 등록하면 CGLIB(구체 클래스를 상속받아 클래스 생성)로 스프링 빈에 등록되어 싱글톤 보장됨.
 * **/
@MapperScan(basePackages = "flab.just10minutes.member.repository")
/**basePackages에 지정된 경로 이하의 모든 인터페이스로 기반 @Mapper를 사용할 수 있음**/
@Slf4j
public class DatabaseConfig {

    @Bean
    @ConfigurationProperties(prefix="spring.datasource.hikari")
    /**.properties 나 .yml 파일에 있는 설정 값을 자바 클래스에 바인딩 해 사용할 수 있게 해줌**/
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        Resource[] arrResource = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/**/*.xml");;
        sqlSessionFactoryBean.setMapperLocations(arrResource);
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return (SqlSessionFactory) sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
