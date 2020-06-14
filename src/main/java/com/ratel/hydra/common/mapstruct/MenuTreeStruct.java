package com.ratel.hydra.common.mapstruct;

import com.ratel.hydra.system.dto.MenuTree;
import com.ratel.hydra.system.po.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author ratel
 * @date 2020-06-02
 */
@Mapper(componentModel = "spring")
public interface MenuTreeStruct {

    @Mappings({
            @Mapping(target = "icon",source = "icon"),
            @Mapping(target = "title",source = "menuName"),
            @Mapping(target = "href",source = "url"),
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "type",source = "type")
    })
    MenuTree toMenuTree(Menu menu);

    @Mappings({
            @Mapping(target = "icon",source = "icon"),
            @Mapping(target = "menuName",source = "title"),
            @Mapping(target = "url",source = "href"),
            @Mapping(target = "id",source = "id"),
    })
    Menu toMenu(MenuTree menuTree);
}