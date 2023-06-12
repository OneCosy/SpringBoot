package com.bit.util;

import lombok.Data;

@Data
public class PageNavigation {

    private boolean startRange;
    private boolean endRange;
    private int totalCount;
    private int newCount;
    private int totalPageCount;
    private int currentPage;
    private int naviSize;
    private int countPerPage;
    private String navigator;


    public void makeNavigator() {
        int startPage = (currentPage - 1) / naviSize * naviSize + 1;
        int endPage = startPage + naviSize - 1;

        if (totalPageCount < endPage)
            endPage = totalPageCount;

        StringBuilder buffer = new StringBuilder();
        buffer.append("		<ul class=\"pagination\"> \n");
        buffer.append("			<li class=\"page-item\" data-pg=\"1\"> \n");
        buffer.append("				<a href=\"#\" class=\"page-link\">최신</a> \n");
        buffer.append("			</li> \n");
        buffer.append("			<li class=\"page-item\" data-pg=\"" + (this.startRange ? 1 : (startPage - 1)) + "\"> \n");
        buffer.append("				<a href=\"#\" class=\"page-link\">이전</a> \n");
        buffer.append("			</li> \n");

        for (int i = startPage; i <= endPage; i++) {
            buffer.append("			<li class=\"" + (currentPage == i ? "page-item active" : "page-item") + "\" data-pg=\"" + i + "\"><a href=\"#\" class=\"page-link\">" + i + "</a></li> \n");
        }

        buffer.append("			<li class=\"page-item\" data-pg=\"" + (this.endRange ? endPage : (endPage + 1)) + "\"> \n");
        buffer.append("				<a href=\"#\" class=\"page-link\">다음</a> \n");
        buffer.append("			</li> \n");
        buffer.append("			<li class=\"page-item\" data-pg=\"" + totalPageCount + "\"> \n");
        buffer.append("				<a href=\"#\" class=\"page-link\">마지막</a> \n");
        buffer.append("			</li> \n");
        buffer.append("		</ul> \n");

        this.navigator = buffer.toString();
    }
}