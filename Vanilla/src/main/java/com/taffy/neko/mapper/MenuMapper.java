package com.taffy.neko.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taffy.neko.entity.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(long userId);
}
