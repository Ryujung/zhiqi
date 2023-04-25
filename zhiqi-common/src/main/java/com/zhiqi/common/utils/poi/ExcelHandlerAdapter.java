package com.zhiqi.common.utils.poi;

/**
 * @author RyuJung
 * @since 2023/4/15-10:59
 */
public interface ExcelHandlerAdapter {
    /**
     * 格式化
     *
     * @param value 单元格数据值
     * @param args  excel注解args参数组
     * @return 处理后的值
     */
    Object format(Object value, String[] args);

}
