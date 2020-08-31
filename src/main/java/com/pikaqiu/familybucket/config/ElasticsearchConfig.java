package com.pikaqiu.familybucket.config;


import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

    //@Value("${spring.elasticsearch.rest.uris}")
    private String hosts;

    /**
     * 默认的converter不支持long到localdatime的转换，从elasticsearch读取的时候会报错，所以在这里添加一个。
     */
    @Bean
    @Override
    public ElasticsearchCustomConversions elasticsearchCustomConversions() {
        List<Converter> converters= new ArrayList<>();
        converters.add(LongToLocalDateTimeConverter.INSTANCE);
        return new ElasticsearchCustomConversions(converters);
    }



    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration configuration = ClientConfiguration.builder()
                .connectedTo("30.40.37.216:9200")
                .build();
        return RestClients.create(configuration).rest();
    }

    @ReadingConverter
    static enum LongToLocalDateTimeConverter implements Converter<Long, LocalDateTime> {
        INSTANCE;

        private LongToLocalDateTimeConverter() {
        }

        public LocalDateTime convert(Long source) {
            return Instant.ofEpochMilli(source).atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
    }

}
