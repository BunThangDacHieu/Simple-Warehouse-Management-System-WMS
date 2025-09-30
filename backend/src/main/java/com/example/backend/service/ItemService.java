package com.example.backend.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.backend.bussinessObject.model.Item;
import com.example.backend.repository.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /*-------------------------------------CRUD cơ bản------------------------------------- */
    //Lấy danh sách của toàn bộ hàng hóa
    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    //Tìm dựa trên id
    public Optional<Item> findItembyId(long id) {
        return itemRepository.findById(id);
    }

    //Tạo
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    //Nâng cấp
    public Item updateItem(Item updatedItem) {
        Item item = itemRepository.findById(updatedItem.getId())
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
        item.setName(updatedItem.getName());
        item.setQuantity(updatedItem.getQuantity());
        item.setDescription(updatedItem.getDescription());
        return itemRepository.save(item);
    }

    //Xóa
    public void deleteItem(long id) {
        itemRepository.deleteById(id);
    }
    /*-------------------------------------Logic nghiệp vụ------------------------------------- */
    //Danh sách hàng hóa theo kho
    public List<Item> findItemListbyWarehouseId(Long warehouseId){
        return itemRepository.findItemListbyWarehouseId(warehouseId);
    }

}
