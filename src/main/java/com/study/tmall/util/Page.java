package com.study.tmall.util;

public class Page {
    private int start;//每页开始位置
    private int count;//每页的数量
    private int total;//总数
    private String param;
    private static final int defaultcount=5;

    public Page() {
        count=defaultcount;
    }

    public Page(int start, int count) {
        //this();
        this.start = start;
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
    //总页数
    public int getTotalPage(){
        if(total==0) return 1;
        if(total%count!=0) return total/count+1;
        return total/count;
    }
    //最后一页开始的序号
    public int getLast(){
        if(total==0) return 0;
        int index;
        if(total%count==0) index=total-count;
        else index=total-total%count;
        return index;
    }
    public boolean isHasPrevious(){
        return start>0;
    }
    public boolean isHasNext(){
        return start<getLast();
    }
    //@Override
    public String toString(){
        return "Page [start="+start+", count="+count+", total="+total+", getStart()="
                +getStart()+", getCount()="+getCount()+", getTotalPage()"+getTotalPage()
                +", getLast()"+getLast()+", isHasPrevious()"+isHasPrevious()+", isHasNext()"
                +isHasNext()+"]";
    }
}
