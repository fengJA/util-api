package com.fj.test.utils.clone;

import lombok.Data;

import java.util.*;

@Data
public class CloneTest<K, V> implements Cloneable,Comparable{
    private transient Entry<?,?>[] table;
    private Integer num;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        CloneTest t = (CloneTest<K, V>) super.clone();
        t.table = new Entry<?,?>[table.length];
        for (int i = 0; i < table.length; i++) {
            t.table[i] = (table[i] != null) ?
                    (Entry<?,?>) table[i].clone() : null;
        }
        return t;
    }

    @Override
    public int compareTo(Object o) {
        o = (CloneTest<K, V>) o;
        return (this.num < ((CloneTest) o).num) ? -1 : ((this.num == ((CloneTest) o).num) ? 0 : 1);
    }

    private static class Entry<K,V>{
        final K key;
        V value;
        Entry<K,V> next;

        protected Entry(K key, V value, Entry<K,V> next) {
            this.key =  key;
            this.value = value;
            this.next = next;
        }

        /**
         *  @SuppressWarnings("unchecked")
         *  告诉编译器忽略 unchecked 警告信息，允许使用重复的名称。忽略第二个和后面出现的名称。
         *  这里的clone()实现深度拷贝；不采用for循环方式，针对列表，每个元素都会消耗一段栈空间，如果链表太长，会栈溢出
          * @return
         */
        @SuppressWarnings("unchecked")
        protected Object clone() {
            return new Entry<>(key, value,
                    (next==null ? null : (Entry<K,V>) next.clone()));
        }
    }

    public static void main(String[] args) {
//        HashMap<String, String> tab = new HashMap<>(3);
//        tab.put("1", "1");
//        // Aa BB 的hash值是相同的，都是2112，所以这两条数据会在一个链表里
//        tab.put("Aa", "11");
//        tab.put("BB", "66");
//        int beforeSize = tab.size();
//        System.out.println(beforeSize);
//        // 初始大小是3，再增加元素，会触发扩容
//        tab.put("2", "6");
//        int afterSize = tab.size();
//        System.out.println(afterSize);
        CloneTest<Object, Object> test = new CloneTest<>();
        CloneTest<Object, Object> test2 = new CloneTest<>();
        CloneTest<Object, Object> test3 = new CloneTest<>();
        test.setNum(12);
        test2.setNum(15);
        test3.setNum(9);
        CloneTest[] a = new CloneTest[]{test,test2,test3};

        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i].num);   // 输出 12 15 9
        }
        System.out.println("============");
        Arrays.sort(a);

        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i].num);  // 输出  9 12 15
        }

    }
}
