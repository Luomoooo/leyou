package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {
    INVALID_FILE_TYPE(400, "无效文件类型"),
    GOODS_ID_CANNOT_BE_NULL(400, "商品ID不能为空"),
    BRAND_SAVE_ERROR(500, "新增品牌失败"),
    INSERT_GOODS_ERROR(500, "新增商品失败"),
    UPLOAD_FILE_ERROR(500, "文件上传失败"),
    GOODS_UPDATE_ERROR(500, "商品更新失败"),
    BRAND_NOT_FOND(404, "品牌不存在"),
    CATEGORY_NOT_FOND(404, "商品分类没有查到"),
    SPEC_GROUP_NOT_FOND(404, "商品组不存在"),
    SPEC_PARAM_NOT_FOND(404, "商品规格参数不存在"),
    GOODS_NOT_FOND(404, "商品不存在"),
    GOODS_DETAIL_NOT_FOND(404, "商品详情不存在"),
    GOODS_SKU_NOT_FOND(404, "商品SKU不存在"),
    GOODS_STOCK_NOT_FOND(404, "商品库存不存在")
    ;
    private int code;
    private String msg;


}
