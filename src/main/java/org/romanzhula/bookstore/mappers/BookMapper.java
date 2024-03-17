package org.romanzhula.bookstore.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.romanzhula.bookstore.dto.BookDTO;
import org.romanzhula.bookstore.models.Book;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    Book toModel(BookDTO bookDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    BookDTO toDTO(Book book);
}
