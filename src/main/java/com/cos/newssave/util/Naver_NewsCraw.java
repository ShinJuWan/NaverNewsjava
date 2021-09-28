package com.cos.newssave.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cos.newssave.domain.Naver_News;

@Component
public class Naver_NewsCraw {

   int aidNum = 1;
   
   public List<Naver_News> collect5() {
      RestTemplate rt = new RestTemplate();
      List<Naver_News> newsList = new ArrayList<>();
      
      for (int i = 1; i < 6; i++) {
         String aid = String.format("%010d", aidNum);
         String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=102&oid=022&aid="+aid;
         String html = rt.getForObject(url, String.class);

         Document doc = Jsoup.parse(html);
         
         Elements companyElement = doc.select("img").select("[alt]");
         Element titleElement = doc.selectFirst("#articleTitle");
         Element createdAtElement = doc.selectFirst("#main_content > div.article_header > div.article_info > div > span:nth-child(1)");
         String company = companyElement.text();
         String title = titleElement.text();
         String createAt = createdAtElement.text();
         
         Naver_News news = Naver_News.builder()
        	   .company(company)	 
        	   .title(title)
               .createdAt(createAt)
               .build();
         
         newsList.add(news);
         
         aidNum ++;
      }
      return newsList;
   }
}