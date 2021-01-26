// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression.constant;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV1Int;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaInt1ConstantNode.class)
public final class NablaInt1ConstantNodeGen extends NablaInt1ConstantNode {

    @Child private NablaExpressionNode value_;
    @Child private NablaExpressionNode size_;
    @CompilationFinal private volatile int state_0_;
    @CompilationFinal private DefaultCachedData defaultCached_cache;
    @CompilationFinal private CachedData cached_cache;

    private NablaInt1ConstantNodeGen(NablaExpressionNode value, NablaExpressionNode size) {
        this.value_ = value;
        this.size_ = size;
    }

    @ExplodeLoop
    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state_0 = state_0_;
        Object valueValue_ = this.value_.executeGeneric(frameValue);
        Object sizeValue_ = this.size_.executeGeneric(frameValue);
        if (state_0 != 0 /* is-state_0 doDefaultCached(VirtualFrame, NV0Int, NV0Int, int, NV1Int) || doCached(VirtualFrame, NV0Int, NV0Int, int, int, NV1Int) */ && valueValue_ instanceof NV0Int) {
            NV0Int valueValue__ = (NV0Int) valueValue_;
            if (sizeValue_ instanceof NV0Int) {
                NV0Int sizeValue__ = (NV0Int) sizeValue_;
                if ((state_0 & 0b1) != 0 /* is-state_0 doDefaultCached(VirtualFrame, NV0Int, NV0Int, int, NV1Int) */) {
                    DefaultCachedData s1_ = this.defaultCached_cache;
                    while (s1_ != null) {
                        if ((s1_.cachedSize_ == sizeValue__.getData()) && (isZero(valueValue__.getData()))) {
                            return doDefaultCached(frameValue, valueValue__, sizeValue__, s1_.cachedSize_, s1_.result_);
                        }
                        s1_ = s1_.next_;
                    }
                }
                if ((state_0 & 0b10) != 0 /* is-state_0 doCached(VirtualFrame, NV0Int, NV0Int, int, int, NV1Int) */) {
                    CachedData s2_ = this.cached_cache;
                    while (s2_ != null) {
                        if ((s2_.cachedSize_ == sizeValue__.getData())) {
                            return doCached(frameValue, valueValue__, sizeValue__, s2_.cachedValue_, s2_.cachedSize_, s2_.result_);
                        }
                        s2_ = s2_.next_;
                    }
                }
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue, valueValue_, sizeValue_);
    }

    private NV1Int executeAndSpecialize(VirtualFrame frameValue, Object valueValue, Object sizeValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state_0 = state_0_;
        try {
            if (valueValue instanceof NV0Int) {
                NV0Int valueValue_ = (NV0Int) valueValue;
                if (sizeValue instanceof NV0Int) {
                    NV0Int sizeValue_ = (NV0Int) sizeValue;
                    int count1_ = 0;
                    DefaultCachedData s1_ = this.defaultCached_cache;
                    if ((state_0 & 0b1) != 0 /* is-state_0 doDefaultCached(VirtualFrame, NV0Int, NV0Int, int, NV1Int) */) {
                        while (s1_ != null) {
                            if ((s1_.cachedSize_ == sizeValue_.getData()) && (isZero(valueValue_.getData()))) {
                                break;
                            }
                            s1_ = s1_.next_;
                            count1_++;
                        }
                    }
                    if (s1_ == null) {
                        {
                            int cachedSize__ = (sizeValue_.getData());
                            if ((cachedSize__ == sizeValue_.getData()) && (isZero(valueValue_.getData())) && count1_ < (3)) {
                                s1_ = new DefaultCachedData(defaultCached_cache);
                                s1_.cachedSize_ = cachedSize__;
                                s1_.result_ = (getDefaultResult(cachedSize__));
                                this.defaultCached_cache = s1_;
                                this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 doDefaultCached(VirtualFrame, NV0Int, NV0Int, int, NV1Int) */;
                            }
                        }
                    }
                    if (s1_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return doDefaultCached(frameValue, valueValue_, sizeValue_, s1_.cachedSize_, s1_.result_);
                    }
                    int count2_ = 0;
                    CachedData s2_ = this.cached_cache;
                    if ((state_0 & 0b10) != 0 /* is-state_0 doCached(VirtualFrame, NV0Int, NV0Int, int, int, NV1Int) */) {
                        while (s2_ != null) {
                            if ((s2_.cachedSize_ == sizeValue_.getData())) {
                                break;
                            }
                            s2_ = s2_.next_;
                            count2_++;
                        }
                    }
                    if (s2_ == null) {
                        {
                            int cachedSize__1 = (sizeValue_.getData());
                            if ((cachedSize__1 == sizeValue_.getData()) && count2_ < (3)) {
                                s2_ = new CachedData(cached_cache);
                                s2_.cachedValue_ = (valueValue_.getData());
                                s2_.cachedSize_ = cachedSize__1;
                                s2_.result_ = (getResult(s2_.cachedValue_, cachedSize__1));
                                this.cached_cache = s2_;
                                this.state_0_ = state_0 = state_0 | 0b10 /* add-state_0 doCached(VirtualFrame, NV0Int, NV0Int, int, int, NV1Int) */;
                            }
                        }
                    }
                    if (s2_ != null) {
                        lock.unlock();
                        hasLock = false;
                        return doCached(frameValue, valueValue_, sizeValue_, s2_.cachedValue_, s2_.cachedSize_, s2_.result_);
                    }
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.value_, this.size_}, valueValue, sizeValue);
        } finally {
            if (hasLock) {
                lock.unlock();
            }
        }
    }

    @Override
    public NodeCost getCost() {
        int state_0 = state_0_;
        if (state_0 == 0) {
            return NodeCost.UNINITIALIZED;
        } else {
            if ((state_0 & (state_0 - 1)) == 0 /* is-single-state_0  */) {
                DefaultCachedData s1_ = this.defaultCached_cache;
                CachedData s2_ = this.cached_cache;
                if ((s1_ == null || s1_.next_ == null) && (s2_ == null || s2_.next_ == null)) {
                    return NodeCost.MONOMORPHIC;
                }
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static NablaInt1ConstantNode create(NablaExpressionNode value, NablaExpressionNode size) {
        return new NablaInt1ConstantNodeGen(value, size);
    }

    @GeneratedBy(NablaInt1ConstantNode.class)
    private static final class DefaultCachedData {

        @CompilationFinal DefaultCachedData next_;
        @CompilationFinal int cachedSize_;
        @CompilationFinal NV1Int result_;

        DefaultCachedData(DefaultCachedData next_) {
            this.next_ = next_;
        }

    }
    @GeneratedBy(NablaInt1ConstantNode.class)
    private static final class CachedData {

        @CompilationFinal CachedData next_;
        @CompilationFinal int cachedValue_;
        @CompilationFinal int cachedSize_;
        @CompilationFinal NV1Int result_;

        CachedData(CachedData next_) {
            this.next_ = next_;
        }

    }
}
