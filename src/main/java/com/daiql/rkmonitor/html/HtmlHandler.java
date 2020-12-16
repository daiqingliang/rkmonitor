package com.daiql.rkmonitor.html;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author daiql
 * @description html处理
 * @date 2020/12/16 21:30
 */
public class HtmlHandler {

    public static List<String> getUnorderedList(String url) {
        List<String> stringList = new ArrayList<>();
        try {
            WebClient wc = new WebClient();
            wc.setJavaScriptTimeout(5000);
            wc.getOptions().setUseInsecureSSL(true);//接受任何主机连接 无论是否有有效
            wc.getOptions().setJavaScriptEnabled(true);//设置支持javascript脚本
            wc.getOptions().setCssEnabled(false);
            wc.getOptions().setThrowExceptionOnScriptError(false);
            wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
            wc.getOptions().setDoNotTrackEnabled(false);

            HtmlPage htmlPage = wc.getPage(url);
            HtmlPage nextpage = htmlPage.getAnchorByHref("https://query.ruankao.org.cn/score/main").click();
//        System.out.println("**************nextPage***************");
//        System.out.println(nextpage.asText());
//        System.out.println("*********************************");
            HtmlElement htmlElement = (HtmlElement)nextpage.getByXPath("//ul[@class='select']").get(0);
            if (htmlElement != null) {
                Iterable<DomElement> elements = htmlElement.getChildElements();
                for (DomElement element : elements) {
                    stringList.add(element.asText());
//                    System.out.println("**************************************************************************");
//                    System.out.println(element.asText());
//                    System.out.println("**************************************************************************");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringList;
    }

    public static void main(String[] args) {
        String url = "https://www.ruankao.org.cn/";
        List<String> list = getUnorderedList(url);
        System.out.println(list);
    }
}
