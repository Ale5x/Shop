package study.shop.model;

import java.util.Objects;

public class Card {

//    private final static String FILE_CARD_PATH = "Data shop/Discount Cards/List discount cards.txt";
//    private static List<Integer> listCard = new ArrayList<>();

    private long idCard;
    private double percent;

    public Card() {
    }

    public Card(long idCard, double percent) {
        this.idCard = idCard;
        this.percent = percent;
    }

    public long getIdCard() {
        return idCard;
    }

    public void setIdCard(long idCard) {
        this.idCard = idCard;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Card card = (Card) obj;
        return idCard == card.idCard &&
                Double.compare(card.percent, percent) == 0;
    }

    @Override
    public String toString() {
        return "Card{" +
                "idCard=" + idCard +
                ", percent=" + percent +
                '}';
    }
}
