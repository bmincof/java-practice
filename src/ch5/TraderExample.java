package ch5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TraderExample {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );

        // 2011년에 일어난 모든 트랜잭션을 값 오름차순으로
        System.out.println(
            transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .toList()
        );

        // 거래자가 근무하는 모든 도시를 중복없이
        System.out.println(
            transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .toList()
        );

        // 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로
        System.out.println(
            transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(trader -> trader.getCity().equalsIgnoreCase("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .toList()
        );

        // 모든 거래자의 이름을 알파벳순으로
        System.out.println(
            transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .toList()
        );

        // 밀라노에 거래자가 있는지 확인
        System.out.println(
            transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equalsIgnoreCase("Milan"))
        );

        // 케임브리지에 거주하는 거래자의 모든 트랜잭션 값
        System.out.println(
            transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equalsIgnoreCase("Cambridge"))
                .map(Transaction::getValue)
                .toList()
        );

        // 전체 트랜잭션 중 최댓값
        System.out.println(
            transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .get()
        );

        // 전체 트랜잭션 중 최솟값
        System.out.println(
            transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min)
                .get()
        );
    }

}
