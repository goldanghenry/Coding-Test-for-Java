package Basic;

class Box<T> {
    private T item;
    public void setItem(T item) {
        this.item = item;
    }
    public T getItem() {
        return item;
    }
}

public class GenericTest {
    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();
        stringBox.setItem("test");
        System.out.println(stringBox.getItem());

        Box<Integer> integerBox = new Box<>();
        integerBox.setItem(1);
        System.out.println(integerBox.getItem());
    }
}
