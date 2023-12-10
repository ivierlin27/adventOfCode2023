package main.java.day7;

import main.java.day7.domain.Hand;
import main.java.day7.domain.HandType;
import main.java.domain.Input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Phase2 {
    List<Hand> hands = new ArrayList<>();

    List<Character> cardOrder = List.of(
            'A',
            'K',
            'Q',
            'T',
            '9',
            '8',
            '7',
            '6',
            '5',
            '4',
            '3',
            '2',
            'J'
    );

    public List<Hand> getHands() {
        return hands;
    }

    public long parseInput(Input input) {
        String[] parts = input.getCurrentLine().split(" ");

        hands.add(new Hand(parts[0], Integer.parseInt(parts[1]), cardOrder, evaluateType(parts[0])));

        return 0;
    }

    private HandType evaluateType(String cards) {
        Map<Character, Integer> hand = new LinkedHashMap<>();
        int jokerCount = 0;
        for (int x = 0; x < cards.length(); x++) {
            Character character = cards.charAt(x);
            if (character == 'J') {
                jokerCount++;
            } else if (hand.containsKey(character)) {
                hand.put(character, hand.get(character) + 1);
            } else {
                hand.put(character, 1);
            }
        }

        if (jokerCount > 0 && jokerCount < 5) {
            Map.Entry<Character, Integer> entry = Collections.max(hand.entrySet(), Map.Entry.comparingByValue());
            hand.put(entry.getKey(), entry.getValue() + jokerCount);
        } else if (jokerCount == 5) {
            return HandType.FIVE_OF_A_KIND;
        }

        if (hand.containsValue(5)) {
            return HandType.FIVE_OF_A_KIND;
        } else if (hand.containsValue(4)) {
            return HandType.FOUR_OF_A_KIND;
        } else if ((hand.containsValue(3) || hand.containsValue(2)) && hand.size() == 2) {
            return HandType.FULL_HOUSE;
        } else if (hand.containsValue(3) && hand.size() > 2) {
            return HandType.THREE_OF_A_KIND;
        } else if (hand.containsValue(2) && hand.size() == 3) {
            return HandType.TWO_PAIR;
        } else if (hand.containsValue(2) && hand.size() == 4) {
            return HandType.ONE_PAIR;
        } else {
            return HandType.HIGH_CARD;
        }
    }
}
