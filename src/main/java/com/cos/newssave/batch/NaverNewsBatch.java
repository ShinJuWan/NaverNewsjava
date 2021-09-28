package com.cos.newssave.batch;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cos.newssave.domain.Naver_News;
import com.cos.newssave.domain.Naver_NewsRepository;
import com.cos.newssave.util.Naver_NewsCraw;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class NaverNewsBatch {

	private final Naver_NewsRepository newsRepository;
	private final Naver_NewsCraw naverCraw;
	
	// 초 분 시 일 월 주
	@Scheduled(fixedDelay = 1000*60*1)
	public void testCount() {
		List<Naver_News> newsList = naverCraw.collect5();
		newsRepository.saveAll(newsList);
	}
}
