package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/koalas")
public class KoalaController {
    private Map<Integer, Koala> koalas;


    @PostConstruct
    public void init(){
        koalas = new HashMap<>();
    }

@GetMapping
    public List<Koala> getAllKoalas(){
       return koalas.values().stream().toList();
}
@GetMapping("/{id}")
    public Koala findById(@PathVariable Integer id){
        if(!koalas.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"bu id'ye sahip bir koala bulunamadı");
        }
        return koalas.get(id);
}
@PostMapping
    public ResponseEntity<Koala> addKoalaToList(@RequestBody Koala koala){
koalas.put(koala.getId(),koala);
return ResponseEntity.ok(koala);
}


@PutMapping("/{id}")
    public Koala updateKoala(@PathVariable Integer id ,@RequestBody Koala koala){
        if(!koalas.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"bu id'ye sahip bir koala yok.");
        }
         koalas.put(id, koala);
        return koala  ;
}
@DeleteMapping("/{id}")
    public Koala deletedKoala(@PathVariable Integer id){
        if(!koalas.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND ,"bu id ye sahipr bir koala yok");
        }
        Koala deleted=koalas.remove(id);
        return deleted;
}
}
