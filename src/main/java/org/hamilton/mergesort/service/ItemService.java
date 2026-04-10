package org.hamilton.mergesort.service;

import org.hamilton.mergesort.model.Item;
import org.hamilton.mergesort.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private MergeSortService mergeSortService;

    public List<Item> getSortedItems(String order) {
        List<Item> items = repository.findAll();
        return mergeSortService.sort(items, order);
    }

    public Item save(Item item) {
        return repository.save(item);
    }
}