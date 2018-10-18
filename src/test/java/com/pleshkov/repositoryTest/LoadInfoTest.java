package com.pleshkov.repositoryTest;

import com.pleshkov.data.entity.LoadInfo;
import com.pleshkov.services.LoadInfoService;
import com.pleshkov.util.XMLParser;
import com.pleshkov.beans.xml.XMLSales;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author pleshkov on 17.10.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoadInfoTest {

    private static final String FILENAME = "fileName";

    @Autowired
    LoadInfoService service;

    @After
    public void clear(){
        for (LoadInfo info : service.getAll()){
            service.deleteLoadInfo(info);
        }
    }

    @Test
    public void addTest() throws JAXBException, FileNotFoundException {
        List<LoadInfo> list = service.getAll();
        Assert.assertNotNull(list);
        int size = list.size();
        service.saveInfo(FILENAME, generateInfo());

        list = service.getAll();
        Assert.assertNotNull(list);
        Assert.assertEquals(size + 1, list.size());
    }

    @Test
    public void existTest() throws JAXBException, FileNotFoundException {
        Assert.assertFalse(service.isLoadInfoExist(FILENAME));
        service.saveInfo(FILENAME, generateInfo());
        Assert.assertTrue(service.isLoadInfoExist(FILENAME));
    }

    @Test
    public void saveExceptionTest(){
        Assert.assertFalse(service.isLoadInfoExist(FILENAME ));
        Exception e = new Exception("Text");
        service.saveInfo(FILENAME, e);
        Assert.assertTrue(service.isLoadInfoExist(FILENAME ));
        boolean found = false;
        for (LoadInfo info : service.getAll()){
            if (info.getFileName().equalsIgnoreCase(FILENAME)){
                Assert.assertEquals(info.getLog(), "Text");
                found = true;
            }
        }
        Assert.assertTrue(found);
    }

    private XMLSales generateInfo() throws JAXBException, FileNotFoundException {
        File file = ResourceUtils.getFile(this.getClass().getResource("/text.xml"));
        return (XMLSales) XMLParser.parseFile(file, XMLSales.class);
    }
}
