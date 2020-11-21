package ru.bellintegrator.practice.model;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.bellintegrator.practice.view.user.UserListRequestView;
import ru.bellintegrator.practice.view.user.UserUpdateView;
import ru.bellintegrator.practice.view.user.UserView;

@Configuration
public class MapperConfig {
    @Bean
    public MapperFactory mapperFactory() {

        DefaultMapperFactory.Builder builder = new DefaultMapperFactory.Builder();
        MapperFactory factory = builder.mapNulls(false).build();

        factory.classMap(User.class, UserView.class)
                .field("citizenship.name", "citizenshipName")
                .field("citizenship.code", "citizenshipCode")
                .byDefault()
                .register();

        factory.classMap(Document.class, UserView.class)
                .field("documentType.code", "docCode")
                .field("documentType.name", "docName")
                .byDefault()
                .register();

        return factory;
    }
}
