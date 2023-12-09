package main.java.day7.domain;

import java.util.Comparator;
import java.util.List;

public class Hand implements Comparable<Hand> {
    String cards;
    int bid;
    HandType type;

    List<Character> cardOrder;

    Comparator<Character> cardComparator;

    public Hand(String cards, int bid, List<Character> cardOrder, HandType type) {
        this.cards = cards;
        this.bid = bid;
        this.cardOrder = cardOrder;
        this.type = type;
        this.cardComparator = Comparator.comparingInt(cardOrder::indexOf);
    }

    public String getCards() {
        return cards;
    }

    public int getBid() {
        return bid;
    }

    public HandType getType() {
        return type;
    }

    @Override
    public int compareTo(Hand hand) {
        if (type.compareTo(hand.getType()) == 0) {
            if (compareCard(hand, 0) == 0) {
                if (compareCard(hand, 1) == 0) {
                    if (compareCard(hand, 2) == 0) {
                        if (compareCard(hand, 3) == 0) {
                            if (compareCard(hand, 4) == 0) {
                                return 0;
                            } else {
                                return compareCard(hand, 4);
                            }
                        } else {
                            return compareCard(hand, 3);
                        }
                    } else {
                        return compareCard(hand, 2);
                    }
                } else {
                    return compareCard(hand, 1);
                }
            } else {
                return compareCard(hand, 0);
            }
        } else {
            return type.compareTo(hand.getType());
        }
    }

    private int compareCard(Hand hand, int index) {
        Character card1 = cards.charAt(index);
        Character card2 = hand.getCards().charAt(index);

        return cardComparator.compare(card1, card2);
    }
}
