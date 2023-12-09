package main.java.day7;

import main.java.day7.domain.Hand;
import main.java.day7.domain.HandType;
import main.java.domain.Input;

import java.util.*;
import java.util.stream.IntStream;

public class Phase1 {
    List<Hand> hands = new ArrayList<>();

    List<Character> cardOrder = List.of(
            'A',
            'K',
            'Q',
            'J',
            'T',
            '9',
            '8',
            '7',
            '6',
            '5',
            '4',
            '3',
            '2'
    );

    public long calculateWinnings() {
        hands.sort(Collections.reverseOrder());
        return IntStream.range(0, hands.size())
                .mapToObj(i -> hands.get(i).getBid() * (i + 1))
                .reduce(0, Integer::sum);
    }

    protected long parseInput(Input input) {
        String[] parts = input.getCurrentLine().split(" ");

        hands.add(new Hand(parts[0], Integer.parseInt(parts[1]), cardOrder, evaluateType(parts[0])));

        return 0;
    }

    private HandType evaluateType(String cards) {
        Map<Character, Integer> hand = new HashMap<>();
        for (int x = 0; x < cards.length(); x++) {
            Character character = cards.charAt(x);
            if (hand.containsKey(character)) {
                hand.put(character, hand.get(character) + 1);
            } else {
                hand.put(character, 1);
            }
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
