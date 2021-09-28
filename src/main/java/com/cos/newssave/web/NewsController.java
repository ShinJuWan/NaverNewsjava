package com.cos.newssave.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.newssave.domain.Naver_News;
import com.cos.newssave.domain.Naver_NewsRepository;
import com.cos.newssave.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class NewsController {

   private final Naver_NewsRepository newsRepository;
   
   @GetMapping("/news")
   public CMRespDto<List<Naver_News>> findAll(){
      return new CMRespDto<>(1, "성공", newsRepository.findAll());
   }
}