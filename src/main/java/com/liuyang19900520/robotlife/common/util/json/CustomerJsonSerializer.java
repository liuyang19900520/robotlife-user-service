package com.liuyang19900520.robotlife.common.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.liuyang19900520.robotlife.common.util.LStringUtils;


/**
 * depend on jackson
 *
 * @author Liuyang
 */
public class CustomerJsonSerializer {

    JsonObjectMapper mapper = new JsonObjectMapper();
    JacksonJsonFilter jacksonFilter = new JacksonJsonFilter();

    /**
     * @param clazz   target type
     * @param include include fields
     * @param filter  filter fields
     */
    public void filter(Class<?> clazz, String include, String filter) {
        if (clazz == null) {
            return;
        }
        if (LStringUtils.isNotBlank(include)) {
            jacksonFilter.include(clazz, include.split(","));
        }
        if (LStringUtils.isNotBlank(filter)) {
            jacksonFilter.filter(clazz, filter.split(","));
        }
        mapper.addMixIn(clazz, jacksonFilter.getClass());
    }

    public String toJson(Object object) throws JsonProcessingException {
        mapper.setFilterProvider(jacksonFilter);
        //调整long 2 String
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        mapper.registerModule(simpleModule);
        return mapper.writeValueAsString(object);
    }

    public void filter(JSON json) {
        this.filter(json.type(), json.include(), json.filter());
    }
}
