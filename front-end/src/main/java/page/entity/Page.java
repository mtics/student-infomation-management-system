package page.entity;

import java.util.List;

/**
 * 定义一个泛型类
 * @param <T>
 */
public class Page<T> {

    private int currentPage;        // 当前页

    private int currentIndex;       // 当前数据索引

    private int totalPages;         // 总页数

    private int pageSize;              // 一页多少数据

    private List<T> list;              // 所有的数据列表

    private int totalCount;         // 数据总条数

    public Page(int currentPage, int pageSize, List<T> list) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.list = list;
        this.totalCount = list.size();

        // 计算最大页数
        // 总记录数除以每页数据条数，根据余数设置页数
        // 若有余数，则页数=商+1；否则页数=商
        if(totalCount % pageSize == 0){
            this.totalPages = totalCount / pageSize;
        }else{
            this.totalPages = totalCount / pageSize + 1;
        }

        this.currentIndex = (currentPage - 1) *pageSize;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }
}