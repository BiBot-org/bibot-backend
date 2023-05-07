package com.coderecipe.global.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<LocalDateTime, String> localDateTimeStringConverter = new AbstractConverter<>() {
            @Override
            protected String convert(LocalDateTime source) {
                return source.toString();
            }
        };

        Converter<LocalDate, String> localDateStringConverter = new AbstractConverter<>() {
            @Override
            protected String convert(LocalDate source) {
                return source.toString();
            }
        };

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);

        modelMapper.addConverter(localDateTimeStringConverter);
        modelMapper.addConverter(localDateStringConverter);

        return modelMapper;
    }
}