package com.pleshkov;

import com.pleshkov.data.entity.LoadInfo;
import com.pleshkov.services.LoadInfoService;
import com.pleshkov.services.SaleService;
import com.pleshkov.beans.view.FormParams;
import com.pleshkov.beans.view.TopProduct;
import com.pleshkov.beans.view.ViewProductSale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Главный контроллер. Отвечает за работу с View частью
 * @author pleshkov on 03.10.2018.
 */
@Controller
public class MainController {

    @Autowired
    SaleService saleService;

    @Autowired
    LoadInfoService loadInfoService;

    /**
     * Список покупок
     * @param saleParams параметры формы
     * @param model модель
     * @return УРЛ страницы
     */
    @RequestMapping("/saleList")
    public String saleList(@ModelAttribute FormParams saleParams, Model model) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Long cardId = null;
        try {
            if (saleParams != null
                    && saleParams.getDate() != null
                    && !saleParams.getDate().isEmpty()) {
                date = sdf.parse(saleParams.getDate());
            }
        } catch (ParseException e) {
            System.out.println("Ошибка при парсинге даты");
        }
        if (saleParams != null && saleParams.getCardId() != 0){
            cardId = saleParams.getCardId();
        }
        List<ViewProductSale> productSaleList;
        if (cardId != null){
            productSaleList = saleService.getViewSaleList(cardId, date);
        } else {
            productSaleList = new ArrayList<>();
        }
        model.addAttribute("productList", productSaleList);
        model.addAttribute("sum", saleService.resultSum(productSaleList));
        model.addAttribute("saleCount", saleService.saleCount(productSaleList));
        model.addAttribute("absSaleCount", saleService.absoluteSaleCount());
        return "saleList";
    }

    /**
     * Сумма чеков за день
     * @param saleParams параметры формы
     * @param model можель
     * @return УРЛ формы
     */
    @RequestMapping("/payOnDay")
    public String payOnDay(@ModelAttribute FormParams saleParams, Model model) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            if (saleParams != null
                    && saleParams.getDate() != null
                    && !saleParams.getDate().isEmpty()) {
                date = sdf.parse(saleParams.getDate());
            }
        } catch (ParseException e) {
            System.out.println("Ошибка при парсинге даты");
        }
        model.addAttribute("absSaleCount", saleService.absoluteSaleCount());
        model.addAttribute("sumOnDay",  saleService.getSumOnDay(date));
        return "payOnDay";
    }

    /**
     * Список ТОП-3 продуктов
     * @param formParams параметры формы
     * @param model можель
     * @return УРЛ формы
     */
    @RequestMapping("/topList")
    public String topList(@ModelAttribute FormParams formParams, Model model) {
        Long cardId = null;
        if (formParams != null && formParams.getCardId() != 0){
            cardId = formParams.getCardId();
        }
        List<TopProduct> topList = new ArrayList<>();
        if (cardId != null){
            List<ViewProductSale> productSaleList = saleService.getViewSaleList(cardId, null);
            topList = saleService.getTopList(cardId);
        }
        model.addAttribute("topList", topList);
        return "topList";
    }

    /**
     * Загруженные файлы
     * @param model модель
     * @return УРЛ формы
     */
    @RequestMapping("/loadedFiles")
    public String topList(Model model) {
       List<LoadInfo> fileList = loadInfoService.getAll();
        model.addAttribute("fileList", fileList);
        return "loadedFiles";
    }
}
