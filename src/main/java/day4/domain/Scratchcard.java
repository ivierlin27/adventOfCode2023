package main.java.day4.domain;

import java.util.List;

public class Scratchcard {
    int cardNumber;
    List<Integer> winningNumbers;
    List<Integer> myNumbers;

    public Scratchcard(int cardNumber, List<Integer> winningNumbers, List<Integer> myNumbers) {
        this.cardNumber = cardNumber;
        this.winningNumbers = winningNumbers;
        this.myNumbers = myNumbers;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public List<Integer> getMyNumbers() {
        return myNumbers;
    }
}
