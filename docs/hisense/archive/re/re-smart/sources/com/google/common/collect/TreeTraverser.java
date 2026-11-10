package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtCompatible
@Deprecated
public abstract class TreeTraverser<T> {

    private final class BreadthFirstIterator extends UnmodifiableIterator<T> implements PeekingIterator<T> {
        public final Queue<T> queue = new ArrayDeque();

        public BreadthFirstIterator(T t) {
            this.queue.add(t);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        @Override // java.util.Iterator, com.google.common.collect.PeekingIterator
        public T next() {
            T tRemove = this.queue.remove();
            Iterables.addAll(this.queue, TreeTraverser.this.children(tRemove));
            return tRemove;
        }

        @Override // com.google.common.collect.PeekingIterator
        public T peek() {
            return this.queue.element();
        }
    }

    private final class PostOrderIterator extends AbstractIterator<T> {
        public final ArrayDeque<PostOrderNode<T>> stack = new ArrayDeque<>();

        public PostOrderIterator(T t) {
            this.stack.addLast(expand(t));
        }

        private PostOrderNode<T> expand(T t) {
            return new PostOrderNode<>(t, TreeTraverser.this.children(t).iterator());
        }

        @Override // com.google.common.collect.AbstractIterator
        public T computeNext() {
            while (!this.stack.isEmpty()) {
                PostOrderNode<T> last = this.stack.getLast();
                if (!last.childIterator.hasNext()) {
                    this.stack.removeLast();
                    return last.root;
                }
                this.stack.addLast(expand(last.childIterator.next()));
            }
            return endOfData();
        }
    }

    private static final class PostOrderNode<T> {
        public final Iterator<T> childIterator;
        public final T root;

        public PostOrderNode(T t, Iterator<T> it) {
            if (t == null) {
                throw new NullPointerException();
            }
            this.root = t;
            if (it == null) {
                throw new NullPointerException();
            }
            this.childIterator = it;
        }
    }

    private final class PreOrderIterator extends UnmodifiableIterator<T> {
        public final Deque<Iterator<T>> stack = new ArrayDeque();

        public PreOrderIterator(T t) {
            Deque<Iterator<T>> deque = this.stack;
            if (t == null) {
                throw new NullPointerException();
            }
            deque.addLast(new Iterators.AnonymousClass9(t));
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        @Override // java.util.Iterator
        public T next() {
            Iterator<T> last = this.stack.getLast();
            T next = last.next();
            Preconditions.checkNotNull(next);
            if (!last.hasNext()) {
                this.stack.removeLast();
            }
            Iterator<T> it = TreeTraverser.this.children(next).iterator();
            if (it.hasNext()) {
                this.stack.addLast(it);
            }
            return next;
        }
    }

    @Deprecated
    public static <T> TreeTraverser<T> using(final Function<T, ? extends Iterable<T>> function) {
        if (function != null) {
            return new TreeTraverser<T>() { // from class: com.google.common.collect.TreeTraverser.1
                @Override // com.google.common.collect.TreeTraverser
                public Iterable<T> children(T t) {
                    return (Iterable) function.apply(t);
                }
            };
        }
        throw new NullPointerException();
    }

    @Deprecated
    public final FluentIterable<T> breadthFirstTraversal(final T t) {
        if (t != null) {
            return new FluentIterable<T>() { // from class: com.google.common.collect.TreeTraverser.4
                @Override // java.lang.Iterable
                public UnmodifiableIterator<T> iterator() {
                    return new BreadthFirstIterator(t);
                }
            };
        }
        throw new NullPointerException();
    }

    public abstract Iterable<T> children(T t);

    public UnmodifiableIterator<T> postOrderIterator(T t) {
        return new PostOrderIterator(t);
    }

    @Deprecated
    public final FluentIterable<T> postOrderTraversal(final T t) {
        if (t != null) {
            return new FluentIterable<T>() { // from class: com.google.common.collect.TreeTraverser.3
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Iterable
                public UnmodifiableIterator<T> iterator() {
                    return TreeTraverser.this.postOrderIterator(t);
                }
            };
        }
        throw new NullPointerException();
    }

    public UnmodifiableIterator<T> preOrderIterator(T t) {
        return new PreOrderIterator(t);
    }

    @Deprecated
    public final FluentIterable<T> preOrderTraversal(final T t) {
        if (t != null) {
            return new FluentIterable<T>() { // from class: com.google.common.collect.TreeTraverser.2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Iterable
                public UnmodifiableIterator<T> iterator() {
                    return TreeTraverser.this.preOrderIterator(t);
                }
            };
        }
        throw new NullPointerException();
    }
}
