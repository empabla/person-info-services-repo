package pl.kurs.userservice.mapping;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.userservice.dto.UserDto;
import pl.kurs.userservice.model.AppRole;
import pl.kurs.userservice.model.AppUser;

@Service
public class AppUserToUserDtoConverter implements Converter<AppUser, UserDto> {

    @Override
    public UserDto convert(MappingContext<AppUser, UserDto> mappingContext) {
        AppUser source = mappingContext.getSource();
        return UserDto.builder()
                .id(source.getId())
                .username(source.getUsername())
                .password(source.getPassword())
                .roles(source.getAppRoles()
                        .stream()
                        .map(AppRole::getName)
                        .toArray(String[]::new))
                .build();
    }

}
