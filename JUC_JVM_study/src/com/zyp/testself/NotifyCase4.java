package com.zyp.testself;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/15 13:12
 */
class Test4{
    private int num;
    private char word;
    private int flag;

    public synchronized void printNum(){
        while (flag!=1){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 2; i++) {
            System.out.print(num++);
        }
        this.notifyAll();
        flag=2;
    }

    public synchronized void printWord(){
        while (flag!=2){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print((char) word++);
        this.notifyAll();
        flag=1;
    }


    public void setNum(int num) {
        this.num = num;
    }

    public void setWord(char word) {
        this.word = word;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getNum() {
        return num;
    }

    public char getWord() {
        return word;
    }
}
public class NotifyCase4 {
    public static void main(String[] args) {
        Test4 test4 = new Test4();
        test4.setFlag(1);
        test4.setNum(1);
        test4.setWord('A');
        new Thread(()->{
            /*for (int i = 0; i < 26; i++) {
                test4.printNum();
            }*/
            while (test4.getNum()<=52){
                test4.printNum();
            }
        }).start();
        new Thread(()->{
            /*for (int i = 0; i < 26; i++) {
                test4.printWord();
            }*/
            while (test4.getWord()<=90){
                test4.printWord();
            }
        }).start();
    }
}
