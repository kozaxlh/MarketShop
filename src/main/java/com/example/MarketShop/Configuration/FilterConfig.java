package com.example.MarketShop.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    public FilterConfig (ObjectMapper objectMapper) {
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("UsersInfo",SimpleBeanPropertyFilter.serializeAllExcept("userID","password"));

        objectMapper.setFilterProvider(filterProvider);
    }
}
