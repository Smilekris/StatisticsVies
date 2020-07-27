package com.smile.monkeydebugger.service;

/**
 * @ClassName ChainAsbtract
 * @Author kris
 * @Date 2020/7/27
 * 责任链
 **/
public abstract class ChainAsbtract {

    protected int order;

    private ChainAsbtract next;

    public int getOrder(){
        return this.order;
    }

    public void setNext(ChainAsbtract next){
        this.next = next;
    }

    protected abstract void doPrint(String s);

    public void print(String s){
        this.doPrint(s);
        if(this.next!= null){
            this.next.print(s);
        }
    }
}
