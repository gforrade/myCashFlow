package ar.com.gaf.mycashflow.dao.filters.commons;

public class OrderExpression<T extends Enum<?>> {
    T element;
    OrderDirection direction;

    public OrderExpression(T element, OrderDirection direction) {
        this.element = element;
        this.direction = direction;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public OrderDirection getDirection() {
        return direction;
    }

    public void setDirection(OrderDirection direction) {
        this.direction = direction;
    }
}
