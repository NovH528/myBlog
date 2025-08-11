package com.ruoyi.web.controller.module;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@RestController
@RequestMapping("/search")
public class SearchProxyController {

    @Resource
    private RestTemplate restTemplate;

    /**
     * 代理百度搜索接口
     */
    @GetMapping("/proxy")
    public AjaxResult proxyBaiduSearch(@RequestParam String keyword) {
        try {
            String encodeKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
            String url = "https://www.baidu.com/s?wd=" + encodeKeyword;

            HttpHeaders headers = new HttpHeaders();
            headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");

            long startTime = System.currentTimeMillis();
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );
            long timeCost = (System.currentTimeMillis() - startTime) / 1000;

            // 关键修改：使用Jsoup解析百度返回的HTML
            Document doc = Jsoup.parse(Objects.requireNonNull(response.getBody())); // 解析响应内容
            Elements resultItems = doc.select("div.result.c-container.xpath-log"); // 百度搜索结果项的选择器

            List<Map<String, String>> results = new ArrayList<>();
            for (Element item : resultItems) {
                // 提取标题（百度标题在h3标签内的a链接）
                Element titleElement = item.selectFirst("h3.t a");
                String title = titleElement != null ? titleElement.text() : "";
                String link = titleElement != null ? titleElement.attr("href") : "";

                // 提取摘要（百度摘要在div.c-abstract内）
                Element summaryElement = item.selectFirst("div.c-abstract");
                String summary = summaryElement != null ? summaryElement.text() : "";

                // 提取真实URL（百度会跳转，这里直接用解析到的链接）
                results.add(Map.of(
                        "title", title,
                        "url", link,
                        "summary", summary
                ));
            }

            JSONObject data = new JSONObject();
            data.put("results", results);
            data.put("total", results.size());
            data.put("timeCost", timeCost);
            return AjaxResult.success(data);

        } catch (Exception e) {
            return AjaxResult.error("搜索失败：" + e.getMessage());
        }
    }
}