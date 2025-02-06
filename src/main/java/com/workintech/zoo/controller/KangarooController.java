package com.workintech.zoo.controller;


import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.validation.zooKangarooValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init(){
        kangaroos = new HashMap<>();

    }
@ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Kangaroo> allKangaaros(){
        return kangaroos.values().stream().toList();
}

@GetMapping("/{id}")
public Kangaroo findById(@PathVariable Integer id){

    zooKangarooValidation.isValid(id);
    zooKangarooValidation.checkKangarooExistence(kangaroos,id,true);
   return kangaroos.get(id) ;
       /* if(kangaroos.containsKey(id)){
            return kangaroos.get(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND," böyle bir id'ye sahip kangaroo bulunamadı");*/
}

    @PostMapping
    public Kangaroo saveKangaroo(@RequestBody Kangaroo kangaroo) {
zooKangarooValidation.checkKangarooExistence(kangaroos,kangaroo.getId(),false);
zooKangarooValidation.checkKangarooWeight(kangaroo.getWeight());
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroos.get(kangaroo.getId());
       /* this.kangaroos.put(kangaroo.getId(), kangaroo);
        return ResponseEntity.ok(kangaroo);*/
    }


@PutMapping("/{id}")
    public Kangaroo updateKangaroo(@PathVariable Integer id,@RequestBody Kangaroo kangaroo){
        zooKangarooValidation.isValid(id);
        zooKangarooValidation.checkKangarooWeight(kangaroo.getWeight());
        kangaroo.setId(id);
        if(kangaroos.containsKey(id)){
            kangaroos.put(id,kangaroo);
            return kangaroos.get(id);
        }
        else {
            return saveKangaroo(kangaroo);
        }
      /*  if(!kangaroos.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Bu id'ye sahip bir kangaroo bulunamadı");
        }
    kangaroos.put(id,kangaroo);

    return kangaroos.get(id);*/
}

    @DeleteMapping("/{id}")
    public Kangaroo deleteKangaroo(@PathVariable Integer id) {
        zooKangarooValidation.isValid(id);
        zooKangarooValidation.checkKangarooExistence(kangaroos,id,true);
        return kangaroos.remove(id);
    /*    if (!kangaroos.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Silinecek kangaroo bulunamadı");
        }
        kangaroos.remove(id);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Kangaroo başarıyla silindi");
        response.put("id", id);

        return ResponseEntity.ok(response);*/
    }
}
