package TW2.mapper;

import TW2.dto.*;
import TW2.model.Ads;
import TW2.model.Comments;
import TW2.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


/**
 * Интерфейс для преобразования (маппинга) между сущностями и DTO.
 * <p>
 * Этот интерфейс использует библиотеку MapStruct для автоматической генерации кода
 * преобразования между объектами различных типов.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface Mappers {

    /**
     * Преобразует объект Users в UserDto.
     *
     * @param users объект пользователя.
     * @return объект UserDto.
     */
    @Mapping(target = "image", ignore = true)
    UserDto toUserDto(Users users);

    /**
     * Преобразует RegisterDto в объект Users.
     *
     * @param registerDto объект данных для регистрации.
     * @return объект Users.
     */
    @Mapping(target = "email", source = "username")
    @Mapping(target = "id", ignore = true)
    Users toUsers(RegisterDto registerDto);

    /**
     * Преобразует объект Users в UpdateUserDto.
     *
     * @param users объект пользователя.
     * @return объект UpdateUserDto.
     */
    UpdateUserDto toUpdateUserDto(Users users);

    /**
     * Преобразует объект Comments в CommentDto.
     *
     * @param comments объект комментария.
     * @return объект CommentDto.
     */
    @Mapping(source = "users.id", target = "author")
    @Mapping(source = "users.firstName", target = "authorFirstName")
    CommentDto toCommentDto(Comments comments);

    /**
     * Преобразует CreateOrUpdateCommentDto в Comments и устанавливает текущее время создания.
     *
     * @param commentDto объект данных для создания или обновления комментария.
     * @return объект Comments.
     */
    @Mapping(expression = "java(System.currentTimeMillis())", target = "createdAt")
    Comments toComments(CreateOrUpdateCommentDto commentDto);

    /**
     * Преобразует объект Ads в ExtendedAdDto, включая информацию о пользователе.
     *
     * @param ads объект объявления.
     * @return объект ExtendedAdDto.
     */
    @Mapping(source = "users.firstName", target = "authorFirstName")
    @Mapping(source = "users.lastName", target = "authorLastName")
    @Mapping(source = "users.email", target = "email")
    @Mapping(source = "users.phone", target = "phone")
    ExtendedAdDto toExtendedAdDto(Ads ads);

    /**
     * Преобразует объект Ads в AdDto, устанавливая автора объявления.
     *
     * @param ads объект объявления.
     * @return объект AdDto.
     */
    @Mapping(source = "users.id", target = "author")
    AdDto toAdDto(Ads ads);

    /**
     * Преобразует CreateOrUpdateAdDto в Ads.
     *
     * @param adDto объект данных для создания или обновления объявления.
     * @return объект Ads.
     */
    Ads toAds(CreateOrUpdateAdDto adDto);
}