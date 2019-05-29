package pers.ljc.interview.tanmer.service.lawyer;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liujicheng on 2019/5/29.
 */
public class LwayerInfoTrans {

    private static final Map<String, Integer> monthMap = new HashMap<>(12);

    static {
        monthMap.put("January",1);
        monthMap.put("February",2);
        monthMap.put("March",3);
        monthMap.put("April",4);
        monthMap.put("May",5);
        monthMap.put("June",6);
        monthMap.put("July",7);
        monthMap.put("August",8);
        monthMap.put("September",9);
        monthMap.put("October",10);
        monthMap.put("December",11);
        monthMap.put("November",12);
    }

    public static Date transStr2Date(String dateStr) {
        dateStr = dateStr.trim();
        String year = dateStr.substring(dateStr.length() - 5, dateStr.length()).trim();
        String month = dateStr.substring(0, dateStr.length() - 5).trim();
        Integer monthInt = monthMap.get(month);

        Calendar cd = Calendar.getInstance();
        cd.set(Integer.valueOf(year), monthInt, 1, 1, 1, 1);
        Date date = cd.getTime();

        return date;
    }

    public static String transName (String name){
        name = name.replaceAll("'","");
        name = name.replaceAll("\\.","");
        String[] nameArr = name.split(",");
        String middleName = nameArr[0];
        String firstName = nameArr[1];

        String[] middleNameArr = middleName.split(" ");
        String[] firstNameArr = firstName.split(" ");
        StringBuilder resultName = new StringBuilder();
        for (int i = 0; i < firstNameArr.length; i++) {
            String thisItem = firstNameArr[i];
            if (thisItem.trim().length()>0) {
                resultName.append(thisItem);
                resultName.append(" ");
            }
        }

        for (int i = 0; i < middleNameArr.length; i++) {
            String thisItem = middleNameArr[i];
            if (thisItem.trim().length()>0) {
                resultName.append(thisItem);
                resultName.append(" ");
            }
        }

        resultName.deleteCharAt(resultName.length()-1);
        return resultName.toString();
    }

    public static void main(String[] args) {
        Date date = transStr2Date("December 1980");
        System.out.println(date);
    }
}
