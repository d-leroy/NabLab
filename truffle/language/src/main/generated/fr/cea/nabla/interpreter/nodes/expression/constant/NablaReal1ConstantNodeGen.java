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
import fr.cea.nabla.interpreter.nodes.expression.constant.NablaReal1ConstantNode;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1Real;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaReal1ConstantNode.class)
public final class NablaReal1ConstantNodeGen extends NablaReal1ConstantNode {

    @Child private NablaExpressionNode value_;
    @Child private NablaExpressionNode size_;
    @CompilationFinal private int state_;
    @CompilationFinal private DefaultCachedData defaultCached_cache;
    @CompilationFinal private CachedData cached_cache;

    private NablaReal1ConstantNodeGen(NablaExpressionNode value, NablaExpressionNode size) {
        this.value_ = value;
        this.size_ = size;
    }

    @ExplodeLoop
    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object valueValue_ = this.value_.executeGeneric(frameValue);
        Object sizeValue_ = this.size_.executeGeneric(frameValue);
        if (state != 0 /* is-active doDefaultCached(VirtualFrame, NV0Real, NV0Int, int, NV1Real) || doCached(VirtualFrame, NV0Real, NV0Int, double, int, NV1Real) */ && valueValue_ instanceof NV0Real) {
            NV0Real valueValue__ = (NV0Real) valueValue_;
            if (sizeValue_ instanceof NV0Int) {
                NV0Int sizeValue__ = (NV0Int) sizeValue_;
                if ((state & 0b1) != 0 /* is-active doDefaultCached(VirtualFrame, NV0Real, NV0Int, int, NV1Real) */) {
                    DefaultCachedData s1_ = this.defaultCached_cache;
                    while (s1_ != null) {
                        if ((s1_.cachedSize_ == sizeValue__.getData()) && (isZero(valueValue__.getData()))) {
                            return doDefaultCached(frameValue, valueValue__, sizeValue__, s1_.cachedSize_, s1_.result_);
                        }
                        s1_ = s1_.next_;
                    }
                }
                if ((state & 0b10) != 0 /* is-active doCached(VirtualFrame, NV0Real, NV0Int, double, int, NV1Real) */) {
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

    private NV1Real executeAndSpecialize(VirtualFrame frameValue, Object valueValue, Object sizeValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state = state_;
        try {
            if (valueValue instanceof NV0Real) {
                NV0Real valueValue_ = (NV0Real) valueValue;
                if (sizeValue instanceof NV0Int) {
                    NV0Int sizeValue_ = (NV0Int) sizeValue;
                    int count1_ = 0;
                    DefaultCachedData s1_ = this.defaultCached_cache;
                    if ((state & 0b1) != 0 /* is-active doDefaultCached(VirtualFrame, NV0Real, NV0Int, int, NV1Real) */) {
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
                                this.state_ = state = state | 0b1 /* add-active doDefaultCached(VirtualFrame, NV0Real, NV0Int, int, NV1Real) */;
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
                    if ((state & 0b10) != 0 /* is-active doCached(VirtualFrame, NV0Real, NV0Int, double, int, NV1Real) */) {
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
                            int cachedSize__ = (sizeValue_.getData());
                            if ((cachedSize__ == sizeValue_.getData()) && count2_ < (3)) {
                                s2_ = new CachedData(cached_cache);
                                s2_.cachedValue_ = (valueValue_.getData());
                                s2_.cachedSize_ = cachedSize__;
                                s2_.result_ = (getResult(s2_.cachedValue_, cachedSize__));
                                this.cached_cache = s2_;
                                this.state_ = state = state | 0b10 /* add-active doCached(VirtualFrame, NV0Real, NV0Int, double, int, NV1Real) */;
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
        int state = state_;
        if (state == 0b0) {
            return NodeCost.UNINITIALIZED;
        } else if ((state & (state - 1)) == 0 /* is-single-active  */) {
            DefaultCachedData s1_ = this.defaultCached_cache;
            CachedData s2_ = this.cached_cache;
            if ((s1_ == null || s1_.next_ == null) && (s2_ == null || s2_.next_ == null)) {
                return NodeCost.MONOMORPHIC;
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static NablaReal1ConstantNode create(NablaExpressionNode value, NablaExpressionNode size) {
        return new NablaReal1ConstantNodeGen(value, size);
    }

    @GeneratedBy(NablaReal1ConstantNode.class)
    private static final class DefaultCachedData {

        @CompilationFinal DefaultCachedData next_;
        @CompilationFinal int cachedSize_;
        @CompilationFinal NV1Real result_;

        DefaultCachedData(DefaultCachedData next_) {
            this.next_ = next_;
        }

    }
    @GeneratedBy(NablaReal1ConstantNode.class)
    private static final class CachedData {

        @CompilationFinal CachedData next_;
        @CompilationFinal double cachedValue_;
        @CompilationFinal int cachedSize_;
        @CompilationFinal NV1Real result_;

        CachedData(CachedData next_) {
            this.next_ = next_;
        }

    }
}
