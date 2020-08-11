package com.ssafy.nnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.nnd.dto.Image;

public interface ImageRepository extends JpaRepository<Image, Long>  {
	
}
