package org.hamilton.mergesort.service;

import org.hamilton.mergesort.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MergeSortService {

    public List<Item> sort(List<Item> items, String order) {
        if (items.size() <= 1) return items;

        int mid = items.size() / 2;

        List<Item> left = sort(items.subList(0, mid), order);
        List<Item> right = sort(items.subList(mid, items.size()), order);

        return merge(left, right, order);
    }

    private List<Item> merge(List<Item> left, List<Item> right, String order) {
        List<Item> result = new ArrayList<>();

        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {

            boolean condition;

            if ("desc".equalsIgnoreCase(order)) {
                condition = left.get(i).getValue() > right.get(j).getValue();
            } else {
                condition = left.get(i).getValue() < right.get(j).getValue();
            }

            if (condition) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        result.addAll(left.subList(i, left.size()));
        result.addAll(right.subList(j, right.size()));

        return result;
    }
}