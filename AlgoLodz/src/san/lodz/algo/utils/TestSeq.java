package san.lodz.algo.utils;

public class TestSeq {

    public static void main(String... args) {
        Seq<String> napisy = LinkedSeq.make("a", "b", "c", "d", "a");

//    Seq<Integer> result = napisy.allIndexesOf(s -> "a".equals(s));

        System.out.println(napisy.asString());
//    empty
//    System.out.println(Nil.get().asString());
//print index of "b"
        System.out.println(napisy.indexOf(s -> "a".equals(s)));
//    System.out.println(napisy.findFirst(s -> s.length() == 2));

//    System.out.println(LinkedSeq.make(1, 2, 3, 4, 5).
//        fmap(n -> n * n).asString());
        System.out.println(LinkedSeq.make(1, 2, 3, 4, 5).
                fmap(n -> n%2 == 0).asString());
    }

}
