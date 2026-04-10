package org.hamilton.mergesort.repository;

import org.hamilton.mergesort.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
