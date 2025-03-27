package com.example.kakashi.controller;

import com.example.kakashi.dto.PaginatedResponse;
import com.example.kakashi.model.KakashiEntity;
import com.example.kakashi.service.KakashiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class KakashiController {

    @Autowired
    private KakashiService kakashiService;

    @GetMapping
    public ResponseEntity<PaginatedResponse> getAllItems(@RequestParam(value = "pageNumber",defaultValue = "0", required = false) int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return new ResponseEntity<>(kakashiService.getAllKakashi(pageNumber,pageSize), HttpStatus.OK);

    }

    @GetMapping("/{custId}")
    public ResponseEntity<KakashiEntity> getItemById(@PathVariable Long custId) {
        KakashiEntity item = kakashiService.getItemById(custId);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<KakashiEntity> addItem(@RequestBody KakashiEntity kakashiEntity) {
        KakashiEntity savedItem = kakashiService.addItem(kakashiEntity);
        return ResponseEntity.ok(savedItem);
    }

    @PutMapping("/{custId}")
    public ResponseEntity<KakashiEntity> updateItem(@PathVariable Long custId, @RequestBody KakashiEntity kakashiEntity) {
        KakashiEntity updatedItem = kakashiService.updateItem(custId, kakashiEntity);
        return updatedItem != null ? ResponseEntity.ok(updatedItem) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{custId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long custId) {
        boolean isDeleted = kakashiService.deleteItem(custId);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
