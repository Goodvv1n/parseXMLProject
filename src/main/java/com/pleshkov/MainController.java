package com.pleshkov;

import com.pleshkov.data.entity.LoadInfo;
import com.pleshkov.services.LoadInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pleshkov on 03.10.2018.
 */
@RestController
@RequestMapping("/")
public class MainController {

    private static final String template = "Hello, %s!";

    private final LoadInfoService loadInfoService;

    @Autowired
    public MainController(LoadInfoService loadInfoService) {
        this.loadInfoService = loadInfoService;
    }

    @RequestMapping("/")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        loadInfoService.saveInfo(generate());
        return String.format(template, name);
    }

    private LoadInfo generate(){
        LoadInfo loadInfo = new LoadInfo();
        loadInfo.setFileName("file1");
        loadInfo.setLog("Ololo");
        return loadInfo;
    }
}
