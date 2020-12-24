package com.sleepy.zeo.comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorDemo {

    static class Filter {
        int order;
        public String data;

        Filter(int order, String data) {
            this.order = order;
            this.data = data;
        }
    }

    static class FilterComparator implements Comparator<Filter> {

        /**
         * 1. 负数代表左边的值小于右边，0代表左边的值等于右边，正数代表左边的值大于右边
         * 2. 排序是按照从小到大排序的，越小的值越排在上面
         */
        @Override
        public int compare(Filter left, Filter right) {
            return left.order - right.order;
        }
    }

    public static void main(String[] args) {
        List<Filter> list = new ArrayList<>();
        list.add(new Filter(20, "20"));
        list.add(new Filter(15, "14.5"));
        list.add(new Filter(15, "15"));
        list.add(new Filter(25, "25"));
        list.add(new Filter(16, "16"));
        list.add(new Filter(15, "15.5"));
        list.add(new Filter(100, "100"));

        list.sort(new FilterComparator());

        for (Filter filter : list) {
            System.out.println(filter.data);
        }
    }
}
