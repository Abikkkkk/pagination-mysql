package com.example.kakashi.repository;

import com.example.kakashi.model.KakashiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KakashiRepository extends JpaRepository<KakashiEntity,Long> {
}
