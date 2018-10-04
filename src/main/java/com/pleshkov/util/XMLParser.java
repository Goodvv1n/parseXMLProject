package com.pleshkov.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

//import static jdk.nashorn.internal.codegen.ObjectClassGenerator.LOG;

/**
 * Парсер XML файлов
 * @author pleshkov on 04.10.2018.
 */
public class XMLParser {

    /**
     * Парсинг и загрузка данных из файла
     * @param file файл
     * @param clazz Класс загружаемых объектов
     */
    public static <T> Object parseFile(File file, Class<T> clazz) throws JAXBException {
        JAXBContext jaxbContext;
        jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return jaxbUnmarshaller.unmarshal(file);
    }
}
