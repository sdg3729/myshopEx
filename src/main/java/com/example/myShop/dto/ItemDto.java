package com.example.myShop.dto;

import java.time.LocalDateTime;

public class ItemDto {

    private String itemName;          // 상품명
    private String itemDetail;        // 상품 상세 설명
    private Integer price;            // 가격
    private LocalDateTime regTime;    // 등록 시간

    public ItemDto() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(String itemDetail) {
        this.itemDetail = itemDetail;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.regTime = regTime;
    }
}