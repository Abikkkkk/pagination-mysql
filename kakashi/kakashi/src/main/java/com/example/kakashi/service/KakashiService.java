package com.example.kakashi.service;

import com.example.kakashi.dto.KakashiDto;
import com.example.kakashi.dto.PaginatedResponse;
import com.example.kakashi.model.KakashiEntity;
import com.example.kakashi.repository.KakashiRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KakashiService {

    private final KakashiRepository kakashiRepository;

    public KakashiService(KakashiRepository kakashiRepository) {
        this.kakashiRepository = kakashiRepository;
    }

    public PaginatedResponse getAllKakashi(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber,pageSize);

        Page<KakashiEntity> kakashis = kakashiRepository.findAll(pageable);
        List<KakashiEntity> kakashiList = kakashis.getContent();
        List<KakashiDto> content = kakashiList.stream().map(this::mapToDto).toList();

        PaginatedResponse paginatedResponse = new PaginatedResponse();

        paginatedResponse.setContent(content);
        paginatedResponse.setPageNumber(kakashis.getNumber());
        paginatedResponse.setPageSize(kakashis.getSize());
        paginatedResponse.setLast(kakashis.isLast());
        paginatedResponse.setTotalKakashis(kakashis.getTotalElements());
        paginatedResponse.setTotalPages(kakashis.getTotalPages());  //in - built methods from Page interface

        return paginatedResponse;
    }

    private KakashiDto mapToDto(KakashiEntity kakashiEntity) {

        KakashiDto kakashiDto = new KakashiDto();
        kakashiDto.setCustID(kakashiEntity.getCustID());
        kakashiDto.setFirstName(kakashiEntity.getFirstName());
        kakashiDto.setLastName(kakashiEntity.getLastName());

        return kakashiDto;
    }

    public KakashiEntity getItemById(Long custId) {
        return kakashiRepository.findById(custId).orElse(null);
    }

    public KakashiEntity addItem(KakashiEntity kakashiEntity) {
        return kakashiRepository.save(kakashiEntity);
    }

    public KakashiEntity updateItem(Long custId, KakashiEntity kakashiEntity) {
        Optional<KakashiEntity> existingItem = kakashiRepository.findById(custId);
        if (existingItem.isPresent()) {
            kakashiEntity.setCustID(custId); // Ensure the correct ID is set
            return kakashiRepository.save(kakashiEntity);
        }
        return null; // Return null if the item doesn't exist
    }

    public boolean deleteItem(Long custId) {
        if (kakashiRepository.existsById(custId)) {
            kakashiRepository.deleteById(custId);
            return true;
        }
        return false;
    }
}
