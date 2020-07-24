package com.fj.test.outerclass;

import lombok.Data;

public class Parcel {
    @Data
    class OuterClass{
        private String nzme = "蛋糕";

        private void as(){
            System.out.println("I am private");
        }

        public void sa(){
            System.out.println("I am public");
        }
    }

    public void wa(){
        OuterClass outerClass = new OuterClass();
        String nzme = outerClass.nzme;
        System.out.println(nzme);
        outerClass.as();
    }

    public static void main(String[] args) {
        Parcel parcel = new Parcel();
        parcel.wa();
        Parcel.OuterClass po = parcel.new OuterClass();
        po.sa();
        Parcel.OuterClass pj = new Parcel().new OuterClass();
        parcel.tr("1").value(); // 打印我是无敌的1
    }

    private void ff(){
        class Ff{
            private String name;
        }

        Ff ff = new Ff();
        String name = ff.name;
    }

    public NewClass tr(String q){
        return new NewClass(){
            String a = q;
            public String value(){
                System.out.println("我是无敌的" + a);
                return a;
            }
        };
    }

}
