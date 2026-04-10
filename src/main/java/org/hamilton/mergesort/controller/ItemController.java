package org.hamilton.mergesort.controller;

import lombok.RequiredArgsConstructor;
import org.hamilton.mergesort.model.Item;
import org.hamilton.mergesort.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService service;

    @GetMapping("/sorted")
    public List<Item> getSorted(@RequestParam(defaultValue = "asc") String order) {
        return service.getSortedItems(order);
    }

    @PostMapping
    public Item create(@RequestBody Item item) {
        item.setCreatedAt(LocalDateTime.now());
        return service.save(item);
    }
}