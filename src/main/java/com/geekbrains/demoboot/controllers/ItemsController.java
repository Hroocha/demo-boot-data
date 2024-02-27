package com.geekbrains.demoboot.controllers;

import com.geekbrains.demoboot.entities.Item;
import com.geekbrains.demoboot.repositories.specifications.ItemSpecs;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.ui.Model;
import com.geekbrains.demoboot.services.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.logging.Filter;

// вообще здесь в перемешку бэк и фронт, так делать не надо
@Controller
@RequestMapping("/items")
public class ItemsController {
    private final ItemsService itemsService;

    @Autowired
    public ItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

//    показывает все продукты
//    @GetMapping
//    public String showItems(Model model){
//        Item item = new Item();
//        model.addAttribute("items",itemsService.getAllItems());
//        model.addAttribute("item", item);
//        return "items";
//    }

    @GetMapping
    public String showItemsFiltered(Model model){
        Item item = new Item();
        model.addAttribute("items",itemsService.getAllFiltered());
        model.addAttribute("item", item);
        itemsService.dropFilter();
        return "items";
    }
//    показывает товары на определенной странице
//    @GetMapping
//    public String showAllItemsPage(Model model){
//        model.addAttribute("items", itemsService.getAllItemsFromPage(0).getContent());
//        return "items";
//    }

    // сделать фильтрацию с браузера, id списком, по тексту, и по цене

    @GetMapping
    public String showWithFilters(@RequestParam(value = "name")String name, @RequestParam (value = "price") Integer price, @RequestParam (value = "id") List<Integer> ids){
        itemsService.getWithFilters(name, price, ids);
        return "redirect:/items";
    }

// сразу фильтрует по тексту и по цене
//    @GetMapping
//    public String showItemsPage(Model model){
//        Specification<Item> filter = Specification.where((null));
//        filter = filter.and(ItemSpecs.priceGreaterThanOrEq(20)).and(ItemSpecs.titleContains("ea"));
//        List<Item> result = itemsService.getItemsWithPagingAndFiltering(filter, PageRequest.of(0,10)).getContent();
//        model.addAttribute("items", result);
//        return "items";
//    }
}
