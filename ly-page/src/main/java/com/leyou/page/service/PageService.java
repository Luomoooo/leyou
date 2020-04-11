package com.leyou.page.service;

import com.leyou.item.pojo.*;
import com.leyou.page.client.BrandClient;
import com.leyou.page.client.CategoryClient;
import com.leyou.page.client.GoodsClient;
import com.leyou.page.client.SpecificationClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PageService {

    @Autowired
    private BrandClient brandClient;
    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private GoodsClient goodsClient;
    @Autowired
    private SpecificationClient specClient;

    @Autowired
    private TemplateEngine templateEngine;

    public Map<String, Object> loadModel(Long spuId) {
        Map<String, Object> model = new HashMap<>();
        Spu spu = goodsClient.querySpuById(spuId);
        List<Category> categories = categoryClient.queryCategoryBtIds(
                Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
        SpuDetail spuDetail = spu.getSpuDetail();
        Brand brand = brandClient.queryBrandById(spu.getBrandId());
        List<SpecGroup> groups = specClient.queryGroupByCid(spu.getCid3());
        List<Sku> skus = spu.getSkus();

        List<SpecParam> params = specClient.queryParamList(null, spu.getCid3(), null, false);

        Map<Long, String> paramMap = new HashMap<>();
        params.forEach(param -> {
            paramMap.put(param.getId(), param.getName());

        });

        model.put("categories", categories);
        model.put("brand", brand);
        model.put("spu", spu);
        model.put("skus", skus);
        model.put("spuDetail", spuDetail);
        model.put("groups", groups);
        model.put("paramMap", paramMap);

        return model;
    }

    public void createHtml(Long spuId) {

        Context context = new Context();
        context.setVariables(loadModel(spuId));
        File file = new File("E:\\Code\\Program\\JAVA\\JAVA_WEB\\leyou\\upload", spuId + ".html");

        try {
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            templateEngine.process("item", context, writer);

        } catch (Exception e) {
            log.error("[静态页服务] 生成异常", e);
        }


    }

    public void deleteHtml(Long spuId) {
        File file = new File("E:\\Code\\Program\\JAVA\\JAVA_WEB\\leyou\\upload", spuId + ".html");
        file.deleteOnExit();
    }
}
