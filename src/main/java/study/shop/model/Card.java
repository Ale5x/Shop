package study.shop.model;

import java.util.Objects;

public class Card {

    private String idCard;
    private double percent;

    public Card() {
    }

    public Card(String idCard, double percent) {
        this.idCard = idCard;
        this.percent = percent;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
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
