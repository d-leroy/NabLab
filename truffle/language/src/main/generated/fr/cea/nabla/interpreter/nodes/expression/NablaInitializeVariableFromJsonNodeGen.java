// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression;

import com.google.gson.JsonElement;
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
import fr.cea.nabla.interpreter.nodes.expression.NablaInitializeVariableFromJsonNode;
import fr.cea.nabla.interpreter.values.NV0Bool;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1Bool;
import fr.cea.nabla.interpreter.values.NV1IntLibrary;
import fr.cea.nabla.interpreter.values.NV1Real;
import fr.cea.nabla.interpreter.values.NV2Bool;
import fr.cea.nabla.interpreter.values.NV2Int;
import fr.cea.nabla.interpreter.values.NV2Real;
import fr.cea.nabla.interpreter.values.NV3Bool;
import fr.cea.nabla.interpreter.values.NV3Int;
import fr.cea.nabla.interpreter.values.NV3Real;
import fr.cea.nabla.interpreter.values.NV4Bool;
import fr.cea.nabla.interpreter.values.NV4Int;
import fr.cea.nabla.interpreter.values.NV4Real;
import java.util.concurrent.locks.Lock;

@GeneratedBy(NablaInitializeVariableFromJsonNode.class)
public final class NablaInitializeVariableFromJsonNodeGen extends NablaInitializeVariableFromJsonNode {

    private static final LibraryFactory<NV1IntLibrary> N_V1_INT_LIBRARY_ = LibraryFactory.resolve(NV1IntLibrary.class);

    @Child private NablaExpressionNode value_;
    @CompilationFinal private int state_;
    @CompilationFinal private int exclude_;
    @Child private Initialize6Data initialize6_cache;

    private NablaInitializeVariableFromJsonNodeGen(JsonElement initialValue, NablaExpressionNode value) {
        super(initialValue);
        this.value_ = value;
    }

    private NablaInitializeVariableFromJsonNodeGen(NablaExpressionNode value) {
        this.value_ = value;
    }

