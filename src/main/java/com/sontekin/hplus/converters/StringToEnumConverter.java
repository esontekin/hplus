package com.sontekin.hplus.converters;

import com.sontekin.hplus.beans.Gender;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, Gender> {
    @Override
    public Gender convert(String source) {
        switch (source){
            case "Male": {
                return Gender.MALE;
            }
            case "Female": {
                return Gender.FEMALE;
            }
            default: {
                return Gender.OTHER;
            }
        }
    }
}
