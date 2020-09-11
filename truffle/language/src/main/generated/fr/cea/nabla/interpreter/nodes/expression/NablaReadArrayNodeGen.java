// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import com.oracle.truffle.api.nodes.NodeUtil;
import fr.cea.nabla.interpreter.nodes.expression.NablaExpressionNode;
import fr.cea.nabla.interpreter.nodes.expression.NablaReadArrayNode;
import fr.cea.nabla.interpreter.values.NV1Bool;
import fr.cea.nabla.interpreter.values.NV1IntLibrary;
import fr.cea.nabla.interpreter.values.NV1Real;
import fr.cea.nabla.interpreter.values.NV2Bool;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;
import fr.cea.nabla.interpreter.values.NV3Real;
import fr.cea.nabla.interpreter.values.NV4Real;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaReadArrayNode.class)
public final class NablaReadArrayNodeGen extends NablaReadArrayNode {

    private static final LibraryFactory<NV1IntLibrary> N_V1_INT_LIBRARY_ = LibraryFactory.resolve(NV1IntLibrary.class);

    @Child private NablaExpressionNode arrayNode_;
    @CompilationFinal private int state_;
    @CompilationFinal private int exclude_;
    @Child private ReadNV1Int1Index0Data readNV1Int1Index0_cache;

    private NablaReadArrayNodeGen(NablaExpressionNode[] indices, NablaExpressionNode arrayNode) {
        super(indices);
        this.arrayNode_ = arrayNode;
    }

