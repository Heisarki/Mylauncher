package com.example.myapplication;

public class Pause extends Thread {
    public void run(){
        for(int i=1; i<5; i++){
            try{
                Thread.sleep(10000);
            }
            catch (InterruptedException e){
                System.out.println(e);
            }
            System.out.println(i);
        }
    }

    public static void main(){

        Pause T1= new Pause();
        T1.start();
    }


}
