package org.example.entity;

import lombok.Data;
import org.example.loader.YamlPropertyLoader;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@ConfigurationProperties(prefix = "family")
@Validated

//@PropertySource(value = {"classpath:family.properties"})

@PropertySource(value = {"classpath:family.yml"},factory = YamlPropertyLoader.class)

public class Family {

    @Length(min = 5 , message = "家庭名称必须在5到10位之间")

    private String familyName;


    private String father;


    private String mother;


    private String child;


    @Range(min = 3 , message = "家庭年龄必须大于3年")

    private Integer age;

}
