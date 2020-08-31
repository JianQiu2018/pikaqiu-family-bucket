package com.pikaqiu.familybucket.elasticsearch.entities;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Description:加上了@Document注解之后，默认情况下这个实体中所有的属性都会被建立索引、并且分词
 *             在Elasticsearch 6.X 版本中，不建议使用type，而且在7.X版本中将会彻底废弃type，
 *             7.X不加@Field不会报错；type默认_doc。 PS:如果版本不一致会导致各种问题
 *
 * @author PikaQiu
 * @date 2019/8/26 10:32
 */
@Setter
@Getter
@Document(indexName = "familybucket")
public class ArticleInfoEs implements Serializable {

    @Id
    private String id;
//    @Field(type = FieldType.Text)
    @Field(type = FieldType.Text,fielddata = true, searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String title;
    @Field(type = FieldType.Text, index = false)
    private String content;
//    @Field(type = FieldType.Integer)
    private Integer pageView;
    @Field(type = FieldType.Long)
    private Long idLong;
    private Long idLong2;
    /*
    * ik_smart 是 IK Analysis 其中一种分词形式。IK Analysis主要有两种类型的分词形式，分别是 ik_max_word 和 ik_smart。
      ik_max_word: 会将文本做最细粒度的拆分，比如会将“中华人民共和国国歌”拆分为“中华人民共和国”、“中华人民”、“中华”、“华人”、“人民共和国”、“人民”、“人”、“民”,、“共和国”、“共和”、“和”、“国歌”等，会穷尽各种可能的组合；
      ik_smart: 会做最粗粒度的拆分，比如会将“中华人民共和国国歌”拆分为“中华人民共和国”、“国歌”。
    * */
    @Field(type = FieldType.Text,fielddata = true, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String tags;  // 标签

//    @Field(type = FieldType.Date ,format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    private Date createTime;

}

