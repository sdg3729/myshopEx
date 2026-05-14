package com.example.myShop.repository;

import com.example.myShop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("select i from Item i where i.itemDetail like" +
        " %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail (@Param("itemDetail") String itemDetail);

    @Query(value = "select*from t_item i where i.item_detail like" +
        " %:itemDetail% order by i.price desc", nativeQuery = true) // 기존 디비 쿼리 사용시 nativeQuery 지정
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);

    List<Item> findByItemName(String 테스트_상품1);
}
