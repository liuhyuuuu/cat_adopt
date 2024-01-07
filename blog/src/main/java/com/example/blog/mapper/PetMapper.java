package com.example.blog.mapper;

import com.example.blog.entity.Pet;

import java.util.List;


public interface PetMapper {

    Pet get(String name);

    List<Pet> getUid(int uid);

    Pet getPid(int pid);

    List<Pet> getList(Pet pet);

    List<Pet> getKind(Pet pet);

    List<Pet> getColor(Pet pet);

    void add(Pet pet);

    void update(Pet pet);

    void del(Pet pet);

}
