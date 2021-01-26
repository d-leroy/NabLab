// CheckStyle: start generated
package fr.cea.nabla.interpreter.nodes.expression;

import com.google.gson.JsonElement;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.library.LibraryFactory;
import com.oracle.truffle.api.nodes.EncapsulatingNodeReference;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import fr.cea.nabla.interpreter.values.NV0Bool;
import fr.cea.nabla.interpreter.values.NV0Int;
import fr.cea.nabla.interpreter.values.NV0Real;
import fr.cea.nabla.interpreter.values.NV1Bool;
import fr.cea.nabla.interpreter.values.NV1IntLibrary;
import fr.cea.nabla.interpreter.values.NV1RealLibrary;
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
@SuppressWarnings("unused")
public final class NablaInitializeVariableFromJsonNodeGen extends NablaInitializeVariableFromJsonNode {

    private static final LibraryFactory<NV1IntLibrary> N_V1_INT_LIBRARY_ = LibraryFactory.resolve(NV1IntLibrary.class);
    private static final LibraryFactory<NV1RealLibrary> N_V1_REAL_LIBRARY_ = LibraryFactory.resolve(NV1RealLibrary.class);

    @Child private NablaExpressionNode value_;
    @CompilationFinal private volatile int state_0_;
    @CompilationFinal private volatile int exclude_;
    @Child private Initialize6Data initialize6_cache;
    @Child private Initialize12Data initialize12_cache;

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
        int state_0 = state_0_;
        Object valueValue_ = this.value_.executeGeneric(frameValue);
        if ((state_0 & 0b1) != 0 /* is-state_0 doInitialize(NV0Bool) */ && valueValue_ instanceof NV0Bool) {
            NV0Bool valueValue__ = (NV0Bool) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state_0 & 0b10) != 0 /* is-state_0 doInitialize(NV1Bool) */ && valueValue_ instanceof NV1Bool) {
            NV1Bool valueValue__ = (NV1Bool) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state_0 & 0b100) != 0 /* is-state_0 doInitialize(NV2Bool) */ && valueValue_ instanceof NV2Bool) {
            NV2Bool valueValue__ = (NV2Bool) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state_0 & 0b1000) != 0 /* is-state_0 doInitialize(NV3Bool) */ && valueValue_ instanceof NV3Bool) {
            NV3Bool valueValue__ = (NV3Bool) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state_0 & 0b10000) != 0 /* is-state_0 doInitialize(NV4Bool) */ && valueValue_ instanceof NV4Bool) {
            NV4Bool valueValue__ = (NV4Bool) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state_0 & 0b100000) != 0 /* is-state_0 doInitialize(NV0Int) */ && valueValue_ instanceof NV0Int) {
            NV0Int valueValue__ = (NV0Int) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state_0 & 0b11000000) != 0 /* is-state_0 doInitialize(Object, NV1IntLibrary) || doInitialize(Object, NV1IntLibrary) */) {
            if ((state_0 & 0b1000000) != 0 /* is-state_0 doInitialize(Object, NV1IntLibrary) */) {
                Initialize6Data s7_ = this.initialize6_cache;
                while (s7_ != null) {
                    if ((s7_.arrays_.accepts(valueValue_)) && (s7_.arrays_.isArray(valueValue_))) {
                        return doInitialize(valueValue_, s7_.arrays_);
                    }
                    s7_ = s7_.next_;
                }
            }
            if ((state_0 & 0b10000000) != 0 /* is-state_0 doInitialize(Object, NV1IntLibrary) */) {
                EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                Node prev_ = encapsulating_.set(this);
                try {
                    {
                        NV1IntLibrary initialize7_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                        if ((initialize7_arrays__.isArray(valueValue_))) {
                            return this.initialize7Boundary(state_0, valueValue_);
                        }
                    }
                } finally {
                    encapsulating_.set(prev_);
                }
            }
        }
        if ((state_0 & 0b100000000) != 0 /* is-state_0 doInitialize(NV2Int) */ && valueValue_ instanceof NV2Int) {
            NV2Int valueValue__ = (NV2Int) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state_0 & 0b1000000000) != 0 /* is-state_0 doInitialize(NV3Int) */ && valueValue_ instanceof NV3Int) {
            NV3Int valueValue__ = (NV3Int) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state_0 & 0b10000000000) != 0 /* is-state_0 doInitialize(NV4Int) */ && valueValue_ instanceof NV4Int) {
            NV4Int valueValue__ = (NV4Int) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state_0 & 0b100000000000) != 0 /* is-state_0 doInitialize(NV0Real) */ && valueValue_ instanceof NV0Real) {
            NV0Real valueValue__ = (NV0Real) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state_0 & 0b11000000000000) != 0 /* is-state_0 doInitialize(Object, NV1RealLibrary) || doInitialize(Object, NV1RealLibrary) */) {
            if ((state_0 & 0b1000000000000) != 0 /* is-state_0 doInitialize(Object, NV1RealLibrary) */) {
                Initialize12Data s13_ = this.initialize12_cache;
                while (s13_ != null) {
                    if ((s13_.arrays_.accepts(valueValue_)) && (s13_.arrays_.isArray(valueValue_))) {
                        return doInitialize(valueValue_, s13_.arrays_);
                    }
                    s13_ = s13_.next_;
                }
            }
            if ((state_0 & 0b10000000000000) != 0 /* is-state_0 doInitialize(Object, NV1RealLibrary) */) {
                EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                Node prev_ = encapsulating_.set(this);
                try {
                    {
                        NV1RealLibrary initialize13_arrays__ = (N_V1_REAL_LIBRARY_.getUncached());
                        if ((initialize13_arrays__.isArray(valueValue_))) {
                            return this.initialize13Boundary(state_0, valueValue_);
                        }
                    }
                } finally {
                    encapsulating_.set(prev_);
                }
            }
        }
        if ((state_0 & 0b100000000000000) != 0 /* is-state_0 doInitialize(NV2Real) */ && valueValue_ instanceof NV2Real) {
            NV2Real valueValue__ = (NV2Real) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state_0 & 0b1000000000000000) != 0 /* is-state_0 doInitialize(NV3Real) */ && valueValue_ instanceof NV3Real) {
            NV3Real valueValue__ = (NV3Real) valueValue_;
            return doInitialize(valueValue__);
        }
        if ((state_0 & 0x10000) != 0 /* is-state_0 doInitialize(NV4Real) */ && valueValue_ instanceof NV4Real) {
            NV4Real valueValue__ = (NV4Real) valueValue_;
            return doInitialize(valueValue__);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(valueValue_);
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object initialize7Boundary(int state_0, Object valueValue_) {
        {
            NV1IntLibrary initialize7_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
            return doInitialize(valueValue_, initialize7_arrays__);
        }
    }

    @SuppressWarnings("static-method")
    @TruffleBoundary
    private Object initialize13Boundary(int state_0, Object valueValue_) {
        {
            NV1RealLibrary initialize13_arrays__ = (N_V1_REAL_LIBRARY_.getUncached());
            return doInitialize(valueValue_, initialize13_arrays__);
        }
    }

    private Object executeAndSpecialize(Object valueValue) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        int state_0 = state_0_;
        int exclude = exclude_;
        try {
            if (valueValue instanceof NV0Bool) {
                NV0Bool valueValue_ = (NV0Bool) valueValue;
                this.state_0_ = state_0 = state_0 | 0b1 /* add-state_0 doInitialize(NV0Bool) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV1Bool) {
                NV1Bool valueValue_ = (NV1Bool) valueValue;
                this.state_0_ = state_0 = state_0 | 0b10 /* add-state_0 doInitialize(NV1Bool) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV2Bool) {
                NV2Bool valueValue_ = (NV2Bool) valueValue;
                this.state_0_ = state_0 = state_0 | 0b100 /* add-state_0 doInitialize(NV2Bool) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV3Bool) {
                NV3Bool valueValue_ = (NV3Bool) valueValue;
                this.state_0_ = state_0 = state_0 | 0b1000 /* add-state_0 doInitialize(NV3Bool) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV4Bool) {
                NV4Bool valueValue_ = (NV4Bool) valueValue;
                this.state_0_ = state_0 = state_0 | 0b10000 /* add-state_0 doInitialize(NV4Bool) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV0Int) {
                NV0Int valueValue_ = (NV0Int) valueValue;
                this.state_0_ = state_0 = state_0 | 0b100000 /* add-state_0 doInitialize(NV0Int) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (((exclude & 0b1)) == 0 /* is-not-exclude doInitialize(Object, NV1IntLibrary) */) {
                int count7_ = 0;
                Initialize6Data s7_ = this.initialize6_cache;
                if ((state_0 & 0b1000000) != 0 /* is-state_0 doInitialize(Object, NV1IntLibrary) */) {
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
                            this.state_0_ = state_0 = state_0 | 0b1000000 /* add-state_0 doInitialize(Object, NV1IntLibrary) */;
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
                NV1IntLibrary initialize7_arrays__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            initialize7_arrays__ = (N_V1_INT_LIBRARY_.getUncached());
                            if ((initialize7_arrays__.isArray(valueValue))) {
                                this.exclude_ = exclude = exclude | 0b1 /* add-exclude doInitialize(Object, NV1IntLibrary) */;
                                this.initialize6_cache = null;
                                state_0 = state_0 & 0xffffffbf /* remove-state_0 doInitialize(Object, NV1IntLibrary) */;
                                this.state_0_ = state_0 = state_0 | 0b10000000 /* add-state_0 doInitialize(Object, NV1IntLibrary) */;
                                lock.unlock();
                                hasLock = false;
                                return doInitialize(valueValue, initialize7_arrays__);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (valueValue instanceof NV2Int) {
                NV2Int valueValue_ = (NV2Int) valueValue;
                this.state_0_ = state_0 = state_0 | 0b100000000 /* add-state_0 doInitialize(NV2Int) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV3Int) {
                NV3Int valueValue_ = (NV3Int) valueValue;
                this.state_0_ = state_0 = state_0 | 0b1000000000 /* add-state_0 doInitialize(NV3Int) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV4Int) {
                NV4Int valueValue_ = (NV4Int) valueValue;
                this.state_0_ = state_0 = state_0 | 0b10000000000 /* add-state_0 doInitialize(NV4Int) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV0Real) {
                NV0Real valueValue_ = (NV0Real) valueValue;
                this.state_0_ = state_0 = state_0 | 0b100000000000 /* add-state_0 doInitialize(NV0Real) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (((exclude & 0b10)) == 0 /* is-not-exclude doInitialize(Object, NV1RealLibrary) */) {
                int count13_ = 0;
                Initialize12Data s13_ = this.initialize12_cache;
                if ((state_0 & 0b1000000000000) != 0 /* is-state_0 doInitialize(Object, NV1RealLibrary) */) {
                    while (s13_ != null) {
                        if ((s13_.arrays_.accepts(valueValue)) && (s13_.arrays_.isArray(valueValue))) {
                            break;
                        }
                        s13_ = s13_.next_;
                        count13_++;
                    }
                }
                if (s13_ == null) {
                    {
                        NV1RealLibrary arrays__1 = super.insert((N_V1_REAL_LIBRARY_.create(valueValue)));
                        // assert (s13_.arrays_.accepts(valueValue));
                        if ((arrays__1.isArray(valueValue)) && count13_ < (3)) {
                            s13_ = super.insert(new Initialize12Data(initialize12_cache));
                            s13_.arrays_ = s13_.insertAccessor(arrays__1);
                            this.initialize12_cache = s13_;
                            this.state_0_ = state_0 = state_0 | 0b1000000000000 /* add-state_0 doInitialize(Object, NV1RealLibrary) */;
                        }
                    }
                }
                if (s13_ != null) {
                    lock.unlock();
                    hasLock = false;
                    return doInitialize(valueValue, s13_.arrays_);
                }
            }
            {
                NV1RealLibrary initialize13_arrays__ = null;
                {
                    EncapsulatingNodeReference encapsulating_ = EncapsulatingNodeReference.getCurrent();
                    Node prev_ = encapsulating_.set(this);
                    try {
                        {
                            initialize13_arrays__ = (N_V1_REAL_LIBRARY_.getUncached());
                            if ((initialize13_arrays__.isArray(valueValue))) {
                                this.exclude_ = exclude = exclude | 0b10 /* add-exclude doInitialize(Object, NV1RealLibrary) */;
                                this.initialize12_cache = null;
                                state_0 = state_0 & 0xffffefff /* remove-state_0 doInitialize(Object, NV1RealLibrary) */;
                                this.state_0_ = state_0 = state_0 | 0b10000000000000 /* add-state_0 doInitialize(Object, NV1RealLibrary) */;
                                lock.unlock();
                                hasLock = false;
                                return doInitialize(valueValue, initialize13_arrays__);
                            }
                        }
                    } finally {
                        encapsulating_.set(prev_);
                    }
                }
            }
            if (valueValue instanceof NV2Real) {
                NV2Real valueValue_ = (NV2Real) valueValue;
                this.state_0_ = state_0 = state_0 | 0b100000000000000 /* add-state_0 doInitialize(NV2Real) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV3Real) {
                NV3Real valueValue_ = (NV3Real) valueValue;
                this.state_0_ = state_0 = state_0 | 0b1000000000000000 /* add-state_0 doInitialize(NV3Real) */;
                lock.unlock();
                hasLock = false;
                return doInitialize(valueValue_);
            }
            if (valueValue instanceof NV4Real) {
                NV4Real valueValue_ = (NV4Real) valueValue;
                this.state_0_ = state_0 = state_0 | 0x10000 /* add-state_0 doInitialize(NV4Real) */;
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
        int state_0 = state_0_;
        if (state_0 == 0) {
            return NodeCost.UNINITIALIZED;
        } else {
            if ((state_0 & (state_0 - 1)) == 0 /* is-single-state_0  */) {
                Initialize6Data s7_ = this.initialize6_cache;
                Initialize12Data s13_ = this.initialize12_cache;
                if ((s7_ == null || s7_.next_ == null) && (s13_ == null || s13_.next_ == null)) {
                    return NodeCost.MONOMORPHIC;
                }
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
    @GeneratedBy(NablaInitializeVariableFromJsonNode.class)
    private static final class Initialize12Data extends Node {

        @Child Initialize12Data next_;
        @Child NV1RealLibrary arrays_;

        Initialize12Data(Initialize12Data next_) {
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
