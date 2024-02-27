package com.geekbrains.demoboot.services;

import com.fasterxml.jackson.module.jsonSchema.types.ArraySchema;
import com.geekbrains.demoboot.entities.Item;
import com.geekbrains.demoboot.repositories.ItemRepository;
import com.geekbrains.demoboot.repositories.specifications.ItemSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemsService {
    private final ItemRepository itemRepository;
    List<Item> itemsFiltered = null;

    @Autowired
    public ItemsService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public Page<Item> getAllItemsFromPage(int pageNumber){
        return itemRepository.findAll(PageRequest.of(pageNumber, 2));
    }
    public Page<Item> getItemsWithPagingAndFiltering (Specification<Item> specifications, Pageable pageable){
        return itemRepository.findAll(specifications, pageable);
    }





    public List<Item> getAllFiltered (){
        if (itemsFiltered == null){
            itemsFiltered = itemRepository.findAll(); // в момент создания там лежат все элементы
        }
        return itemsFiltered;
    }
    public void getWithFilters (String name, Integer price, List<Integer> ids) {
        Specification<Item> filter = Specification.where((null));
        filter = filter.and(ItemSpecs.titleContains(name)).and(ItemSpecs.priceGreaterThanOrEq(price).and(ItemSpecs.idFilter(ids)));
        itemsFiltered = itemRepository.findAll(filter);
    }
    public void dropFilter(){
        itemsFiltered = getAllItems();
    }



}
