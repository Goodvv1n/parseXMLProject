package com.pleshkov;

import com.pleshkov.util.ConfigService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author pleshkov on 04.10.2018.
 */
@RunWith(JUnit4.class)
public class ConfigTest {

    @Test
    public void configTest(){
        ConfigService configService = ConfigService.getInstance();
        Assert.assertEquals(configService.getFolderPath(), "D:/Work/xmlFolder");
        Assert.assertFalse(configService.isEnableLoading());
    }
}
