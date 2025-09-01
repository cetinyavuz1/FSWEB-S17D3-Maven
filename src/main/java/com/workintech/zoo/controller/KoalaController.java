package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

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
    public List<Koala> find(){
        return koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala findById(@PathVariable Integer id){
        return koalas.get(id);
    }

    @PostMapping
    public Koala save(@RequestBody Koala koala){
        koalas.put(koala.getId(), koala);
        return koala;
    }

    @PutMapping("/{id}")
    public Koala update(@PathVariable Integer id, @RequestBody Koala koala){
        koala.setId(id);
        if(koalas.containsKey(id)){
            koalas.put(id, koala);
            return koalas.get(id);
        }else{
            return save(koala);
        }
    }

    @DeleteMapping("/{id}")
    public Koala delete(@PathVariable Integer id){
        return koalas.remove(id);
    }

}
