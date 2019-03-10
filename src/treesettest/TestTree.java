package treesettest;

import java.util.TreeSet;

public class TestTree{
    public static void main(String[] args) {
        new TestTree().go();
    }

    private void go() {
        Book b1 =new Book( "двадцать тысяч лье под водой");
        Book b2 =new Book( "Дети капитана Гранта");
        Book b3 =new Book( "Странная история доктора Джекила и мистера Хайда");


        TreeSet <Book> tree = new TreeSet<>();
        tree.add(b1);
        tree.add(b2);
        tree.add(b3);
        System.out.println(tree);
    }

}
class Book  implements Comparable {
private String title;

    public Book(String title) {
        this.title = title;
    }

    @Override
    public int compareTo(Object o) {
        Book book = (Book)o;
        return (title.compareTo(book.title));
    }
}
