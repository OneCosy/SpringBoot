package com.bit.jsptest.model.service;

import com.bit.jsptest.model.FileInfoDto;
import com.bit.jsptest.model.GuestBookDto;
import com.bit.jsptest.model.mapper.GuestBookMapper;
import com.bit.util.PageNavigation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GuestBookServiceImpl implements GuestBookService {

    @Autowired
    private GuestBookMapper guestBookMapper;

    @Override
    @Transactional
    public void registerArticle(GuestBookDto guestBookDto) throws Exception {
        guestBookMapper.registerArticle(guestBookDto);
        List<FileInfoDto> fileInfos = guestBookDto.getFileInfos();
        if (fileInfos != null && !fileInfos.isEmpty()) {
            guestBookMapper.registerFile(guestBookDto);
        }
    }

    @Override
    public List<GuestBookDto> listArticle(Map<String, String> map) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();
        String key = map.get("key");
        if("userid".equals(key))
            key = "g.userid";
        param.put("key", key == null ? "" : key);
        param.put("word", map.get("word") == null ? "" : map.get("word"));
        int currentPage = Integer.parseInt(map.get("pg") == null ? "1" : map.get("pg"));
        int sizePerPage = Integer.parseInt(map.get("spp"));
        int start = (currentPage - 1) * sizePerPage;
        param.put("start", start);
        param.put("spp", sizePerPage);
        return guestBookMapper.listArticle(param);
    }

    @Override
    public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
        PageNavigation pageNavigation = new PageNavigation();

        int naviSize = 10;
        int currentPage = Integer.parseInt(map.get("pg"));
        int sizePerPage = Integer.parseInt(map.get("spp"));

        pageNavigation.setCurrentPage(currentPage);
        pageNavigation.setNaviSize(naviSize);

        int totalCount = guestBookMapper.getTotalCount(map);
        pageNavigation.setTotalCount(totalCount);

        int totalPageCount = (totalCount - 1) / sizePerPage + 1;
        pageNavigation.setTotalPageCount(totalPageCount);

        boolean startRange = currentPage <= naviSize;
        pageNavigation.setStartRange(startRange);

        boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
        pageNavigation.setEndRange(endRange);
        pageNavigation.makeNavigator();

        return pageNavigation;
    }

    @Override
    public GuestBookDto getArticle(int articleNo) throws Exception {
        return guestBookMapper.getArticle(articleNo);
    }

    @Override
    public void updateArticle(GuestBookDto guestBookDto) throws Exception {
        guestBookMapper.updateArticle(guestBookDto);
    }

    @Override
    @Transactional
    public void deleteArticle(int articleNo, String path) throws Exception {
        List<FileInfoDto> fileList = guestBookMapper.fileInfoList(articleNo);
        guestBookMapper.deleteFile(articleNo);
        guestBookMapper.deleteArticle(articleNo);
        for(FileInfoDto fileInfoDto : fileList) {
            File file = new File(path + File.separator + fileInfoDto.getSaveFolder() + File.separator + fileInfoDto.getSaveFile());
            file.delete();
        }
    }

}