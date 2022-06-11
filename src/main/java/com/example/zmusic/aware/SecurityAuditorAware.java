package com.example.zmusic.aware;

import com.example.zmusic.dto.UserDto;
import com.example.zmusic.entity.User;
import com.example.zmusic.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityAuditorAware implements AuditorAware<User> {

    private UserMapper userMapper;

    @Override
    @NonNull
    public Optional<User> getCurrentAuditor() {
        Object user = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (user instanceof UserDto u) {
            return Optional.of(userMapper.toEntity(u));
        }
        return Optional.empty();
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