    @ExplodeLoop
    @Override
    public Object executeGeneric(VirtualFrame frameValue) {
        int state = state_;
        Object valueValue_ = this.value_.executeGeneric(frameValue);
        if ((state & 0b1) != 0 /* is-active doInitialize(NV0Bool) */ && valueValue_ instanceof NV0Bool) {
            NV0Bool valueValue__ = (NV0Bool) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b10) != 0 /* is-active doInitialize(NV1Bool) */ && valueValue_ instanceof NV1Bool) {
            NV1Bool valueValue__ = (NV1Bool) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b100) != 0 /* is-active doInitialize(NV2Bool) */ && valueValue_ instanceof NV2Bool) {
            NV2Bool valueValue__ = (NV2Bool) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b1000) != 0 /* is-active doInitialize(NV3Bool) */ && valueValue_ instanceof NV3Bool) {
            NV3Bool valueValue__ = (NV3Bool) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b10000) != 0 /* is-active doInitialize(NV4Bool) */ && valueValue_ instanceof NV4Bool) {
            NV4Bool valueValue__ = (NV4Bool) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b100000) != 0 /* is-active doInitialize(NV0Int) */ && valueValue_ instanceof NV0Int) {
            NV0Int valueValue__ = (NV0Int) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b11000000) != 0 /* is-active doInitialize(Object, NV1IntLibrary) || doInitialize(Object, NV1IntLibrary) */) {
            if ((state & 0b1000000) != 0 /* is-active doInitialize(Object, NV1IntLibrary) */) {
                Initialize6Data s7_ = this.initialize6_cache;
                while (s7_ != null) {
                    if ((s7_.arrays_.accepts(valueValue_)) && (s7_.arrays_.isArray(valueValue_))) {
                        return doInitialize(valueValue_, s7_.arrays_);
                    }
                    s7_ = s7_.next_;
                }
            }
            if ((state & 0b10000000) != 0 /* is-active doInitialize(Object, NV1IntLibrary) */) {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    if (((N_V1_INT_LIBRARY_.getUncached(valueValue_)).isArray(valueValue_))) {
                        return doInitialize(valueValue_, (N_V1_INT_LIBRARY_.getUncached(valueValue_)));
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
        }
        if ((state & 0b100000000) != 0 /* is-active doInitialize(NV2Int) */ && valueValue_ instanceof NV2Int) {
            NV2Int valueValue__ = (NV2Int) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b1000000000) != 0 /* is-active doInitialize(NV3Int) */ && valueValue_ instanceof NV3Int) {
            NV3Int valueValue__ = (NV3Int) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b10000000000) != 0 /* is-active doInitialize(NV4Int) */ && valueValue_ instanceof NV4Int) {
            NV4Int valueValue__ = (NV4Int) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b100000000000) != 0 /* is-active doInitialize(NV0Real) */ && valueValue_ instanceof NV0Real) {
            NV0Real valueValue__ = (NV0Real) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b1000000000000) != 0 /* is-active doInitialize(NV1Real) */ && valueValue_ instanceof NV1Real) {
            NV1Real valueValue__ = (NV1Real) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b10000000000000) != 0 /* is-active doInitialize(NV2Real) */ && valueValue_ instanceof NV2Real) {
            NV2Real valueValue__ = (NV2Real) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b100000000000000) != 0 /* is-active doInitialize(NV3Real) */ && valueValue_ instanceof NV3Real) {
            NV3Real valueValue__ = (NV3Real) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state & 0b1000000000000000) != 0 /* is-active doInitialize(NV4Real) */ && valueValue_ instanceof NV4Real) {
            NV4Real valueValue__ = (NV4Real) valueValue_;
            return doInitialize(valueValue__);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(valueValue_);
    }

    private Object executeAndSpecialize(Object valueValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state = state_;
        int exclude = exclude_;
        try {
            if (valueValue instanceof NV0Bool) {
                NV0Bool valueValue_ = (NV0Bool) valueValue;
                this.state_ = state = state | 0b1 /* add-active doInitialize(NV0Bool) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV1Bool) {
                NV1Bool valueValue_ = (NV1Bool) valueValue;
                this.state_ = state = state | 0b10 /* add-active doInitialize(NV1Bool) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV2Bool) {
                NV2Bool valueValue_ = (NV2Bool) valueValue;
                this.state_ = state = state | 0b100 /* add-active doInitialize(NV2Bool) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV3Bool) {
                NV3Bool valueValue_ = (NV3Bool) valueValue;
                this.state_ = state = state | 0b1000 /* add-active doInitialize(NV3Bool) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV4Bool) {
                NV4Bool valueValue_ = (NV4Bool) valueValue;
                this.state_ = state = state | 0b10000 /* add-active doInitialize(NV4Bool) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV0Int) {
                NV0Int valueValue_ = (NV0Int) valueValue;
                this.state_ = state = state | 0b100000 /* add-active doInitialize(NV0Int) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if ((exclude) == 0 /* is-not-excluded doInitialize(Object, NV1IntLibrary) */) {
                int count7_ = 0;
                Initialize6Data s7_ = this.initialize6_cache;
                if ((state & 0b1000000) != 0 /* is-active doInitialize(Object, NV1IntLibrary) */) {
                    while (s7_ != null) {
                        if ((s7_.arrays_.accepts(valueValue)) && (s7_.arrays_.isArray(valueValue))) {
                            break;
                        }
                        s7_ = s7_.next_;
                        count7_++;
                    }
                }
                if (s7_ == null) {
                    {
                        NV1IntLibrary arrays__ = super.insert((N_V1_INT_LIBRARY_.create(valueValue)));
                        // assert (s7_.arrays_.accepts(valueValue));
                        if ((arrays__.isArray(valueValue)) && count7_ < (3)) {
                            s7_ = super.insert(new Initialize6Data(initialize6_cache));
                            s7_.arrays_ = s7_.insertAccessor(arrays__);
                            this.initialize6_cache = s7_;
                            this.state_ = state = state | 0b1000000 /* add-active doInitialize(Object, NV1IntLibrary) */;
                        }
                    }
                }
                if (s7_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doInitialize(valueValue, s7_.arrays_);
                }
            }
            {
                Node prev_ = NodeUtil.pushEncapsulatingNode(this);
                try {
                    {
                        NV1IntLibrary initialize7_arrays__ = (N_V1_INT_LIBRARY_.getUncached(valueValue));
                        if ((initialize7_arrays__.isArray(valueValue))) {
                            this.exclude_ = exclude = exclude | 0b1 /* add-excluded doInitialize(Object, NV1IntLibrary) */;
                            this.initialize6_cache = null;
                            state = state & 0xffffffbf /* remove-active doInitialize(Object, NV1IntLibrary) */;
                            this.state_ = state = state | 0b10000000 /* add-active doInitialize(Object, NV1IntLibrary) */;
                            lock.unlock();
                            hasLock = false;
                            return doInitialize(valueValue, initialize7_arrays__);
                        }
                    }
                } finally {
                    NodeUtil.popEncapsulatingNode(prev_);
                }
            }
            if (valueValue instanceof NV2Int) {
                NV2Int valueValue_ = (NV2Int) valueValue;
                this.state_ = state = state | 0b100000000 /* add-active doInitialize(NV2Int) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV3Int) {
                NV3Int valueValue_ = (NV3Int) valueValue;
                this.state_ = state = state | 0b1000000000 /* add-active doInitialize(NV3Int) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV4Int) {
                NV4Int valueValue_ = (NV4Int) valueValue;
                this.state_ = state = state | 0b10000000000 /* add-active doInitialize(NV4Int) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV0Real) {
                NV0Real valueValue_ = (NV0Real) valueValue;
                this.state_ = state = state | 0b100000000000 /* add-active doInitialize(NV0Real) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV1Real) {
                NV1Real valueValue_ = (NV1Real) valueValue;
                this.state_ = state = state | 0b1000000000000 /* add-active doInitialize(NV1Real) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV2Real) {
                NV2Real valueValue_ = (NV2Real) valueValue;
                this.state_ = state = state | 0b10000000000000 /* add-active doInitialize(NV2Real) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV3Real) {
                NV3Real valueValue_ = (NV3Real) valueValue;
                this.state_ = state = state | 0b100000000000000 /* add-active doInitialize(NV3Real) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV4Real) {
                NV4Real valueValue_ = (NV4Real) valueValue;
                this.state_ = state = state | 0b1000000000000000 /* add-active doInitialize(NV4Real) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            throw new UnsupportedSpecializationException(this, new Node[] {this.value_}, valueValue);
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
            Initialize6Data s7_ = this.initialize6_cache;
            if ((s7_ == null || s7_.next_ == null)) {
                return NodeCost.MONOMORPHIC;
            }
        }
        return NodeCost.POLYMORPHIC;
    }

    public static NablaInitializeVariableFromJsonNode create(JsonElement initialValue, NablaExpressionNode value) {
        return new NablaInitializeVariableFromJsonNodeGen(initialValue, value);
    }

    public static NablaInitializeVariableFromJsonNode create(NablaExpressionNode value) {
        return new NablaInitializeVariableFromJsonNodeGen(value);
    }

    @GeneratedBy(NablaInitializeVariableFromJsonNode.class)
    private static final class Initialize6Data extends Node {

        @Child Initialize6Data next_;
        @Child NV1IntLibrary arrays_;

        Initialize6Data(Initialize6Data next_) {
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
