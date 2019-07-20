package com.zte.zudp.common.persistence.entity.page;

import com.fasterxml.jackson.annotation.JsonView;
import com.zte.zudp.common.ServletUtils;
import com.zte.zudp.common.config.Configuration;
import com.zte.zudp.common.persistence.entity.BaseEntity;
import com.zte.zudp.common.utils.StringUtils;
import com.zte.zudp.modules.sys.utils.json.View;

import java.util.List;

/**
 * <p>分页实体类，示例如下</p>
 * <blockquote>
 * <pre>{@code
 *      Page.start();
 *      Page result = Page.end(List);
 * }</pre>
 * </blockquote>
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-03-06.
 */
public class Page<T extends BaseEntity> {

    public static final String PAGE = "page";

    private static final String PARAM_PAGE_NO = "page_no";
    private static final String PARAM_PAGE_SIZE = "page_size";

    private static ThreadLocal<Page> local = new ThreadLocal<>();

    private static int PAGE_SIZE;

    @JsonView(View.Default.class)
    private int current;    // 当前页数

    @JsonView(View.Default.class)
    private int size;       // 分页长度

    private int next;       // 下一页数

    private int prev;       // 前一页数

    @JsonView(View.Default.class)
    private int first;      // 第一页数

    @JsonView(View.Default.class)
    private int last;       // 最后一页数

    @JsonView(View.Default.class)
    private List<T> result;   // 分页数据

    @JsonView(View.Default.class)
    private long total;     // 总记录数





    static {
        String size = Configuration.getProperty("sys.page.size");
        if (StringUtils.isEmpty(size)) {
            PAGE_SIZE = 15;
        } else {
            PAGE_SIZE = Integer.parseInt(size);
        }
    }

    /**
     * 请勿直接创建 Page 对象，可能会达不到预期的效果，请翻阅 Page 类注释！
     */
    private Page() {
        this.size = PAGE_SIZE;
        this.current = 1;
        this.next = 1;
        this.prev = 1;
    }

    /**
     * @param current 当前页
     */
    public Page(int current) {
        this();
        this.current = current;
    }

    public Page(int current, int size) {
        this();
        this.current = current;
        this.size = size;
    }

    public static <T extends BaseEntity> Page<T> newInstance(List<T> result) {
        start();
        return end(result);
    }

    public static <T extends BaseEntity> Page<T> newInstance(int cur, int size, List<T> result) {
        start(cur, size);
        return end(result);
    }

    public static void start() {
        String cur = ServletUtils.getHttpRequest().getParameter(PARAM_PAGE_NO);
        String size = ServletUtils.getHttpRequest().getParameter(PARAM_PAGE_SIZE);

        start(StringUtils.of(cur, 1), StringUtils.of(size, Page.PAGE_SIZE));
    }

    public static void start(int cur, int size) {
        local.set(new Page(cur, size));
    }

    public static Page get() {
        return local.get();
    }

    @SuppressWarnings("unchecked")
    public static <T extends BaseEntity> Page<T> end(List<T> result) {
        Page<T> page = local.get();
        local.remove();
        page.setResult(result);
        return page;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    /**
     * <p>在分页拦截器中调用，初始化数据</p>
     * <p>开发者不应该直接调用本方法</p>
     * @param totalNumber 不分页时当前查询返回的总条数
     */
    public void initialized(long totalNumber) {
        first = 1;
        last = (int) ((totalNumber + size - 1) / size);
        total = totalNumber;

        if (last == 0) {
            last++;
        }

        if (current > last) {
            current = last;
        }

        if (current < first) {
            current = first;
        }

        // correct
        next = current + 1;
        prev = current - 1;

        if (isFirstOne()) {
            prev = first;
        }

        if (isLastOne()) {
            next = last;
        }
    }

    /**
     * 当前 Page 对象的 result 属性的长度
     */
    public int count() {
        return getResult().size();
    }

    public boolean isFirstOne() {
        return current == first;
    }

    public boolean isLastOne() {
        return current == last;
    }

    /**
     * 获取当前页的数据总数
     */
    public int currentPageSize() {
        if (isFirstOne()) {
            return count();
        }

        if (isLastOne()) {
            return count() - (current - 1) * size;
        }

        return size;
    }

    public String toHTML() {
        StringBuilder result = new StringBuilder();

        result.append("<a class=\"current\" href=\"");
        result.append(getPageHTML(current, size));
        for (int count = 1; (current + count) <= last && count < 5; count++) {
            result.append("<a href=\"");
            result.append(getPageHTML(current + count, size));
        }

        return result.toString();
    }

    private StringBuilder getPageHTML(Integer current, Integer size) {
        return new StringBuilder().append("javascript:void(0);\" onclick=\"page('")
                                  .append(current)
                                  .append("','")
                                  .append(size)
                                  .append("');\">")
                                  .append(current)
                                  .append("</a>");
    }

    // ---------------------------------------------------------------------------------------------

    public List<T> getResult() {
        return result;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

}
