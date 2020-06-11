package com.ratel.hydra.system.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.Map;

/**
 * @author ratel
 * @date 2020-06-09
 */
@Data
public class PageQuery<T> {
    private T query;
    private Map<String,Object> queryParam;
    private Page page = new Page();
}
