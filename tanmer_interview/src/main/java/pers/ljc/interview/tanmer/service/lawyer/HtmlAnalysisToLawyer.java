package pers.ljc.interview.tanmer.service.lawyer;

import pers.ljc.interview.tanmer.vo.LawyerString;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liujicheng on 2019/5/28.
 * 从html中分解出需要的信息
 */
public class HtmlAnalysisToLawyer {

    public static List<LawyerString> getLawyerInfoFromHtml(String html) throws Exception {
        //取出所有换行符
        html = html.replaceAll("\\r", "");
        html = html.replaceAll("\\n", "");
        html = html.replaceAll("\\t", "");
        //获取table
        List<String> tableList = getListContentByPattern(html,getPattrenStrByTagName("table"));

        if (tableList==null||tableList.isEmpty()) {
            throw new Exception("没有找到table");
        }

        //获取tr
        List<String> trList = getListContentByPattern(html,getPattrenStrByTagName("tr"));



        return getLawyerInfoByTr(trList);
    }

    private static List<LawyerString> getLawyerInfoByTr(List<String> content){
        List<LawyerString> list = new ArrayList<>();
        for (int i = 1; i < content.size(); i++) {
            LawyerString lawyer = getLawyerInfoByTr(content.get(i));
            list.add(lawyer);
        }
        return list;
    }

    private static  LawyerString getLawyerInfoByTr(String perLawyerHtml){
        //去掉所有的= 干扰
        perLawyerHtml = perLawyerHtml.replaceAll("=","");
        List<String> tdList = getListContentByPattern(perLawyerHtml,getPattrenStrByTagName("td"));

        //取得第一个td -  名字
        String nameTd = tdList.get(0);

        String name = deleteTage(nameTd, "td");
        name = deleteTage(name, "a");

        LawyerString domain = new LawyerString();
        domain.setLawyerName(name);
        domain.setStatus(deleteTage(tdList.get(1),"td"));
        domain.setBarNumber(deleteTage(tdList.get(2),"td"));
        domain.setCity(deleteTage(tdList.get(3),"td"));
        domain.setAdmissionDate(deleteTage(tdList.get(4),"td"));



        return domain;
    }


    //获取不包含标签本身的内容
    private static String getPattrenStrByTagNameNoTag(String tagname){
        return "(?<=(<[^("+tagname+")^/]{1,20}?"+tagname+"[\\s\\S]{1,20}?>))[\\s\\S]*?(?=(<[^("+tagname+")]{1,20}?"+tagname+"[\\s\\S]{1,20}?>))";
    }

    //获取标签头正则
    private static String getPattrenTagPre(String tagname){
        return "<[^("+tagname+")^/]*?"+tagname+"[\\s\\S]*?>";
    }

    //去除标签
    private static String deleteTage(String content,String tagName){
        content = content.replaceAll(getPattrenTagPre(tagName),"");
        content = content.replaceAll(getPattrenTagLast(tagName),"");
        return content;
    }

    //获取标签尾
    private static String getPattrenTagLast(String tagname){
        return "<[^("+tagname+")]*?"+tagname+"[\\s\\S]*?>";
    }

    //获取包含标签本身的内容
    private static String getPattrenStrByTagName(String tagname){
        return "<[^("+tagname+")^/]*?"+tagname+"[\\s\\S]*?>[\\s\\S]*?<[^("+tagname+")]*?"+tagname+"[\\s\\S]*?>";
    }

    private static List<String> getListContentByPattern(String content,String pattren){
        List<String> result = new ArrayList<>();

        Pattern pattern = Pattern.compile(pattren);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }


}
