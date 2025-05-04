package com.gersimuca.erp.feature.user;

import com.gersimuca.erp.common.MapperConfig;
import com.gersimuca.erp.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.oauth2.jwt.Jwt;

@Mapper(config = MapperConfig.class, uses = JwtMapper.class)
public interface UserMapper {
  UserDto mapToDto(UserEntity userEntity);

  UserModel mapToModel(UserDto userDto);

  @Mapping(target = "userId", ignore = true)
  @Mapping(target = "preferredLanguage", ignore = true)
  @Mapping(source = "token", target = "givenName", qualifiedBy = JwtMapper.GivenNameMapper.class)
  @Mapping(source = "token", target = "familyName", qualifiedBy = JwtMapper.FamilyNameMapper.class)
  @Mapping(source = "token", target = "username", qualifiedBy = JwtMapper.UsernameMapper.class)
  @Mapping(source = "token", target = "email", qualifiedBy = JwtMapper.EMailMapper.class)
  UserDto mapToDto(Jwt token);
}
