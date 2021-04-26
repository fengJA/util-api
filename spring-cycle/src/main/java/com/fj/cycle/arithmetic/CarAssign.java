package com.fj.cycle.arithmetic;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author FJ
 * @since 2021-04-01
 */
public class CarAssign {
    public static void main(String[] args) {
        int[][] sign = {{1,12,5}, {23,1,5},{13,7,9}};
//        int[] a = {1,3,5,7,9};
//        permute(a);
        CarAssign carAssign = new CarAssign();
        carAssign.Solution(sign);

    }

    private double t = 0.00;
    private int[] flag;
    private double res = -1000.00;//0.00只能过80%
    private ArrayList<Integer> add;
    public  void Solution(int[][] nums) {
        int n = nums.length;
        flag = new int[n];
        for(int i = 0;i<n;i++) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(i+1);
            flag[i] = 1;
            t = nums[0][i];
            Best(temp, nums, 1);
            t = 0.00;
            flag[i] = 0;
        }
        System.out.println(String.format("%.2f", res));
        for(int i = 0;i<add.size();i++)
        {
            System.out.println(i+1 +" "+ add.get(i));
        }
    }
    public void Best(ArrayList<Integer> temp, int[][] nums, int end)
    {
        if(end==nums.length)
        {
            if(res<t)
            {
                res = t;
                add = (ArrayList<Integer>) temp.clone();
            }
        }
        for(int i = 0;i<nums.length;i++)
        {
            if(flag[i]!=1)
            {
                flag[i] = 1;
                t += nums[end][i];
                temp.add(i+1);
                Best(temp, nums, end+1);
                temp.remove(temp.size()-1);
                t -= nums[end][i];
                flag[i] = 0;
            }
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        //利用回溯法求解
        //定义一个全局变量保存所有集合
        List<List<Integer>> res = new ArrayList<List<Integer>>();
       if(nums.length==0||nums==null) return res;
       //传入三个参数来进行回溯
       backTrack(res,new ArrayList<Integer>(),nums);
        return res;
    }
    public static void backTrack(List<List<Integer>> res,List<Integer> list,int [] nums){
       //终结条件，满足条件的时候
       if(list.size()==nums.length) {
            //将满足条件的解加入到解集之中，全局添加一个满足条件的集合
             res.add(new ArrayList<Integer>(list));
             return;//这个可有可无因为是没有返回值的函数。为了方便理解这里的return是回到else循环里面的backTrack，循环全部结束子函数才会运行结束
       } else{
            for(int i =0;i<nums.length;i++){
                if(list.contains(nums[i])) continue;
                //list中不包含nums[i]的时候才添加
                list.add(nums[i]);
                //递归调用，此时的list一直在变化
                backTrack(res,list,nums);
                System.out.println("tempList回溯之后的内容:"+list+"-------"+"i的值:"+i);
                //它移除tempList最后一个元素的作用就是返回上一次调用时的数据，也就是希望返回之前的节点再去重新搜索满足条件。这样才能实现回溯
                //移除的过程一再在做递归，同样在循环
                list.remove(list.size()-1);
            }
         }
    }
}