    @ExplodeLoop
    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object arrayNodeValue_ = this.arrayNode_.executeGeneric(frameValue);
        if ((state & 0b11) != 0 /* is-active readNV1Int1Index(VirtualFrame, Object, NV1IntLibrary) || readNV1Int1Index(VirtualFrame, Object, NV1IntLibrary) */) {
            if ((state & 0b1) != 0 /* is-active readNV1Int1Index(VirtualFrame, Object, NV1IntLibrary) */) {
                ReadNV1Int1Index0Data s1_ = this.readNV1Int1Index0_cache;
                while (s1_ != null) {
                    if ((s1_.arrays_.accepts(arrayNodeValue_))) {
                        assert (indices.length == 1);
                        if ((s1_.arrays_.isArray(arrayNodeValue_))) {
                            return readNV1Int1Index(frameValue, arrayNodeValue_, s1_.arrays_);
                        }
                    }
                    s1_ = s1_.next_;
                }
            }
            if ((state & 0b10) != 0 /* is-active readNV1Int1Index(VirtualFrame, Object, NV1IntLibrary) */) {
                assert (indices.length == 1);
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if (((N_V1_INT_LIBRARY_.getUncached(arrayNodeValue_)).isArray(arrayNodeValue_))) {
                        return readNV1Int1Index(frameValue, arrayNodeValue_, (N_V1_INT_LIBRARY_.getUncached(arrayNodeValue_)));
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
        }
        if ((state & 0b1100) != 0 /* is-active readNV2Int1Index(VirtualFrame, NV2Int) || readNV2Int2Indices(VirtualFrame, NV2Int) */ && arrayNodeValue_ instanceof NV2Int) {
            NV2Int arrayNodeValue__ = (NV2Int) arrayNodeValue_;
            if ((state & 0b100) != 0 /* is-active readNV2Int1Index(VirtualFrame, NV2Int) */) {
                assert (indices.length == 1);
                return readNV2Int1Index(frameValue, arrayNodeValue__);
            }
            if ((state & 0b1000) != 0 /* is-active readNV2Int2Indices(VirtualFrame, NV2Int) */) {
                assert (indices.length == 2);
                return readNV2Int2Indices(frameValue, arrayNodeValue__);
            }
        }
        if ((state & 0b10000) != 0 /* is-active readNV1Real1Index(VirtualFrame, NV1Real) */ && arrayNodeValue_ instanceof NV1Real) {
            NV1Real arrayNodeValue__ = (NV1Real) arrayNodeValue_;
            assert (indices.length == 1);
            return readNV1Real1Index(frameValue, arrayNodeValue__);
        }
        if ((state & 0b1100000) != 0 /* is-active readNV2Real1Index(VirtualFrame, NV2Real) || readNV2Real2Indices(VirtualFrame, NV2Real) */ && arrayNodeValue_ instanceof NV2Real) {
            NV2Real arrayNodeValue__ = (NV2Real) arrayNodeValue_;
            if ((state & 0b100000) != 0 /* is-active readNV2Real1Index(VirtualFrame, NV2Real) */) {
                assert (indices.length == 1);
                return readNV2Real1Index(frameValue, arrayNodeValue__);
            }
            if ((state & 0b1000000) != 0 /* is-active readNV2Real2Indices(VirtualFrame, NV2Real) */) {
                assert (indices.length == 2);
                return readNV2Real2Indices(frameValue, arrayNodeValue__);
            }
        }
        if ((state & 0b1110000000) != 0 /* is-active readNV3Real1Index(VirtualFrame, NV3Real) || readNV3Real2Indices(VirtualFrame, NV3Real) || readNV3Real3Indices(VirtualFrame, NV3Real) */ && arrayNodeValue_ instanceof NV3Real) {
            NV3Real arrayNodeValue__ = (NV3Real) arrayNodeValue_;
            if ((state & 0b10000000) != 0 /* is-active readNV3Real1Index(VirtualFrame, NV3Real) */) {
                assert (indices.length == 1);
                return readNV3Real1Index(frameValue, arrayNodeValue__);
            }
            if ((state & 0b100000000) != 0 /* is-active readNV3Real2Indices(VirtualFrame, NV3Real) */) {
                assert (indices.length == 2);
                return readNV3Real2Indices(frameValue, arrayNodeValue__);
            }
            if ((state & 0b1000000000) != 0 /* is-active readNV3Real3Indices(VirtualFrame, NV3Real) */) {
                assert (indices.length == 3);
                return readNV3Real3Indices(frameValue, arrayNodeValue__);
            }
        }
        if ((state & 0b11110000000000) != 0 /* is-active readNV4Real1Index(VirtualFrame, NV4Real) || readNV4Real2Indices(VirtualFrame, NV4Real) || readNV4Real3Indices(VirtualFrame, NV4Real) || readNV4Real4Indices(VirtualFrame, NV4Real) */ && arrayNodeValue_ instanceof NV4Real) {
            NV4Real arrayNodeValue__ = (NV4Real) arrayNodeValue_;
            if ((state & 0b10000000000) != 0 /* is-active readNV4Real1Index(VirtualFrame, NV4Real) */) {
                assert (indices.length == 1);
                return readNV4Real1Index(frameValue, arrayNodeValue__);
            }
            if ((state & 0b100000000000) != 0 /* is-active readNV4Real2Indices(VirtualFrame, NV4Real) */) {
                assert (indices.length == 2);
                return readNV4Real2Indices(frameValue, arrayNodeValue__);
            }
            if ((state & 0b1000000000000) != 0 /* is-active readNV4Real3Indices(VirtualFrame, NV4Real) */) {
                assert (indices.length == 3);
                return readNV4Real3Indices(frameValue, arrayNodeValue__);
            }
            if ((state & 0b10000000000000) != 0 /* is-active readNV4Real4Indices(VirtualFrame, NV4Real) */) {
                assert (indices.length == 4);
                return readNV4Real4Indices(frameValue, arrayNodeValue__);
            }
        }
        if ((state & 0b100000000000000) != 0 /* is-active readNV1Bool1Index(VirtualFrame, NV1Bool) */ && arrayNodeValue_ instanceof NV1Bool) {
            NV1Bool arrayNodeValue__ = (NV1Bool) arrayNodeValue_;
            assert (indices.length == 1);
            return readNV1Bool1Index(frameValue, arrayNodeValue__);
        }
        if ((state & 0x18000) != 0 /* is-active readNV2Bool1Index(VirtualFrame, NV2Bool) || readNV2Bool2Indices(VirtualFrame, NV2Bool) */ && arrayNodeValue_ instanceof NV2Bool) {
            NV2Bool arrayNodeValue__ = (NV2Bool) arrayNodeValue_;
            if ((state & 0b1000000000000000) != 0 /* is-active readNV2Bool1Index(VirtualFrame, NV2Bool) */) {
                assert (indices.length == 1);
                return readNV2Bool1Index(frameValue, arrayNodeValue__);
            }
            if ((state & 0x10000) != 0 /* is-active readNV2Bool2Indices(VirtualFrame, NV2Bool) */) {
                assert (indices.length == 2);
                return readNV2Bool2Indices(frameValue, arrayNodeValue__);
            }
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(frameValue, arrayNodeValue_);
    }

    private Object executeAndSpecialize(VirtualFrame frameValue, Object arrayNodeValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state = state_;
        int exclude = exclude_;
        try {
            if ((exclude) == 0 /* is-not-excluded readNV1Int1Index(VirtualFrame, Object, NV1IntLibrary) */) {
                int count1_ = 0;
                ReadNV1Int1Index0Data s1_ = this.readNV1Int1Index0_cache;
                if ((state & 0b1) != 0 /* is-active readNV1Int1Index(VirtualFrame, Object, NV1IntLibrary) */) {
                    while (s1_ != null) {
                        if ((s1_.arrays_.accepts(arrayNodeValue))) {
                            assert (indices.length == 1);
                            if ((s1_.arrays_.isArray(arrayNodeValue))) {
                                break;
                            }
                        }
                        s1_ = s1_.next_;
                        count1_++;
                    }
                }
                if (s1_ == null) {
                    if ((indices.length == 1)) {
                        // assert (s1_.arrays_.accepts(arrayNodeValue));
                        NV1IntLibrary arrays__ = super.insert((N_V1_INT_LIBRARY_.create(arrayNodeValue)));
                        if ((arrays__.isArray(arrayNodeValue)) && count1_ < (3)) {
                            s1_ = super.insert(new ReadNV1Int1Index0Data(readNV1Int1Index0_cache));
                            s1_.arrays_ = s1_.insertAccessor(arrays__);
                            this.readNV1Int1Index0_cache = s1_;
                            this.state_ = state = state | 0b1 /* add-active readNV1Int1Index(VirtualFrame, Object, NV1IntLibrary) */;
                        }
                    }
                }
                if (s1_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return readNV1Int1Index(frameValue, arrayNodeValue, s1_.arrays_);
                }
            }
            {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if ((indices.length == 1)) {
                        NV1IntLibrary readNV1Int1Index1_arrays__ = (N_V1_INT_LIBRARY_.getUncached(arrayNodeValue));
                        if ((readNV1Int1Index1_arrays__.isArray(arrayNodeValue))) {
                            this.exclude_ = exclude = exclude | 0b1 /* add-excluded readNV1Int1Index(VirtualFrame, Object, NV1IntLibrary) */;
                            this.readNV1Int1Index0_cache = null;
                            state = state & 0xfffffffe /* remove-active readNV1Int1Index(VirtualFrame, Object, NV1IntLibrary) */;
                            this.state_ = state = state | 0b10 /* add-active readNV1Int1Index(VirtualFrame, Object, NV1IntLibrary) */;
                            lock.unlock();
                            hasLock = false;
                            return readNV1Int1Index(frameValue, arrayNodeValue, readNV1Int1Index1_arrays__);
                        }
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
            if (arrayNodeValue instanceof NV2Int) {
                NV2Int arrayNodeValue_ = (NV2Int) arrayNodeValue;
                if ((indices.length == 1)) {
                    this.state_ = state = state | 0b100 /* add-active readNV2Int1Index(VirtualFrame, NV2Int) */;
                    lock.unlock();
                    hasLock = false;
                    return readNV2Int1Index(frameValue, arrayNodeValue_);
                }
                if ((indices.length == 2)) {
                    this.state_ = state = state | 0b1000 /* add-active readNV2Int2Indices(VirtualFrame, NV2Int) */;
                    lock.unlock();
                    hasLock = false;
                    return readNV2Int2Indices(frameValue, arrayNodeValue_);
                }
            }
            if (arrayNodeValue instanceof NV1Real) {
                NV1Real arrayNodeValue_ = (NV1Real) arrayNodeValue;
                if ((indices.length == 1)) {
                    this.state_ = state = state | 0b10000 /* add-active readNV1Real1Index(VirtualFrame, NV1Real) */;
                    lock.unlock();
                    hasLock = false;
                    return readNV1Real1Index(frameValue, arrayNodeValue_);
                }
            }
            if (arrayNodeValue instanceof NV2Real) {
                NV2Real arrayNodeValue_ = (NV2Real) arrayNodeValue;
                if ((indices.length == 1)) {
                    this.state_ = state = state | 0b100000 /* add-active readNV2Real1Index(VirtualFrame, NV2Real) */;
                    lock.unlock();
                    hasLock = false;
                    return readNV2Real1Index(frameValue, arrayNodeValue_);
                }
                if ((indices.length == 2)) {
                    this.state_ = state = state | 0b1000000 /* add-active readNV2Real2Indices(VirtualFrame, NV2Real) */;
                    lock.unlock();
                    hasLock = false;
                    return readNV2Real2Indices(frameValue, arrayNodeValue_);
                }
            }
            if (arrayNodeValue instanceof NV3Real) {
                NV3Real arrayNodeValue_ = (NV3Real) arrayNodeValue;
                if ((indices.length == 1)) {
                    this.state_ = state = state | 0b10000000 /* add-active readNV3Real1Index(VirtualFrame, NV3Real) */;
                    lock.unlock();
                    hasLock = false;
                    return readNV3Real1Index(frameValue, arrayNodeValue_);
                }
                if ((indices.length == 2)) {
                    this.state_ = state = state | 0b100000000 /* add-active readNV3Real2Indices(VirtualFrame, NV3Real) */;
                    lock.unlock();
                    hasLock = false;
                    return readNV3Real2Indices(frameValue, arrayNodeValue_);
                }
                if ((indices.length == 3)) {
                    this.state_ = state = state | 0b1000000000 /* add-active readNV3Real3Indices(VirtualFrame, NV3Real) */;
                    lock.unlock();
                    hasLock = false;
                    return readNV3Real3Indices(frameValue, arrayNodeValue_);
                }
            }
            if (arrayNodeValue instanceof NV4Real) {
                NV4Real arrayNodeValue_ = (NV4Real) arrayNodeValue;
                if ((indices.length == 1)) {
                    this.state_ = state = state | 0b10000000000 /* add-active readNV4Real1Index(VirtualFrame, NV4Real) */;
                    lock.unlock();
                    hasLock = false;
                    return readNV4Real1Index(frameValue, arrayNodeValue_);
                }
                if ((indices.length == 2)) {
                    this.state_ = state = state | 0b100000000000 /* add-active readNV4Real2Indices(VirtualFrame, NV4Real) */;
                    lock.unlock();
                    hasLock = false;
                    return readNV4Real2Indices(frameValue, arrayNodeValue_);
                }
                if ((indices.length == 3)) {
                    this.state_ = state = state | 0b1000000000000 /* add-active readNV4Real3Indices(VirtualFrame, NV4Real) */;
                    lock.unlock();
                    hasLock = false;
                    return readNV4Real3Indices(frameValue, arrayNodeValue_);
                }
                if ((indices.length == 4)) {
                    this.state_ = state = state | 0b10000000000000 /* add-active readNV4Real4Indices(VirtualFrame, NV4Real) */;
                    lock.unlock();
                    hasLock = false;
                    return readNV4Real4Indices(frameValue, arrayNodeValue_);
                }
            }
            if (arrayNodeValue instanceof NV1Bool) {
                NV1Bool arrayNodeValue_ = (NV1Bool) arrayNodeValue;
                if ((indices.length == 1)) {
                    this.state_ = state = state | 0b100000000000000 /* add-active readNV1Bool1Index(VirtualFrame, NV1Bool) */;
                    lock.unlock();
                    hasLock = false;
                    return readNV1Bool1Index(frameValue, arrayNodeValue_);
                }
            }
            if (arrayNodeValue instanceof NV2Bool) {
                NV2Bool arrayNodeValue_ = (NV2Bool) arrayNodeValue;
                if ((indices.length == 1)) {
                    this.state_ = state = state | 0b1000000000000000 /* add-active readNV2Bool1Index(VirtualFrame, NV2Bool) */;
                    lock.unlock();
                    hasLock = false;
                    return readNV2Bool1Index(frameValue, arrayNodeValue_);
                }
                if ((indices.length == 2)) {
                    this.state_ = state = state | 0x10000 /* add-active readNV2Bool2Indices(VirtualFrame, NV2Bool) */;
                    lock.unlock();
                    hasLock = false;
                    return readNV2Bool2Indices(frameValue, arrayNodeValue_);
                }
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.arrayNode_}, arrayNodeValue);
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
            ReadNV1Int1Index0Data s1_ = this.readNV1Int1Index0_cache;
            if ((s1_ == null || s1_.next_ == null)) {
                return NodeCost.MONOMORPHIC;
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static NablaReadArrayNode create(NablaExpressionNode[] indices, NablaExpressionNode arrayNode) {
        return new NablaReadArrayNodeGen(indices, arrayNode);
    }

    @GeneratedBy(NablaReadArrayNode.class)
    private static final class ReadNV1Int1Index0Data extends Node {

        @Child ReadNV1Int1Index0Data next_;
        @Child NV1IntLibrary arrays_;

        ReadNV1Int1Index0Data(ReadNV1Int1Index0Data next_) {
            this.next_ = next_;
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

        <T extends Node> T insertAccessor(T node) {
            return super.insert(node);
        }

    }
}
