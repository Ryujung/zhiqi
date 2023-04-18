package com.zhiqi.framework.config;

import com.ryujung.zhiqi.common.utils.StringUtils;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.*;

/**
 * @author RyuJung
 * @since 2023/4/16-18:08
 */
@Configuration
@MapperScan("com.zhiqi.**.mapper")
public class MybatisConfig {

    @Autowired
    Environment env;

    public static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        String typeAliasesPackage = env.getProperty("mybatis.typeAliasesPackage");
        String mapperLocations = env.getProperty("mybatis.mapperLocations");
        String configLocation = env.getProperty("mybatis.configLocation");

        typeAliasesPackage = setTypeAliasesPackage(typeAliasesPackage);
        VFS.addImplClass(SpringBootVFS.class);

        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
        sessionFactory.setMapperLocations(resolveMapperLocations(StringUtils.split(mapperLocations, ",")));
        sessionFactory.setConfigLocation(resolveConfigLocation(configLocation));
        return sessionFactory.getObject();
    }

    private Resource resolveConfigLocation(String configLocation) {
        return new DefaultResourceLoader().getResource(configLocation);
    }

    private String setTypeAliasesPackage(String typeAliasesPackage) {
        HashSet<String> result = new HashSet<>();
        PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        CachingMetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();
        try {
            for (String aliasesPackage : typeAliasesPackage.split(",")) {
                Set<String> resultSet = new HashSet<>();
                aliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                        ClassUtils.convertClassNameToResourcePath(aliasesPackage.trim()) + "/" + DEFAULT_RESOURCE_PATTERN;
                Resource[] resources = resourceResolver.getResources(aliasesPackage);
                if (resources != null) {
                    for (Resource resource : resources) {
                        if (resource.isReadable()) {
                            try {
                                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                                resultSet.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage().getName());
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if (!resultSet.isEmpty()) {
                    result.addAll(resultSet);
                }
            }

            if (!result.isEmpty()) {
                typeAliasesPackage = String.join(",", result.toArray(new String[result.size()]));
            } else {
                throw new RuntimeException("mybatis typeAliasesPackage 路径扫描错误,参数typeAliasesPackage:" + typeAliasesPackage + "未找到任何包");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return typeAliasesPackage;
    }

    /**
     * 解析mapper的位置参数
     */
    public Resource[] resolveMapperLocations(String[] mapperLocations) {
        PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        ArrayList<Resource> resources = new ArrayList<>();
        if (mapperLocations != null) {
            for (String mapperLocation : mapperLocations) {
                try {
                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (IOException e) {
                    // ignore
                }
            }
        }
        return resources.toArray(new Resource[resources.size()]);
    }

}
