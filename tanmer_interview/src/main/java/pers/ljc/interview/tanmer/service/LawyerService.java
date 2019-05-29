package pers.ljc.interview.tanmer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ljc.interview.tanmer.dao.ILawyerDao;
import pers.ljc.interview.tanmer.domain.Lawyer;
import pers.ljc.interview.tanmer.service.lawyer.HtmlAnalysisToLawyer;
import pers.ljc.interview.tanmer.service.lawyer.LwayerInfoTrans;
import pers.ljc.interview.tanmer.utils.FileReader;
import pers.ljc.interview.tanmer.vo.LawyerString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujicheng on 2019/5/28.
 */
@Service
public class LawyerService {
    Logger logger = LoggerFactory.getLogger(LawyerService.class);

    @Autowired
    ILawyerDao iLawyerDao;

    public void readLawyerInfo(String... args){

        if (args.length == 0) {
            logger.error("资源位置为空，程序结束......");
            return;
        }

        String recourcePath = args[0];

        if (recourcePath.trim().isEmpty()) {
            logger.error("资源位置为空，程序结束......");
            return;
        }

        String content = null;
        List<LawyerString> domainList = null;

        try {
            if (recourcePath.startsWith("http")) {
                content = FileReader.getContentByNetPage(recourcePath);
            }else {
                content = FileReader.getContentByNativeFile(recourcePath);
            }


             domainList = HtmlAnalysisToLawyer.getLawyerInfoFromHtml(content);

        } catch (Exception e) {
            logger.error("获取资源内容失败 程序结束 错误信息：",e);
            return;
        }

        List<Lawyer> resultList = new ArrayList<>();
        resultList = transInfo(domainList);

        if (null != domainList&& !domainList.isEmpty()) {
            resultList.forEach(item->iLawyerDao.insert(item));
            logger.info("程序执行成功!");
        }else {
            logger.error("获取结果为空   获取信息失败");
        }
    }

    //字符串原始数据转成   需要格式
    private List<Lawyer> transInfo(List<LawyerString> domainList){

        List<Lawyer> list = new ArrayList<>();

        domainList.forEach(item->{
            Lawyer lawyer = new Lawyer();
            lawyer.setLawyerName(LwayerInfoTrans.transName(item.getLawyerName()));
            lawyer.setCity(item.getCity());
            lawyer.setBarNumber(Integer.valueOf(item.getBarNumber()));
            lawyer.setStatus(item.getStatus());
            lawyer.setAdmissionDate(LwayerInfoTrans.transStr2Date(item.getAdmissionDate()));
            list.add(lawyer);
        });

        return list;
    }
}
