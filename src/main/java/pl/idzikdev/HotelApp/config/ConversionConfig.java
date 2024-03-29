package pl.idzikdev.HotelApp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import pl.idzikdev.HotelApp.converter.ReservationEntityToReservationResponseConverter;
import pl.idzikdev.HotelApp.converter.ReservationRequestToReservationEntityConverter;
import pl.idzikdev.HotelApp.converter.RoomEntityToReservableRoomResponseConverter;

import java.util.HashSet;
import java.util.Set;


@Configuration
public class ConversionConfig {
    private Set<Converter> getConverters() {
        Set<Converter> converters = new HashSet<>();
        converters.add(new RoomEntityToReservableRoomResponseConverter());
        converters.add(new ReservationRequestToReservationEntityConverter());
        converters.add(new ReservationEntityToReservationResponseConverter());
        return converters;
    }
    @Bean
    public ConversionService conversionService(){
        ConversionServiceFactoryBean bean=new ConversionServiceFactoryBean();
        bean.setConverters(getConverters());
        bean.afterPropertiesSet();
        return bean.getObject();
    }
}
