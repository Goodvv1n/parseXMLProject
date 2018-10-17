package com.pleshkov.repositoryTest;

import com.pleshkov.data.entity.SaleEvent;
import com.pleshkov.services.SaleEventService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author pleshkov on 15.10.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleEventTest {

    @Autowired
    SaleEventService service;

    @After
    public void clear(){
        for (SaleEvent event : service.getSaleEventList()){
            service.delete(event);
        }
    }

    @Test
    public void addTest(){
        List<SaleEvent> list = service.getSaleEventList();
        Assert.assertNotNull(list);
        int size = list.size();

        SaleEvent savedItem = service.save(generate());
        Assert.assertNotNull(savedItem);
        Assert.assertNotNull(savedItem.getId());

        list = service.getSaleEventList();
        Assert.assertNotNull(list);
        Assert.assertEquals(size + 1, list.size());
    }

    @Test
    public void findByCardIdTest(){
        List<SaleEvent> list = service.getSaleEventList(111L);
        Assert.assertNotNull(list);
        int size = list.size();

        SaleEvent savedItem = service.save(generate());
        Assert.assertNotNull(savedItem);
        Assert.assertNotNull(savedItem.getId());
        Assert.assertNotNull(savedItem.getDate().equals(new Date(978296400000L)));

        list = service.getSaleEventList(111L);
        Assert.assertNotNull(list);
        Assert.assertEquals(size + 1, list.size());
    }

    @Test
    public void findByDateTest(){
        Date date = new Date(978296400000L);
        List<SaleEvent> list = service.getSaleEventList(date);
        Assert.assertNotNull(list);
        int size = list.size();

        SaleEvent savedItem = service.save(generate());
        Assert.assertNotNull(savedItem);
        Assert.assertNotNull(savedItem.getId());
        Assert.assertNotNull(savedItem.getDate().equals(new Date(978296400000L)));

        list = service.getSaleEventList(date);
        Assert.assertNotNull(list);
        Assert.assertEquals(size + 1, list.size());

        SaleEvent newEvent = generate();
        newEvent.setDate(new Date(978296600000L));
        service.save(newEvent);

        list = service.getSaleEventList(date);
        Assert.assertNotNull(list);
        Assert.assertEquals(size + 2, list.size());
    }

    @Test
    public void findByCardIdAndDateTest(){
        Date date = new Date(978296400000L);
        List<SaleEvent> list = service.getSaleEventList(222L, date);
        Assert.assertNotNull(list);

        SaleEvent event = generate();
        event.setCardId(222L);
        service.save(event);

        list = service.getSaleEventList(222L, date);
        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());

        SaleEvent newEvent = generate();
        newEvent.setDate(new Date(978296600000L));
        service.save(newEvent);

        list = service.getSaleEventList(111L, date);
        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
    }

    @Test
    public  void deleteTest(){
        SaleEvent savedItem = service.save(generate());
        List<SaleEvent> list = service.getSaleEventList();
        Assert.assertNotNull(list);
        int size = list.size();

        service.delete(savedItem);

        list = service.getSaleEventList();
        Assert.assertNotNull(list);
        Assert.assertEquals(size - 1, list.size());
    }

    private SaleEvent generate(){
        SaleEvent saleEvent = new SaleEvent();
        saleEvent.setCardId(111L);
        saleEvent.setDate(new Date(978296400000L));
        return saleEvent;
    }
}
