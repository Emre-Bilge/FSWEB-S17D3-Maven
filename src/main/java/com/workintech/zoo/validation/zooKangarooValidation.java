package com.workintech.zoo.validation;

import com.workintech.zoo.entity.Kangaroo;

import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class zooKangarooValidation {
    public static void isValid(Integer id) {
        if (id == null || id < 0) {
            throw new ZooException("id is not valid" + id, HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkKangarooExistence(Map<Integer, Kangaroo> kangaroos, Integer id, boolean existence) {
        if (existence) {
            if (!kangaroos.containsKey(id)) {
                throw new ZooException("id bulunamadı" + id, HttpStatus.NOT_FOUND);
            }

        } else {
            if (kangaroos.containsKey(id)) {
                throw new ZooException("id bulundu" + id, HttpStatus.BAD_REQUEST);
            }
        }
    }

    public static void checkKangarooWeight(Double weight) {
        if(weight == null || weight <= 0){
            throw new ZooException("hayır "+ weight,HttpStatus.BAD_REQUEST);
        }
    }
}
