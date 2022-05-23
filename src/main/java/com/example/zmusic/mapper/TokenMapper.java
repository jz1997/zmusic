package com.example.zmusic.mapper;

import com.example.zmusic.dto.TokenDto;
import com.example.zmusic.vo.TokenVo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenMapper {
    TokenVo toVo(TokenDto tokenDto);
}