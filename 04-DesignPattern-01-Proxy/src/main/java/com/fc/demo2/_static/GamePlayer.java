package com.fc.demo2._static;

// 游戏玩家，被代理代理，真实对象
public class GamePlayer implements GamePlay {
    @Override
    public void login() {
        System.out.println("使用 爷傲奈我何丶 登录游戏ing");
    }

    @Override
    public void killBoss() {
        System.out.println("爷傲奈我何 一刀999");
    }

    @Override
    public void upgrade() {
        System.out.println("恭喜 爷傲奈我何 升了一级");
    }
}
