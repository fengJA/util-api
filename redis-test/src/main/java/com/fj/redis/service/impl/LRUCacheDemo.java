package com.fj.redis.service.impl;


import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * </p>
 *
 * @author FJ
 * @since 2021-03-30
 */
public class LRUCacheDemo {
    class Node<K, V>{
        K key;
        V value;
        Node<K,V> next;
        Node<K,V> pre;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = this.pre = null;
        }
        public Node(){
            this.next = this.pre = null;
        }
    }

    class DoubleLinkedMap<K, V>{
        Node<K, V> head;
        Node<K, V> tail;
        public DoubleLinkedMap(){
            head = new Node<K, V>();
            tail = new Node<K, V>();
            head.next = tail;
            tail.pre = head;
        }

        // 新增节点到头
        public void addNode(Node<K, V> node){
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
            node.pre = head;
        }

        // 删除节点
        public void deleteNode(Node<K, V> node){
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.pre = null;
            node.next = null;
        }

        // 获取最后一个节点
        public Node getLastNode(){
            return tail.pre;
        }
    }
    private int cacheSize;
    Map<Integer, Node<Integer, Integer>> map;
    DoubleLinkedMap<Integer, Integer> doubleLinkedMap;

    public LRUCacheDemo(int cacheSize){
        this.cacheSize = cacheSize;// 坑位
        map = new HashMap<>();// 查找
        doubleLinkedMap = new DoubleLinkedMap<>();
    }

    public void put(int key, int value) {
        if (map.containsKey(key)){
            // 更新
            Node<Integer, Integer> node = map.get(key);
            node.value = value;
            map.put(key, node);
            doubleLinkedMap.deleteNode(node);
        }else {
            // 坑位满了以后
            if(cacheSize == map.size()){
                Node lastNode = doubleLinkedMap.getLastNode();
                map.remove(lastNode.key);
                doubleLinkedMap.deleteNode(lastNode);
            }
            // 新增新的
            Node<Integer, Integer> node = new Node<>(key, value);
            map.put(key, node);
            doubleLinkedMap.addNode(node);
        }
    }

    public int get(int key){
        if (!map.containsKey(key)){
            return -1;
        }
        Node<Integer, Integer> node = map.get(key);
        doubleLinkedMap.deleteNode(node);
        doubleLinkedMap.addNode(node);
        return node.value;
    }

    public static void main(String[] args) {
//        LRUCacheDemo lruCacheDemo = new LRUCacheDemo(3);
//        lruCacheDemo.put(1,1);
        float f = 121111189111111111111111111117897111111f;
        System.out.println(f);
    }
}
