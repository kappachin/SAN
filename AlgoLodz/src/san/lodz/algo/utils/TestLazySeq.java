package san.lodz.algo.utils;


import static san.lodz.algo.utils.LazySeq.iterate;
import static san.lodz.algo.utils.LazySeq.take;

public class TestLazySeq {

    public static void main(String... args) {
        Seq<Integer> N = iterate(n -> n + 1, 0);
        System.out.println(take(100, N).asString());

//    System.out.println(N.first());
//    System.out.println(N.rest().first());
//    System.out.println(N.rest().rest().first());
//    System.out.println(N.rest().rest().rest().first());

        Seq<String> napisy = LinkedSeq.make("a", "b", "c", "d", "a");
        Seq<Integer> result = napisy.allIndexesOf(s -> "a".equals(s));  // => (0, 4)

        Seq<String> napisy_2 = LinkedSeq.make("aaa", "bb", "cccc", "ddd");
        Seq<String> result_2 = napisy.findAll(s -> s.length() == 3); // => (“aaa”, “ddd”)


        Seq<String> result3 = napisy_2.fmap(e -> e.toString());
        Seq<Integer> result4 = napisy_2.fmap(e -> {return e.length();});

        //System.out.println(napisy.asString());
        //System.out.println(result.asString());
        //System.out.println(napisy_2.asString());
        //System.out.println(result_2.asString());
        //System.out.println(result3.asString());
        //System.out.println(result4.asString());

        Pair p = new Pair(0,1);
        Seq<Pair> Fib = iterate(e -> (new Pair(e.b, e.a + e.b)), p);
        System.out.println(take(2, Fib).fmap(e->e.a).asString());
//    System.out.println(N.first());
//    System.out.println(N.rest().first());
//    System.out.println(N.rest().rest().first());
//    System.out.println(N.rest().rest().rest().first());
    }

}
