package san.lodz.algo.utils;

import san.lodz.math.Binary;
import san.lodz.math.Unary;

public interface Seq<T> {

    T first();

    Seq<T> rest();

    boolean isEmpty();

    Seq<T> cons(T e);

    default String asString() {
        StringBuilder buf = new StringBuilder("(");
        this.forEach((e, isLast) -> {
            buf.append(e);
            if (!isLast) {
                buf.append(",");
            }
            return null;
        });
        return buf.append(")").toString();
    }

    default Seq<Integer> allIndexesOf(Unary<T, Boolean> pred) {
        Seq<Integer> result = Nil.get();
        Seq<T> s = this;
        int i = size() - 1;

        while (!s.isEmpty()) {
            T e = s.first();
            if (pred.call(e))
                result = result.cons(i);
            s = s.rest();
            i -= 1;
        }
        return result;
    }

    default void forEach(Binary<T, Boolean, Void> body) {
        Seq<T> s = this;
        while (!s.isEmpty()) {
            T e = s.first();
            s = s.rest();
            body.call(e, s.isEmpty());
        }
    }

    default <S> Seq<S> fmap(Unary<T, S> f) {
        return LazySeq.fmap(f, this);
    }



    default Seq<T> findAll(Unary<T, Boolean> pred) {
        Seq<T> result = Nil.get();
        Seq<T> s = this;

        while (!s.isEmpty()) {
            T e = s.first();
            if (pred.call(e))
                result = result.cons(e);
            s = s.rest();
        }
        return result;
    }

    default int size() {
        Seq<T> s = this;
        int i = 0;

        while (!s.isEmpty()) {
            s = s.rest();
            i += 1;
        }
        return i;
    }

    default long indexOf(Unary<T, Boolean> pred) {
        Seq<T> s = this;
        long i = 0;
        while (!s.isEmpty()) {
            T e = s.first();
            if (pred.call(e))
                return i;
            s = s.rest();
            i += 1;
        }
        return -1;
    }

}
